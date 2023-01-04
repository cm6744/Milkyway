package cm.milkyway.tipy;

public abstract class TipyValue
{

    public enum Type
    {
        BOOL,
        INT,
        DOUBLE,
        STRING,
        ARRAY_DOUBLE,
        ARRAY_INT,
        ARRAY_BOOL,
        ARRAY_STRING
    }

    public abstract Type type();

    public boolean getBoolean()
    {
        return false;
    }

    public String getString()
    {
        return null;
    }

    public double getDouble()
    {
        return 0;
    }

    public int getInt()
    {
        return 0;
    }

    public double[] getArrayDouble()
    {
        return null;
    }

    public int[] getArrayInt()
    {
        return null;
    }

    public boolean[] getArrayBoolean()
    {
        return null;
    }

    public String[] getArrayString()
    {
        return null;
    }

}
