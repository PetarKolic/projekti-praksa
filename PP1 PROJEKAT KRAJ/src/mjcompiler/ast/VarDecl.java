// generated with ast extension for cup
// version 0.8
// 14/0/2021 14:29:24


package mjcompiler.ast;

public class VarDecl implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private Type Type;
    private String varName;
    private OptArray OptArray;
    private VarDefRepeat VarDefRepeat;

    public VarDecl (Type Type, String varName, OptArray OptArray, VarDefRepeat VarDefRepeat) {
        this.Type=Type;
        if(Type!=null) Type.setParent(this);
        this.varName=varName;
        this.OptArray=OptArray;
        if(OptArray!=null) OptArray.setParent(this);
        this.VarDefRepeat=VarDefRepeat;
        if(VarDefRepeat!=null) VarDefRepeat.setParent(this);
    }

    public Type getType() {
        return Type;
    }

    public void setType(Type Type) {
        this.Type=Type;
    }

    public String getVarName() {
        return varName;
    }

    public void setVarName(String varName) {
        this.varName=varName;
    }

    public OptArray getOptArray() {
        return OptArray;
    }

    public void setOptArray(OptArray OptArray) {
        this.OptArray=OptArray;
    }

    public VarDefRepeat getVarDefRepeat() {
        return VarDefRepeat;
    }

    public void setVarDefRepeat(VarDefRepeat VarDefRepeat) {
        this.VarDefRepeat=VarDefRepeat;
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
        if(Type!=null) Type.accept(visitor);
        if(OptArray!=null) OptArray.accept(visitor);
        if(VarDefRepeat!=null) VarDefRepeat.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Type!=null) Type.traverseTopDown(visitor);
        if(OptArray!=null) OptArray.traverseTopDown(visitor);
        if(VarDefRepeat!=null) VarDefRepeat.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Type!=null) Type.traverseBottomUp(visitor);
        if(OptArray!=null) OptArray.traverseBottomUp(visitor);
        if(VarDefRepeat!=null) VarDefRepeat.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDecl(\n");

        if(Type!=null)
            buffer.append(Type.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+varName);
        buffer.append("\n");

        if(OptArray!=null)
            buffer.append(OptArray.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDefRepeat!=null)
            buffer.append(VarDefRepeat.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDecl]");
        return buffer.toString();
    }
}
