// generated with ast extension for cup
// version 0.8
// 14/0/2021 14:29:24


package mjcompiler.ast;

public class BoolConstValue extends ConstValue {

    private Boolean boolConst;

    public BoolConstValue (Boolean boolConst) {
        this.boolConst=boolConst;
    }

    public Boolean getBoolConst() {
        return boolConst;
    }

    public void setBoolConst(Boolean boolConst) {
        this.boolConst=boolConst;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("BoolConstValue(\n");

        buffer.append(" "+tab+boolConst);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [BoolConstValue]");
        return buffer.toString();
    }
}
