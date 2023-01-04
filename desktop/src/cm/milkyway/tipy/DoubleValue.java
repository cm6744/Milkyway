package cm.milkyway.tipy;

public class DoubleValue extends TipyValue
{

    double v;

    public DoubleValue(double i)
    {
        v = i;
    }

    public Type type()
    {
        return Type.DOUBLE;
    }

    public double getDouble()
    {
        return v;
    }

    public int getInt()
    {
        return (int) v;
    }

}
