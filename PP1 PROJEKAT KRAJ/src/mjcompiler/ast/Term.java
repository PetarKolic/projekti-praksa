// generated with ast extension for cup
// version 0.8
// 14/0/2021 14:29:24


package mjcompiler.ast;

public class Term implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.etf.pp1.symboltable.concepts.Struct struct = null;

    private Factor Factor;
    private MulopFactorRepeat MulopFactorRepeat;

    public Term (Factor Factor, MulopFactorRepeat MulopFactorRepeat) {
        this.Factor=Factor;
        if(Factor!=null) Factor.setParent(this);
        this.MulopFactorRepeat=MulopFactorRepeat;
        if(MulopFactorRepeat!=null) MulopFactorRepeat.setParent(this);
    }

    public Factor getFactor() {
        return Factor;
    }

    public void setFactor(Factor Factor) {
        this.Factor=Factor;
    }

    public MulopFactorRepeat getMulopFactorRepeat() {
        return MulopFactorRepeat;
    }

    public void setMulopFactorRepeat(MulopFactorRepeat MulopFactorRepeat) {
        this.MulopFactorRepeat=MulopFactorRepeat;
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
        if(Factor!=null) Factor.accept(visitor);
        if(MulopFactorRepeat!=null) MulopFactorRepeat.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Factor!=null) Factor.traverseTopDown(visitor);
        if(MulopFactorRepeat!=null) MulopFactorRepeat.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Factor!=null) Factor.traverseBottomUp(visitor);
        if(MulopFactorRepeat!=null) MulopFactorRepeat.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("Term(\n");

        if(Factor!=null)
            buffer.append(Factor.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MulopFactorRepeat!=null)
            buffer.append(MulopFactorRepeat.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [Term]");
        return buffer.toString();
    }
}
