package cm.milkyway.tipy;

public class IntValue extends TipyValue
{

    int v;

    public IntValue(int i)
    {
        v = i;
    }

    public Type type()
    {
        return Type.INT;
    }

    public int getInt()
    {
        return v;
    }

    public double getDouble()
    {
        return v;
    }

}
