package cm.milkyway.tipy;

public class StringValue extends TipyValue
{

    String v;

    public StringValue(String  i)
    {
        v = i;
    }

    public Type type()
    {
        return Type.STRING;
    }

    public String getString()
    {
        return v;
    }

}
