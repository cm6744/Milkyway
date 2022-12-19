package cm.milkywayx.scriptx.cls.value;

import cm.milkywayx.scriptx.cls.method.Method;

public class CLSReferenceMethod extends CLSValue
{

    Method<?> v;
    CLSMethodArg args;

    public CLSReferenceMethod(Method<?> d, CLSMethodArg values)
    {
        v = d;
        args = values;
    }

    public CLSValue get()
    {
        v.invoke(args);
        return v.retVal();
    }

    public Type type()
    {
        return v.retVal().type();
    }

    public double vDouble()
    {
        v.invoke(args);
        return v.retVal().vDouble();
    }

    public boolean vBool()
    {
        v.invoke(args);
        return v.retVal().vBool();
    }

    public String vString()
    {
        v.invoke(args);
        return v.retVal().vString();
    }

    public CLSValue copy()
    {
        return new CLSReferenceMethod(v, args);
    }

}
