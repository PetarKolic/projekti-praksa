// generated with ast extension for cup
// version 0.8
// 14/0/2021 14:29:24


package mjcompiler.ast;

public class StatementRepeatNonEmpty extends StatementRepeat {

    private StatementRepeat StatementRepeat;
    private Statement Statement;

    public StatementRepeatNonEmpty (StatementRepeat StatementRepeat, Statement Statement) {
        this.StatementRepeat=StatementRepeat;
        if(StatementRepeat!=null) StatementRepeat.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
    }

    public StatementRepeat getStatementRepeat() {
        return StatementRepeat;
    }

    public void setStatementRepeat(StatementRepeat StatementRepeat) {
        this.StatementRepeat=StatementRepeat;
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(StatementRepeat!=null) StatementRepeat.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(StatementRepeat!=null) StatementRepeat.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(StatementRepeat!=null) StatementRepeat.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("StatementRepeatNonEmpty(\n");

        if(StatementRepeat!=null)
            buffer.append(StatementRepeat.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [StatementRepeatNonEmpty]");
        return buffer.toString();
    }
}
