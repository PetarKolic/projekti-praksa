package mjcompiler;

import mjcompiler.ast.*;
import mjcompiler.ast.VisitorAdaptor;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

import java.util.Stack;

public class CodeGenerator extends VisitorAdaptor
{

    private final Stack<Integer> ternarySecondExprJumps = new Stack<>();
    private final Stack<Integer> exitTernaryJumps = new Stack<>();
    public void visit(ProgramName programName)
    {
        Obj programObj = Tab.find(programName.getProgramName());

        Tab.openScope();

        programObj.getLocalSymbols().forEach(Tab.currentScope()::addToLocals);

        for (Obj obj : programObj.getLocalSymbols())
        {
            if (obj.getKind() == Obj.Var)
                Code.dataSize++;
        }

    //        Ukoliko nece linija iznad, obrisati je i ispod otkomentarisati
    //        programObj.getLocalSymbols().forEach(o -> Tab.currentScope().addToLocals(o));
    }

    public void visit(Program program)
    {
        Tab.closeScope();
    }

    // int f(x) int y; {} b1 = 1, b2 = 2
    // int f(x) {}  b1 = 1, b2 = 1
    public void visit(MethodName methodName)
    {
        Obj methodObj = Tab.find(methodName.getMethodName());

        Tab.openScope();
        methodObj.getLocalSymbols().forEach(Tab.currentScope()::addToLocals);
        //        Ukoliko nece linija iznad, obrisati je i ispod otkomentarisati
        //        methodObj.getLocalSymbols().forEach(o -> Tab.currentScope().addToLocals(o));


        // Naglasimo klasi mainPC, posto mora da zna odakle krece main fja
        // Main fja krece od trenutne vrednosti PC (jer odatle krecu instrukcije
        // za ulazak u main)
        Code.mainPc = Code.pc;
        // Generisemo ulaz u fju
        Code.put(Code.enter);
        Code.put(0); // broj argumenata je 0
        Code.put(methodObj.getLocalSymbols().size());
    }

    public void visit(MethodDecl methodDecl)
    {
         Tab.closeScope();

         Code.put(Code.exit);
         Code.put(Code.return_);
    }

    public void visit(FactorConstValue node)
    {
        ConstValue constValue = node.getConstValue();
        int value;
        if (constValue instanceof  NumConstValue)
        {
            value = ((NumConstValue) constValue).getNumConst();
        }else if (constValue instanceof  BoolConstValue)
        {
            value = ((BoolConstValue) constValue).getBoolConst() ? 1 : 0;
        }else
        {
            value = (((CharConstValue) constValue).getCharConst());
        }

        Code.loadConst(value);
    }

    public void visit(FactorNewArray node)
    {
        Struct type = Tab.find(node.getType().getTypeName()).getType();
        Code.put(Code.newarray);

        if (type == Tab.charType)
            Code.put(0);
        else
            Code.put(1);
    }

    public void visit(FactorDesignator node) {
        Code.load(node.getDesignator().obj);
    }

    public void visit(DesignatorVarArr node) {
        Obj varArrObj = Tab.find(node.getVarName());

        // na steku cemo imati ..., adresa_niza
        Code.load(varArrObj);
    }

    public void visit(AddopTermRepeatNonEmpty node) {
        int code = Code.add;
        if (node.getAddop() instanceof  AddoptSub)
            code = Code.sub;

        Code.put(code);
    }


    /// -5 + 15 * 2;

    public void visit(MulopFactorRepeatNonEmpty node) {
        int code = Code.mul;
        if (node.getMulop() instanceof MulopDiv)
            code = Code.div;
        else if (node.getMulop() instanceof  MulopMod)
            code = Code.rem;

        Code.put(code);
    }

    public void visit(NegTerm node) {
        if (node.getOptMinus() instanceof  OptMinusNonEmpty)
            Code.put(Code.neg);
    }

    public void visit(DesignatorStatementAssignop node) {
        Code.store(node.getDesignator().obj);
    }

    public void visit(DesignatorStatementInc node) {

        // Za load i store elementa niza, treba nam u oba slucaja
        // na steku arr,idx. Da bi to obezbedili, vec postojeci
        // par arr,idx dupliramo, tak oda imamo jedan par za load
        // i drugi za store
        if (node.getDesignator().obj.getKind() == Obj.Elem) {
            Code.put(Code.dup2);
        }
        Code.load(node.getDesignator().obj);
        Code.loadConst(1);
        Code.put(Code.add);
        Code.store(node.getDesignator().obj);
    }

    public void visit(DesignatorStatementDecr node) {
        if (node.getDesignator().obj.getKind() == Obj.Elem) {
            Code.put(Code.dup2);
        }
        Code.load(node.getDesignator().obj);
        Code.loadConst(-1);
        Code.put(Code.add);
        Code.store(node.getDesignator().obj);
    }

    public void visit(StatementRead node) {
        if (node.getDesignator().obj.getType().equals(Tab.charType))
            Code.put(Code.bread);
        else
            Code.put(Code.read);

        Code.store(node.getDesignator().obj);
    }

    public void visit(StatementPrint node) {
        if (node.getExpr().struct.equals(Tab.charType))
            Code.put(Code.bprint);
        else
            Code.put(Code.print);
    }

    public void visit(OptPrintWithNonEmpty node) {
        Code.loadConst(node.getNum());
    }

    public void visit(OptPrintWidthEmpty node) {
        Code.loadConst(1);
    }

    public void visit(CondFactSingle node) {
        Code.loadConst(0);

        // Nas originalni uslov je da skocimo na tacan deo ukoliko je
        // expr != 0, medjutim, true deo je odmah nakon izracunavanja uslova
        // te ne moramo generisati skok u true deo, nego skok u false deo
        // Funkcija putFalseJump dobije poredjenje koje kada bi se ispunilo
        // skocilo bi na true deo, i od njega nadje komplement, a bi generisalo
        // skok za slucaj da uslov NIJE tacan.

        // Ovde je skok relativan, skace se u odnosu na trenutnu vrednost PC-a
        // posto ne znamo gde je pocetak racunanja izraza za slucaj da je false
        // uslov, mi stavimo da je pomeraj za skok 0, a upisemo adresu gde taj
        // pomeraj treba da se azurira. Adresa na kojoj se nalazi pomeraj nakon
        // sto se pozove putFalseJump instrukcija je PC - 2 (Code.pc -  2)
        Code.putFalseJump(Code.ne, 0);

        ternarySecondExprJumps.push(Code.pc - 2);
    }

    public void visit(CondFactCompare node) {
        int code = Code.eq;
        if (node.getRelop() instanceof  RelopNequals)
            code = Code.ne;
        else if (node.getRelop() instanceof  RelopGrt)
            code = Code.gt;
        else if (node.getRelop() instanceof  RelopGrteq)
            code = Code.ge;
        else if (node.getRelop() instanceof  RelopLess)
            code = Code.lt;
        else if (node.getRelop() instanceof  RelopLesseq)
            code = Code.le;

        Code.putFalseJump(code, 0);
        ternarySecondExprJumps.push(Code.pc - 2);
    }

    public void visit(Colon node) {
        Code.putJump(0);

        exitTernaryJumps.push(Code.pc - 2);

        //Odavde krece generisanje instruckija za racunanje desnog izraza
        // u ternarnom operatoru
        int addrToFix = ternarySecondExprJumps.pop();

        // Fixup je metoda koja dobije adresu na kojoj se nalazi pomeraj
        // i namesti ga tako da se skoci na trenutnu vrednost Code.pc
        // a nama sada Code.pc ukazuje na instrukcije koje ce generisati
        // desni izraz ternarog operatora
        Code.fixup(addrToFix);
    }

    public void visit(TernaryExpr node) {
        int addrToFix = exitTernaryJumps.pop();

        // Bezuslovan skok kada se izracuna nas izraz, treba samo da prepravimo
        // da skoci na kraj ternarnog operatora, odnosno da preskoci ceo kod
        // za generisanje desnog dela (a to je trenutna vrednost Code.pc)
        Code.fixup(addrToFix);
    }

}
