package cm.milkywayx.storfesx.single;

import cm.milkyway.lang.text.Data;

public class IntFes extends SingleFes
{

    int v;

    public IntFes(int i)
    {
        v = i;
    }

    public double asDouble()
    {
        return v;
    }

    public String asString()
    {
        return Data.toString(v);
    }

}
