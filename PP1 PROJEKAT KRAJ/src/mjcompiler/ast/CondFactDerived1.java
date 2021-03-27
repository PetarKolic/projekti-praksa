// generated with ast extension for cup
// version 0.8
// 14/0/2021 14:5:55


package mjcompiler.ast;

public class CondFactDerived1 extends CondFact {

    private Expr1 Expr1;

    public CondFactDerived1 (Expr1 Expr1) {
        this.Expr1=Expr1;
        if(Expr1!=null) Expr1.setParent(this);
    }

    public Expr1 getExpr1() {
        return Expr1;
    }

    public void setExpr1(Expr1 Expr1) {
        this.Expr1=Expr1;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Expr1!=null) Expr1.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Expr1!=null) Expr1.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Expr1!=null) Expr1.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("CondFactDerived1(\n");

        if(Expr1!=null)
            buffer.append(Expr1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [CondFactDerived1]");
        return buffer.toString();
    }
}
