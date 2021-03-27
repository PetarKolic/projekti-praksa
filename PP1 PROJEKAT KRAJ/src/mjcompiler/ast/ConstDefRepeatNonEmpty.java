// generated with ast extension for cup
// version 0.8
// 14/0/2021 14:29:24


package mjcompiler.ast;

public class ConstDefRepeatNonEmpty extends ConstDefRepeat {

    private ConstDefRepeat ConstDefRepeat;
    private String constName;
    private ConstValue ConstValue;

    public ConstDefRepeatNonEmpty (ConstDefRepeat ConstDefRepeat, String constName, ConstValue ConstValue) {
        this.ConstDefRepeat=ConstDefRepeat;
        if(ConstDefRepeat!=null) ConstDefRepeat.setParent(this);
        this.constName=constName;
        this.ConstValue=ConstValue;
        if(ConstValue!=null) ConstValue.setParent(this);
    }

    public ConstDefRepeat getConstDefRepeat() {
        return ConstDefRepeat;
    }

    public void setConstDefRepeat(ConstDefRepeat ConstDefRepeat) {
        this.ConstDefRepeat=ConstDefRepeat;
    }

    public String getConstName() {
        return constName;
    }

    public void setConstName(String constName) {
        this.constName=constName;
    }

    public ConstValue getConstValue() {
        return ConstValue;
    }

    public void setConstValue(ConstValue ConstValue) {
        this.ConstValue=ConstValue;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstDefRepeat!=null) ConstDefRepeat.accept(visitor);
        if(ConstValue!=null) ConstValue.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstDefRepeat!=null) ConstDefRepeat.traverseTopDown(visitor);
        if(ConstValue!=null) ConstValue.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstDefRepeat!=null) ConstDefRepeat.traverseBottomUp(visitor);
        if(ConstValue!=null) ConstValue.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstDefRepeatNonEmpty(\n");

        if(ConstDefRepeat!=null)
            buffer.append(ConstDefRepeat.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+constName);
        buffer.append("\n");

        if(ConstValue!=null)
            buffer.append(ConstValue.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstDefRepeatNonEmpty]");
        return buffer.toString();
    }
}
