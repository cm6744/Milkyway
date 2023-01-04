package cm.milkywayx.physicsx.arcle;

import cm.milkyway.lang.maths.shapes.Vec2;

public class Force
{

    boolean destroyed;
    Vec2 vec2;
    Quality apply;

    public Force(Vec2 v)
    {
        vec2 = v;
    }

    public Force(double v, double d)
    {
        vec2 = Vec2.of(v, d);
    }

    public void apply(Quality q)
    {
        apply = q;
    }

}
