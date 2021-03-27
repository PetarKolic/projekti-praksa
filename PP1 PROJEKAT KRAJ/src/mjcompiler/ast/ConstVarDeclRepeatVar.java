// generated with ast extension for cup
// version 0.8
// 14/0/2021 14:29:24


package mjcompiler.ast;

public class ConstVarDeclRepeatVar extends ConstVarDeclRepeat {

    private ConstVarDeclRepeat ConstVarDeclRepeat;
    private VarDecl VarDecl;

    public ConstVarDeclRepeatVar (ConstVarDeclRepeat ConstVarDeclRepeat, VarDecl VarDecl) {
        this.ConstVarDeclRepeat=ConstVarDeclRepeat;
        if(ConstVarDeclRepeat!=null) ConstVarDeclRepeat.setParent(this);
        this.VarDecl=VarDecl;
        if(VarDecl!=null) VarDecl.setParent(this);
    }

    public ConstVarDeclRepeat getConstVarDeclRepeat() {
        return ConstVarDeclRepeat;
    }

    public void setConstVarDeclRepeat(ConstVarDeclRepeat ConstVarDeclRepeat) {
        this.ConstVarDeclRepeat=ConstVarDeclRepeat;
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
        if(ConstVarDeclRepeat!=null) ConstVarDeclRepeat.accept(visitor);
        if(VarDecl!=null) VarDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstVarDeclRepeat!=null) ConstVarDeclRepeat.traverseTopDown(visitor);
        if(VarDecl!=null) VarDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstVarDeclRepeat!=null) ConstVarDeclRepeat.traverseBottomUp(visitor);
        if(VarDecl!=null) VarDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstVarDeclRepeatVar(\n");

        if(ConstVarDeclRepeat!=null)
            buffer.append(ConstVarDeclRepeat.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDecl!=null)
            buffer.append(VarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstVarDeclRepeatVar]");
        return buffer.toString();
    }
}
