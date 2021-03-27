// generated with ast extension for cup
// version 0.8
// 14/0/2021 14:29:24


package mjcompiler.ast;

public class ConstVarDeclRepeatConst extends ConstVarDeclRepeat {

    private ConstVarDeclRepeat ConstVarDeclRepeat;
    private ConstDecl ConstDecl;

    public ConstVarDeclRepeatConst (ConstVarDeclRepeat ConstVarDeclRepeat, ConstDecl ConstDecl) {
        this.ConstVarDeclRepeat=ConstVarDeclRepeat;
        if(ConstVarDeclRepeat!=null) ConstVarDeclRepeat.setParent(this);
        this.ConstDecl=ConstDecl;
        if(ConstDecl!=null) ConstDecl.setParent(this);
    }

    public ConstVarDeclRepeat getConstVarDeclRepeat() {
        return ConstVarDeclRepeat;
    }

    public void setConstVarDeclRepeat(ConstVarDeclRepeat ConstVarDeclRepeat) {
        this.ConstVarDeclRepeat=ConstVarDeclRepeat;
    }

    public ConstDecl getConstDecl() {
        return ConstDecl;
    }

    public void setConstDecl(ConstDecl ConstDecl) {
        this.ConstDecl=ConstDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstVarDeclRepeat!=null) ConstVarDeclRepeat.accept(visitor);
        if(ConstDecl!=null) ConstDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstVarDeclRepeat!=null) ConstVarDeclRepeat.traverseTopDown(visitor);
        if(ConstDecl!=null) ConstDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstVarDeclRepeat!=null) ConstVarDeclRepeat.traverseBottomUp(visitor);
        if(ConstDecl!=null) ConstDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstVarDeclRepeatConst(\n");

        if(ConstVarDeclRepeat!=null)
            buffer.append(ConstVarDeclRepeat.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstDecl!=null)
            buffer.append(ConstDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstVarDeclRepeatConst]");
        return buffer.toString();
    }
}
