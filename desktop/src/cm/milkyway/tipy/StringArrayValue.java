package cm.milkyway.tipy;

public class StringArrayValue extends TipyValue
{

    String[] v;

    public StringArrayValue(String[] arr)
    {
        v = arr;
    }

    public Type type()
    {
        return Type.ARRAY_STRING;
    }

    public String[] getArrayString()
    {
        return v;
    }

}
