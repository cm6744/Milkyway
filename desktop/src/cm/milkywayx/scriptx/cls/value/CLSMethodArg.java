package cm.milkywayx.scriptx.cls.value;

import cm.milkywayx.scriptx.cls.compile.CLSState;
import cm.milkyway.lang.container.List;

public class CLSMethodArg
{

    List<CLSValue> values;
    CLSState cls;

    public CLSMethodArg(List<CLSValue> lst, CLSState cls)
    {
        values = lst;
    }

    public CLSState state()
    {
        return cls;
    }

    public CLSValue get(int i)
    {
        return values.get(i);
    }

    public int vInt(int i)
    {
        return (int) vDouble(i);
    }

    public double vDouble(int i)
    {
        return values.get(i).vDouble();
    }

    public boolean vBool(int i)
    {
        return values.get(i).vBool();
    }

    public String vString(int i)
    {
        return values.get(i).vString();
    }

}
