package cm.milkywayx.storfesx.single;

import cm.milkyway.lang.text.Data;

public class BooleanFes extends SingleFes
{

    boolean v;

    public BooleanFes(boolean i)
    {
        v = i;
    }

    public String asString()
    {
        return Data.toString(v);
    }

    public boolean asBoolean()
    {
        return v;
    }

}
