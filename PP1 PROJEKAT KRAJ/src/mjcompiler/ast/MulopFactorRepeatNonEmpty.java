// generated with ast extension for cup
// version 0.8
// 14/0/2021 14:29:24


package mjcompiler.ast;

public class MulopFactorRepeatNonEmpty extends MulopFactorRepeat {

    private MulopFactorRepeat MulopFactorRepeat;
    private Mulop Mulop;
    private Factor Factor;

    public MulopFactorRepeatNonEmpty (MulopFactorRepeat MulopFactorRepeat, Mulop Mulop, Factor Factor) {
        this.MulopFactorRepeat=MulopFactorRepeat;
        if(MulopFactorRepeat!=null) MulopFactorRepeat.setParent(this);
        this.Mulop=Mulop;
        if(Mulop!=null) Mulop.setParent(this);
        this.Factor=Factor;
        if(Factor!=null) Factor.setParent(this);
    }

    public MulopFactorRepeat getMulopFactorRepeat() {
        return MulopFactorRepeat;
    }

    public void setMulopFactorRepeat(MulopFactorRepeat MulopFactorRepeat) {
        this.MulopFactorRepeat=MulopFactorRepeat;
    }

    public Mulop getMulop() {
        return Mulop;
    }

    public void setMulop(Mulop Mulop) {
        this.Mulop=Mulop;
    }

    public Factor getFactor() {
        return Factor;
    }

    public void setFactor(Factor Factor) {
        this.Factor=Factor;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MulopFactorRepeat!=null) MulopFactorRepeat.accept(visitor);
        if(Mulop!=null) Mulop.accept(visitor);
        if(Factor!=null) Factor.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MulopFactorRepeat!=null) MulopFactorRepeat.traverseTopDown(visitor);
        if(Mulop!=null) Mulop.traverseTopDown(visitor);
        if(Factor!=null) Factor.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MulopFactorRepeat!=null) MulopFactorRepeat.traverseBottomUp(visitor);
        if(Mulop!=null) Mulop.traverseBottomUp(visitor);
        if(Factor!=null) Factor.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MulopFactorRepeatNonEmpty(\n");

        if(MulopFactorRepeat!=null)
            buffer.append(MulopFactorRepeat.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Mulop!=null)
            buffer.append(Mulop.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Factor!=null)
            buffer.append(Factor.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MulopFactorRepeatNonEmpty]");
        return buffer.toString();
    }
}
