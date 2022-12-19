package cm.milkywayx.scriptx.cls.value;

import cm.milkyway.lang.text.Data;

public class CLSBool extends CLSValue
{

    boolean v;

    public CLSBool(boolean d)
    {
        v = d;
    }

    public Type type()
    {
        return Type.BOOLEAN;
    }

    public boolean vBool()
    {
        return v;
    }

    public String vString()
    {
        return Data.toString(v);
    }

    public void set(boolean d)
    {
        v = d;
    }

    public CLSValue copy()
    {
        return new CLSBool(v);
    }

}
