// generated with ast extension for cup
// version 0.8
// 14/0/2021 14:29:24


package mjcompiler.ast;

public class VarDefRepeatNonEmpty extends VarDefRepeat {

    private VarDefRepeat VarDefRepeat;
    private String varName;
    private OptArray OptArray;

    public VarDefRepeatNonEmpty (VarDefRepeat VarDefRepeat, String varName, OptArray OptArray) {
        this.VarDefRepeat=VarDefRepeat;
        if(VarDefRepeat!=null) VarDefRepeat.setParent(this);
        this.varName=varName;
        this.OptArray=OptArray;
        if(OptArray!=null) OptArray.setParent(this);
    }

    public VarDefRepeat getVarDefRepeat() {
        return VarDefRepeat;
    }

    public void setVarDefRepeat(VarDefRepeat VarDefRepeat) {
        this.VarDefRepeat=VarDefRepeat;
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

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarDefRepeat!=null) VarDefRepeat.accept(visitor);
        if(OptArray!=null) OptArray.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarDefRepeat!=null) VarDefRepeat.traverseTopDown(visitor);
        if(OptArray!=null) OptArray.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarDefRepeat!=null) VarDefRepeat.traverseBottomUp(visitor);
        if(OptArray!=null) OptArray.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDefRepeatNonEmpty(\n");

        if(VarDefRepeat!=null)
            buffer.append(VarDefRepeat.toString("  "+tab));
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

        buffer.append(tab);
        buffer.append(") [VarDefRepeatNonEmpty]");
        return buffer.toString();
    }
}
