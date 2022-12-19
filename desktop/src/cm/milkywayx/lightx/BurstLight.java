package cm.milkywayx.lightx;

import cm.milkyway.lang.maths.Mth;

public class BurstLight extends Light
{

    public double luminous(double x, double y)
    {
        return (intensity / Mth.max(Mth.abs(x - pos.x()), intensity));
    }

}
