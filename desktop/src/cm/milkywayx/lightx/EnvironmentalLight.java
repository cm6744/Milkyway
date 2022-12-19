package cm.milkywayx.lightx;

public class EnvironmentalLight extends Light
{

    double goal;
    boolean trueUp;

    public void updateSmooth()
    {
        if(goal > intensity && trueUp) {
            intensity += (goal - intensity) / 120;
        }
        else if(goal < intensity && !trueUp) {
            intensity += (goal - intensity) / 120;
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
