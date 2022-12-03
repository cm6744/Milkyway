package cm.milkywaycls.cls.value;

import cm.milkywaytool.text.Data;

public class CLSNum extends CLSValue
{

    double v;

    public CLSNum(double d)
    {
        v = d;
    }

    public Type type()
    {
        return Type.DOUBLE;
    }

    public double vDouble()
    {
        return v;
    }

    public void set(double d)
    {
        v = d;
    }

    public String vString()
    {
        return Data.toString(v);
    }

    public CLSValue copy()
    {
        return new CLSNum(v);
    }

}
