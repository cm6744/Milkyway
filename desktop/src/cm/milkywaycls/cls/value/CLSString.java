package cm.milkywaycls.cls.value;

public class CLSString extends CLSValue
{

    String v;

    public CLSString(String d)
    {
        v = d;
    }

    public Type type()
    {
        return Type.STRING;
    }

    public String vString()
    {
        return v;
    }

    public void set(String d)
    {
        v = d;
    }

    public CLSValue copy()
    {
        return new CLSString(v);
    }

}
