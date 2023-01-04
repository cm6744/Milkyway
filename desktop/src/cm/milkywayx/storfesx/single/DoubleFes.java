package cm.milkywayx.storfesx.single;

import cm.milkyway.lang.text.Data;

public class DoubleFes extends SingleFes
{

    double v;

    public DoubleFes(double i)
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
