// generated with ast extension for cup
// version 0.8
// 14/0/2021 14:29:24


package mjcompiler.ast;

public class ConstDefRepeatEmpty extends ConstDefRepeat {

    public ConstDefRepeatEmpty () {
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
        buffer.append("ConstDefRepeatEmpty(\n");

        buffer.append(tab);
        buffer.append(") [ConstDefRepeatEmpty]");
        return buffer.toString();
    }
}
