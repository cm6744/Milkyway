package cm.milkywayx.lightx;

import cm.milkyway.physics.shapes.Rect;

public abstract class Light
{

    Rect pos = new Rect();
    double intensity;
    boolean removed;

    public Rect pos()
    {
        return pos;
    }

    public boolean isRemoved()
    {
        return removed;
    }

    public void remove()
    {
        removed = true;
    }

    public void cancelRemove()
    {
        removed = false;
    }

    public void setIntensity(double i)
    {
        intensity = i;
    }

    public double intensity()
    {
        return intensity;
    }

    public abstract double luminous(double x, double y);

}
