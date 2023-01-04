package cm.milkywayx.physicsx.arcle;

import cm.milkyway.lang.container.list.List;
import cm.milkyway.lang.maths.shapes.Rect;

public class Dimension
{

    double step;
    List<Quality> qualities = new List<>();
    double gravity;

    public void move(Dimension dim2)
    {
        dim2.qualities.addAll(qualities);
        qualities.clear();
    }

    public double gravity()
    {
        return gravity;
    }

    public List<Quality> qualities()
    {
        return qualities;
    }

    public Dimension(double stp, double gravityAccel)
    {
        step = stp;
        gravity = gravityAccel;
    }

    public void add(Quality q)
    {
        qualities.add(q);
    }

    public void dim(double x, double y, double w, double h)
    {
        add(new LockedQuality(Rect.offset(x, y, w, h)));
    }

}
