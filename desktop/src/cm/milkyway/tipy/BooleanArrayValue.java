package cm.milkyway.tipy;

public class BooleanArrayValue extends TipyValue
{

    boolean[] v;

    public BooleanArrayValue(boolean[] arr)
    {
        v = arr;
    }

    public Type type()
    {
        return Type.ARRAY_BOOL;
    }

    public boolean[] getArrayBoolean()
    {
        return v;
    }

}
