// generated with ast extension for cup
// version 0.8
// 14/0/2021 14:29:24


package mjcompiler.ast;

public class DesignatorArrElement extends Designator {

    private DesignatorVarArr DesignatorVarArr;
    private Expr Expr;

    public DesignatorArrElement (DesignatorVarArr DesignatorVarArr, Expr Expr) {
        this.DesignatorVarArr=DesignatorVarArr;
        if(DesignatorVarArr!=null) DesignatorVarArr.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
    }

    public DesignatorVarArr getDesignatorVarArr() {
        return DesignatorVarArr;
    }

    public void setDesignatorVarArr(DesignatorVarArr DesignatorVarArr) {
        this.DesignatorVarArr=DesignatorVarArr;
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DesignatorVarArr!=null) DesignatorVarArr.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DesignatorVarArr!=null) DesignatorVarArr.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DesignatorVarArr!=null) DesignatorVarArr.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("DesignatorArrElement(\n");

        if(DesignatorVarArr!=null)
            buffer.append(DesignatorVarArr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [DesignatorArrElement]");
        return buffer.toString();
    }
}
