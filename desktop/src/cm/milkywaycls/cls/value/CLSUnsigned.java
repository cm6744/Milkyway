package cm.milkywaycls.cls.value;

public class CLSUnsigned extends CLSValue
{

    String v;

    public CLSUnsigned(String d)
    {
        v = d;
    }

    public Type type()
    {
        return Type.KEYWORD;
    }

    public String vString()
    {
        return v;
    }

    public CLSValue copy()
    {
        return new CLSUnsigned(v);
    }

}
