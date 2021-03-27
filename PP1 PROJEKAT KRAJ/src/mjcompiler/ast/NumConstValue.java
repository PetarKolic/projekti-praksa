// generated with ast extension for cup
// version 0.8
// 14/0/2021 14:29:24


package mjcompiler.ast;

public class NumConstValue extends ConstValue {

    private Integer numConst;

    public NumConstValue (Integer numConst) {
        this.numConst=numConst;
    }

    public Integer getNumConst() {
        return numConst;
    }

    public void setNumConst(Integer numConst) {
        this.numConst=numConst;
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
        buffer.append("NumConstValue(\n");

        buffer.append(" "+tab+numConst);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [NumConstValue]");
        return buffer.toString();
    }
}
