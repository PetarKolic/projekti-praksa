// generated with ast extension for cup
// version 0.8
// 14/0/2021 14:29:24


package mjcompiler.ast;

public class TernaryExpr extends Expr {

    private CondFact CondFact;
    private Expr1 Expr1;
    private Colon Colon;
    private Expr1 Expr11;

    public TernaryExpr (CondFact CondFact, Expr1 Expr1, Colon Colon, Expr1 Expr11) {
        this.CondFact=CondFact;
        if(CondFact!=null) CondFact.setParent(this);
        this.Expr1=Expr1;
        if(Expr1!=null) Expr1.setParent(this);
        this.Colon=Colon;
        if(Colon!=null) Colon.setParent(this);
        this.Expr11=Expr11;
        if(Expr11!=null) Expr11.setParent(this);
    }

    public CondFact getCondFact() {
        return CondFact;
    }

    public void setCondFact(CondFact CondFact) {
        this.CondFact=CondFact;
    }

    public Expr1 getExpr1() {
        return Expr1;
    }

    public void setExpr1(Expr1 Expr1) {
        this.Expr1=Expr1;
    }

    public Colon getColon() {
        return Colon;
    }

    public void setColon(Colon Colon) {
        this.Colon=Colon;
    }

    public Expr1 getExpr11() {
        return Expr11;
    }

    public void setExpr11(Expr1 Expr11) {
        this.Expr11=Expr11;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(CondFact!=null) CondFact.accept(visitor);
        if(Expr1!=null) Expr1.accept(visitor);
        if(Colon!=null) Colon.accept(visitor);
        if(Expr11!=null) Expr11.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(CondFact!=null) CondFact.traverseTopDown(visitor);
        if(Expr1!=null) Expr1.traverseTopDown(visitor);
        if(Colon!=null) Colon.traverseTopDown(visitor);
        if(Expr11!=null) Expr11.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(CondFact!=null) CondFact.traverseBottomUp(visitor);
        if(Expr1!=null) Expr1.traverseBottomUp(visitor);
        if(Colon!=null) Colon.traverseBottomUp(visitor);
        if(Expr11!=null) Expr11.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("TernaryExpr(\n");

        if(CondFact!=null)
            buffer.append(CondFact.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr1!=null)
            buffer.append(Expr1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Colon!=null)
            buffer.append(Colon.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr11!=null)
            buffer.append(Expr11.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [TernaryExpr]");
        return buffer.toString();
    }
}
