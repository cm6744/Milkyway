package cm.milkyway.tipy;

public class BooleanValue extends TipyValue
{

    boolean v;

    public BooleanValue(boolean i)
    {
        v = i;
    }

    public Type type()
    {
        return Type.BOOL;
    }

    public boolean getBoolean()
    {
        return v;
    }

}
