package cm.milkywayx.storfesx.single;

import cm.milkywayx.storfesx.Fes;

public abstract class SingleFes implements Fes
{

    public double asDouble()
    {
        return 0;
    }

    public abstract String asString();

    public boolean asBoolean()
    {
        return false;
    }

    public int asInt()
    {
        return (int) asDouble();
    }

}
