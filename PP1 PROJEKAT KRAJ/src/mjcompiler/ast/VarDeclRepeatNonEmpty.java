// generated with ast extension for cup
// version 0.8
// 14/0/2021 14:29:24


package mjcompiler.ast;

public class VarDeclRepeatNonEmpty extends VarDeclRepeat {

    private VarDeclRepeat VarDeclRepeat;
    private VarDecl VarDecl;

    public VarDeclRepeatNonEmpty (VarDeclRepeat VarDeclRepeat, VarDecl VarDecl) {
        this.VarDeclRepeat=VarDeclRepeat;
        if(VarDeclRepeat!=null) VarDeclRepeat.setParent(this);
        this.VarDecl=VarDecl;
        if(VarDecl!=null) VarDecl.setParent(this);
    }

    public VarDeclRepeat getVarDeclRepeat() {
        return VarDeclRepeat;
    }

    public void setVarDeclRepeat(VarDeclRepeat VarDeclRepeat) {
        this.VarDeclRepeat=VarDeclRepeat;
    }

    public VarDecl getVarDecl() {
        return VarDecl;
    }

    public void setVarDecl(VarDecl VarDecl) {
        this.VarDecl=VarDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarDeclRepeat!=null) VarDeclRepeat.accept(visitor);
        if(VarDecl!=null) VarDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarDeclRepeat!=null) VarDeclRepeat.traverseTopDown(visitor);
        if(VarDecl!=null) VarDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarDeclRepeat!=null) VarDeclRepeat.traverseBottomUp(visitor);
        if(VarDecl!=null) VarDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclRepeatNonEmpty(\n");

        if(VarDeclRepeat!=null)
            buffer.append(VarDeclRepeat.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDecl!=null)
            buffer.append(VarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclRepeatNonEmpty]");
        return buffer.toString();
    }
}
