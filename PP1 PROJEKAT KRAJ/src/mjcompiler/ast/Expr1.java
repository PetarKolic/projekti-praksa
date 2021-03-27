// generated with ast extension for cup
// version 0.8
// 14/0/2021 14:29:24


package mjcompiler.ast;

public class Expr1 implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Struct struct = null;

    private NegTerm NegTerm;
    private AddopTermRepeat AddopTermRepeat;

    public Expr1 (NegTerm NegTerm, AddopTermRepeat AddopTermRepeat) {
        this.NegTerm=NegTerm;
        if(NegTerm!=null) NegTerm.setParent(this);
        this.AddopTermRepeat=AddopTermRepeat;
        if(AddopTermRepeat!=null) AddopTermRepeat.setParent(this);
    }

    public NegTerm getNegTerm() {
        return NegTerm;
    }

    public void setNegTerm(NegTerm NegTerm) {
        this.NegTerm=NegTerm;
    }

    public AddopTermRepeat getAddopTermRepeat() {
        return AddopTermRepeat;
    }

    public void setAddopTermRepeat(AddopTermRepeat AddopTermRepeat) {
        this.AddopTermRepeat=AddopTermRepeat;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(NegTerm!=null) NegTerm.accept(visitor);
        if(AddopTermRepeat!=null) AddopTermRepeat.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(NegTerm!=null) NegTerm.traverseTopDown(visitor);
        if(AddopTermRepeat!=null) AddopTermRepeat.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(NegTerm!=null) NegTerm.traverseBottomUp(visitor);
        if(AddopTermRepeat!=null) AddopTermRepeat.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Expr1(\n");

        if(NegTerm!=null)
            buffer.append(NegTerm.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(AddopTermRepeat!=null)
            buffer.append(AddopTermRepeat.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Expr1]");
        return buffer.toString();
    }
}
