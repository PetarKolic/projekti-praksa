package mjcompiler;

import mjcompiler.ast.*;
import org.apache.log4j.Logger;
import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;
import rs.etf.pp1.symboltable.structure.SymbolDataStructure;

public class SemanticAnalyzer extends VisitorAdaptor
{
    private static final Struct boolType = new Struct(Struct.Bool);

    private Struct  currentType;
    private String  currentTypeName;
    private boolean isError = false;

    private static final Logger log = Logger.getLogger(SemanticAnalyzer.class);
    public SemanticAnalyzer()
    {
        // Inicijalizujemo tabelu simbola.
        Tab.init();
        // Dodajemo u universe scope.
        Tab.currentScope().addToLocals(new Obj(Obj.Type, "bool", boolType));
    }


    private static boolean doesExist(String symbolName)
    {
        return Tab.find(symbolName) != Tab.noObj;
    }


    private void logError(SyntaxNode node, String message)
    {
        isError = true;
        String preMsg = String.format("Greska na liniji %d: ", node.getLine());
        log.info(preMsg + " " + message);
    }
    public boolean hasError() {
        return isError;
    }
    @Override
    public void visit(Program program)
    {
        Obj programObj = Tab.find(program.getProgramName().getProgramName());

        // Tabela simbola za trenutni scope se gubi pri zatvaranju scope-a.
        // Trenutni scope predstavlja globalnu tabelu simbola, odnosno definiciju
        // simbolickih konstanti i globalnih promenljivih. Te simbole treba sacuvati
        // jer ce se koristiti za generisanje koda. Svaki simbol u tabeli simbola
        // poznaje pojam vlasnistva lokalnih simbola. Nas simbol koji odredjuje program
        // je vlasnik svih simbola iz globalnog scope-a, te u njega upisujemo sve simbole
        // iz tabele simbola.
        programObj.setLocals(Tab.currentScope().getLocals());
        Tab.closeScope();
    }

    public void visit(ProgramName programName)
    {
        String name = programName.getProgramName();

        // Dodajemo program(naziv programa) u tabelu simbola
        Tab.insert(Obj.Prog, name, Tab.noType);
        // Sada otvaramo scope za globalne definicije
        Tab.openScope();
    }

    private void defineConstant(String typeName, String constName, ConstValue constValue)
    {
        if (doesExist(constName))
        {
            logError(constValue, "Ponovna definicija simbola : " + constName);

            return;
        }

        // const int a = 5;
        // Ukoliko nije tipa int/char/bool, prijavi gresku
        if (!typeName.equals("int") && !typeName.equals("char") && !typeName.equals("bool"))
        {
            logError(constValue, "Tip konstante mora biti int/char/bool!");

            return;
        }

        int value = 0;
        boolean isOk = true;
        if (constValue instanceof  NumConstValue)
        {
            value = ((NumConstValue) constValue).getNumConst();
            isOk = typeName.equals("int");
        }else if (constValue instanceof  BoolConstValue)
        {
            value = ((BoolConstValue) constValue).getBoolConst() ? 1 : 0;
            isOk  = typeName.equals("bool");
        }else
        {
            value = Character.getNumericValue(((CharConstValue) constValue).getCharConst());
            isOk  = typeName.equals("char");
        }

        Tab.currentScope().addToLocals(new Obj(Obj.Con, constName, Tab.find(typeName).getType(), value, 0));
        if (!isOk)
        {
            logError(constValue, "Greska!Ocekivan tip sa desne strane konstante je :" + typeName + "!");
        }
    }

    @Override
    public void visit(ConstDecl node)
    {
        String constName = node.getConstName();
        String typeName  = node.getType().getTypeName();
        ConstValue constValue = node.getConstValue();

        defineConstant(typeName, constName, constValue);
    }

    public void visit(ConstDefRepeatNonEmpty node)
    {
        defineConstant(currentTypeName, node.getConstName(), node.getConstValue());
    }


    @Override
    public void visit(Type type)
    {
        currentType     = Tab.find(type.getTypeName()).getType();
        currentTypeName = type.getTypeName();

        if (!doesExist(type.getTypeName())) {
            logError(type, String.format( "Greska! Ne postoji tip podatka %s", currentTypeName));
        }
    }

    private void defineVar(String varName, boolean isArray, SyntaxNode node)
    {
        // Proveravamo prvo da li postoji simbol sa datim nazivom
        // ukoliko postoji ne mozemo definisati promenljivu.
        if (doesExist(varName))
        {
            logError(node, String.format("Promenljiva sa nazivom %s vec postoji!", varName));
            return;
        }

        // Deklarisemo promenljiv tipa niz.
        if (isArray) {
            // Definisemo novi tip, odnosno niz currentType elemenata
            Struct arrayType = new Struct(Struct.Array, currentType);
            Tab.insert(Obj.Var, varName, arrayType);
        }else{
            Tab.insert(Obj.Var, varName, currentType);
        }
    }

    @Override
    public void visit(VarDecl node)
    {
        String varName = node.getVarName();

        defineVar(varName, node.getOptArray() instanceof OptArrayNonEmpty, node);
    }

    @Override
    public void visit(VarDefRepeatNonEmpty node)
    {
        String varName = node.getVarName();

        defineVar(varName, node.getOptArray() instanceof  OptArrayNonEmpty, node);
    }

    @Override
    public void visit(MethodName node)
    {
        if (doesExist(node.getMethodName()) || !node.getMethodName().equals("main"))
        {
            logError(node, "Simbol sa datim nazivom vec postoji ili se metoda ne zove main");
        }else
        {
            Tab.currentScope().addToLocals(new Obj(Obj.Meth, node.getMethodName(), Tab.noType));
        }
        // Otvaramo novi doseg vazenja za nasu metodu main
        Tab.openScope();
    }

    public void visit(MethodDecl methodDecl)
    {
        SymbolDataStructure sds = Tab.currentScope().getLocals();
        Tab.closeScope();

        Obj methObj = Tab.find(methodDecl.getMethodName().getMethodName());
        methObj.setLocals(sds);
    }

    private void checkIncDecr(Obj varObj, String errorMsg, SyntaxNode node)
    {
        if (!varObj.getType().equals(Tab.intType)) {
            logError(node, errorMsg);
        }
    }
    @Override
    public  void visit(DesignatorStatementInc node)
    {
        checkIncDecr(node.getDesignator().obj,
                "Greska! Inkrement je moguc samo nad tipom podatka int", node);
    }

    @Override
    public void visit(DesignatorStatementDecr node)
    {
        checkIncDecr(node.getDesignator().obj,
                "Greska, dekrement samo moze nad tipom int!", node);
    }


    @Override
    public void visit(DesignatorStatementAssignop node)
    {
        Obj    varObj   = node.getDesignator().obj;
        Struct exprType = node.getExpr().struct;

        if (!exprType.assignableTo(varObj.getType())){
            logError(node, String.format("Tip promenljive %s nije kompatabilan sa desnom straom",
                    varObj.getName()));
        }
    }

    @Override
    public void visit(DesignatorVar node)
    {
        if (!doesExist(node.getVarName())) {
            logError(node, String.format("Ne postoji promenljiva sa nazivom %s", node.getVarName()));
        }

        node.obj = Tab.find(node.getVarName());
        if (node.obj.getKind() != Obj.Var && node.obj.getKind() != Obj.Con)
        {
            logError(node, String.format("%s nije promenljiva!", node.getVarName()));
        }
    }

    @Override
    public void visit(DesignatorArrElement node)
    {
        // int niz[];
        // niz[i]

        Obj varObj = Tab.find(node.getDesignatorVarArr().getVarName());

        // Provera da li postoji promenljiva sa datim nazivom
        if (!doesExist(node.getDesignatorVarArr().getVarName())){
            logError(node, String.format("Promenljiva sa nazivom %s ne postoji!",
                                    varObj.getName()));
            return;
        }
        // Proveravamo da li je tipa array
        if (!varObj.getType().isRefType()) {
            logError(node,String.format("Promenljiva %s nije niz!", varObj.getName()));

            node.obj = new Obj(Obj.Elem, "", varObj.getType());
        }

        // Kreiramo simbol koji predstavlja element niza
        // Njegov tip se dohvata preko getElemType,
        // jer tip promenljive varObj je niz, koji sadrzi elemente
        // tipa ElemType.
        node.obj = new Obj(Obj.Elem, "", varObj.getType().getElemType());
    }

    public void visit(FactorConstValue factorConstValue)
    {
        ConstValue constValue = factorConstValue.getConstValue();

        if (constValue instanceof NumConstValue)
            factorConstValue.struct = Tab.intType;
        else if (constValue instanceof CharConstValue)
            factorConstValue.struct = Tab.charType;
        else
            factorConstValue.struct = boolType;
    }

    public void visit(FactorParenExpr factorParenExpr)
    {
        factorParenExpr.struct = factorParenExpr.getExpr().struct;
    }

    public void visit(FactorNewArray factorNewArray)
    {
        if (factorNewArray.getExpr().struct != Tab.intType)
        {
            logError(factorNewArray, "Velicina niza mora biti tipa int!");
        }
        Struct type = Tab.find(factorNewArray.getType().getTypeName()).getType();

        factorNewArray.struct = new Struct(Struct.Array, type);
    }

    public void visit(FactorDesignator factorDesignator)
    {
        Obj designator = factorDesignator.getDesignator().obj;
        factorDesignator.struct =designator.getType();
    }


    public void checkIfValidExpr(SyntaxNode node, Struct ltype, Struct rtype)
    {
        if (ltype != rtype || rtype != Tab.intType)
        {
            logError(node, "{+,-,*,/,%} operatori su moguci samo nad operandima tipa int!");
        }
    }

    // a * b
    // ^ ^---------- mulopFactorRepeat
    // factor
    public void visit(Term term)
    {
        Struct ltype = term.getFactor().struct;
        Struct rtype = term.getMulopFactorRepeat().struct;

        term.struct = ltype;
        if (rtype == null)
        {
            return;
        }

        // ispitaj za izraz da li su intovi
        checkIfValidExpr(term, ltype, rtype);
    }

    public void visit(MulopFactorRepeatNonEmpty repeatNonEmpty)
    {
        Struct ltype = repeatNonEmpty.getMulopFactorRepeat().struct;
        Struct rtype = repeatNonEmpty.getFactor().struct;

        repeatNonEmpty.struct = rtype;
        if (ltype == null)
        {
            return;
        }

        checkIfValidExpr(repeatNonEmpty, ltype, rtype);
    }

    public void visit(AddopTermRepeatNonEmpty repeatNonEmpty)
    {
        Struct ltype = repeatNonEmpty.getAddopTermRepeat().struct;
        Struct rtype = repeatNonEmpty.getTerm().struct;

        repeatNonEmpty.struct = rtype;
        if (ltype == null)
            return;

        checkIfValidExpr(repeatNonEmpty, ltype, rtype);
    }

    public void visit(Expr1 expr)
    {
        boolean isMinus = expr.getNegTerm().getOptMinus() instanceof  OptMinusNonEmpty;
        Struct ltype = expr.getNegTerm().getTerm().struct;
        Struct rtype = expr.getAddopTermRepeat().struct;

        expr.struct = ltype;
        if (isMinus && ltype != Tab.intType)
        {
            logError(expr, "Unarni operator {-} zahteva tip {int}");
        }

        if (rtype == null)
            return;

        checkIfValidExpr(expr, ltype, rtype);
    }

    public void visit(NormalExpr node) {
        node.struct = node.getExpr1().struct;
    }

    public void visit(TernaryExpr node) {
        Struct left = node.getExpr1().struct;
        Struct right = node.getExpr11().struct;

        if (!left.equals(right)) {
            logError(node, "Leva i desna strana ternarnog operatora moraju biti istog tipa!");
        }
        node.struct = left;
    }

    public void visit(CondFactCompare node) {
        Struct left = node.getExpr1().struct;
        Struct right = node.getExpr11().struct;

        if (!left.compatibleWith(right)) {
            logError(node, "Pri poredjenju tipovi moraju biti kompatibilni");
        }

        if (left.isRefType() && right.isRefType())
        {
            if (!(node.getRelop() instanceof RelopEquals) && !(node.getRelop() instanceof  RelopNequals))
            {
                logError(node, "Poredjenje nizova je moguce samo sa operatorima == i !=");
            }
        }
    }

    public void visit(StatementRead stmtRead)
    {
        Obj obj = stmtRead.getDesignator().obj;
        Struct type = obj.getType();
        if (obj.getKind() != Obj.Var && obj.getKind() != Obj.Elem)
        {
            logError(stmtRead, "Funkcija read ocekuje promenljivu ili element niza");
        }

        if (type == Tab.noType)
        {
            logError(stmtRead, "Tip moze biti samo {int,bool,char} za fju read");
        }
    }
}
