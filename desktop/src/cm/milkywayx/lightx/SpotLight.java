package cm.milkywayx.lightx;

import cm.milkyway.lang.maths.Mth;
import cm.milkyway.lang.maths.VecMth;

public class SpotLight extends Light
{

    public double luminous(double x, double y)
    {
        double dist = VecMth.distanceBetweenAB(pos.x(), pos.y(), x, y);
        return (intensity / Mth.max(dist, intensity));
    }

}
