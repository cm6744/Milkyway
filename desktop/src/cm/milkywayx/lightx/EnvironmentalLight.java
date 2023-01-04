package cm.milkywayx.lightx;

import cm.milkyway.lang.maths.Mth;

public class EnvironmentalLight extends Light
{

    double goal;
    boolean trueUp;

    public void updateSmooth()
    {
        if(goal > intensity && trueUp) {
            intensity = Mth.min(goal, intensity + 0.01);
        }
        else if(goal < intensity && !trueUp) {
            intensity = Mth.max(goal, intensity - 0.01);
        }
    }

    public void smoothTo(double ind)
    {
        goal = ind;
        trueUp = goal > intensity;
    }

    public double luminous(double x, double y)
    {
        return intensity;
    }

}
