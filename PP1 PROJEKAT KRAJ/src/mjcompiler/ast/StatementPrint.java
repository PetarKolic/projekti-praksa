// generated with ast extension for cup
// version 0.8
// 14/0/2021 14:29:24


package mjcompiler.ast;

public class StatementPrint extends Statement {

    private Expr Expr;
    private OptPrintWidth OptPrintWidth;

    public StatementPrint (Expr Expr, OptPrintWidth OptPrintWidth) {
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.OptPrintWidth=OptPrintWidth;
        if(OptPrintWidth!=null) OptPrintWidth.setParent(this);
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public OptPrintWidth getOptPrintWidth() {
        return OptPrintWidth;
    }

    public void setOptPrintWidth(OptPrintWidth OptPrintWidth) {
        this.OptPrintWidth=OptPrintWidth;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Expr!=null) Expr.accept(visitor);
        if(OptPrintWidth!=null) OptPrintWidth.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(OptPrintWidth!=null) OptPrintWidth.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(OptPrintWidth!=null) OptPrintWidth.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("StatementPrint(\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(OptPrintWidth!=null)
            buffer.append(OptPrintWidth.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [StatementPrint]");
        return buffer.toString();
    }
}
