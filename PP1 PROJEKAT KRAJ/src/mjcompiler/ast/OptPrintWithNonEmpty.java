// generated with ast extension for cup
// version 0.8
// 14/0/2021 14:29:24


package mjcompiler.ast;

public class OptPrintWithNonEmpty extends OptPrintWidth {

    private Integer num;

    public OptPrintWithNonEmpty (Integer num) {
        this.num=num;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num=num;
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
        buffer.append("OptPrintWithNonEmpty(\n");

        buffer.append(" "+tab+num);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [OptPrintWithNonEmpty]");
        return buffer.toString();
    }
}
