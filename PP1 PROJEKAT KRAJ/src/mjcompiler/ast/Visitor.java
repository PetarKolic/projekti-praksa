// generated with ast extension for cup
// version 0.8
// 14/0/2021 14:29:24


package mjcompiler.ast;

public interface Visitor { 

    public void visit(Mulop Mulop);
    public void visit(ConstVarDeclRepeat ConstVarDeclRepeat);
    public void visit(Relop Relop);
    public void visit(Assignop Assignop);
    public void visit(OptArray OptArray);
    public void visit(OptMinus OptMinus);
    public void visit(AddopTermRepeat AddopTermRepeat);
    public void visit(Addop Addop);
    public void visit(Factor Factor);
    public void visit(AddopLeft AddopLeft);
    public void visit(Designator Designator);
    public void visit(ConstDefRepeat ConstDefRepeat);
    public void visit(ConstValue ConstValue);
    public void visit(VarDefRepeat VarDefRepeat);
    public void visit(VarDeclRepeat VarDeclRepeat);
    public void visit(AddopRight AddopRight);
    public void visit(StatementRepeat StatementRepeat);
    public void visit(OptPrintWidth OptPrintWidth);
    public void visit(Expr Expr);
    public void visit(DesignatorStatement DesignatorStatement);
    public void visit(MulopFactorRepeat MulopFactorRepeat);
    public void visit(Statement Statement);
    public void visit(MulopLeft MulopLeft);
    public void visit(CondFact CondFact);
    public void visit(MulopRight MulopRight);
    public void visit(MulopMod MulopMod);
    public void visit(MulopMul MulopMul);
    public void visit(MulopDiv MulopDiv);
    public void visit(AddoptSub AddoptSub);
    public void visit(AddopAdd AddopAdd);
    public void visit(AssignopMulopRight AssignopMulopRight);
    public void visit(AssignopAddopRight AssignopAddopRight);
    public void visit(AssignopEqual AssignopEqual);
    public void visit(FactorDesignator FactorDesignator);
    public void visit(FactorNewArray FactorNewArray);
    public void visit(FactorParenExpr FactorParenExpr);
    public void visit(FactorConstValue FactorConstValue);
    public void visit(MulopFactorRepeatEmpty MulopFactorRepeatEmpty);
    public void visit(MulopFactorRepeatNonEmpty MulopFactorRepeatNonEmpty);
    public void visit(Term Term);
    public void visit(AddopTermRepeatEmpty AddopTermRepeatEmpty);
    public void visit(AddopTermRepeatNonEmpty AddopTermRepeatNonEmpty);
    public void visit(NegTerm NegTerm);
    public void visit(Expr1 Expr1);
    public void visit(TernaryExpr TernaryExpr);
    public void visit(NormalExpr NormalExpr);
    public void visit(Colon Colon);
    public void visit(RelopLesseq RelopLesseq);
    public void visit(RelopLess RelopLess);
    public void visit(RelopGrteq RelopGrteq);
    public void visit(RelopGrt RelopGrt);
    public void visit(RelopNequals RelopNequals);
    public void visit(RelopEquals RelopEquals);
    public void visit(CondFactCompare CondFactCompare);
    public void visit(CondFactSingle CondFactSingle);
    public void visit(OptMinusEmpty OptMinusEmpty);
    public void visit(OptMinusNonEmpty OptMinusNonEmpty);
    public void visit(DesignatorArrElement DesignatorArrElement);
    public void visit(DesignatorVar DesignatorVar);
    public void visit(DesignatorVarArr DesignatorVarArr);
    public void visit(DesignatorStatementDecr DesignatorStatementDecr);
    public void visit(DesignatorStatementInc DesignatorStatementInc);
    public void visit(DesignatorStatementAssignop DesignatorStatementAssignop);
    public void visit(OptPrintWidthEmpty OptPrintWidthEmpty);
    public void visit(OptPrintWithNonEmpty OptPrintWithNonEmpty);
    public void visit(StatementPrint StatementPrint);
    public void visit(StatementRead StatementRead);
    public void visit(StatementDesignatorStatement StatementDesignatorStatement);
    public void visit(StatementRepeatEmpty StatementRepeatEmpty);
    public void visit(StatementRepeatNonEmpty StatementRepeatNonEmpty);
    public void visit(VarDefRepeatEmpty VarDefRepeatEmpty);
    public void visit(VarDefRepeatNonEmpty VarDefRepeatNonEmpty);
    public void visit(OptArrayEmpty OptArrayEmpty);
    public void visit(OptArrayNonEmpty OptArrayNonEmpty);
    public void visit(Type Type);
    public void visit(VarDeclRepeatEmpty VarDeclRepeatEmpty);
    public void visit(VarDeclRepeatNonEmpty VarDeclRepeatNonEmpty);
    public void visit(VarDecl VarDecl);
    public void visit(ConstDefRepeatEmpty ConstDefRepeatEmpty);
    public void visit(ConstDefRepeatNonEmpty ConstDefRepeatNonEmpty);
    public void visit(CharConstValue CharConstValue);
    public void visit(BoolConstValue BoolConstValue);
    public void visit(NumConstValue NumConstValue);
    public void visit(ConstDecl ConstDecl);
    public void visit(ConstVarDeclRepeatEmpty ConstVarDeclRepeatEmpty);
    public void visit(ConstVarDeclRepeatConst ConstVarDeclRepeatConst);
    public void visit(ConstVarDeclRepeatVar ConstVarDeclRepeatVar);
    public void visit(MethodName MethodName);
    public void visit(MethodDecl MethodDecl);
    public void visit(ProgramName ProgramName);
    public void visit(Program Program);

}
