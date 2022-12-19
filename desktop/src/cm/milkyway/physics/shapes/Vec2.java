package cm.milkyway.physics.shapes;

public class Vec2
{

    double x, y;

    public static Vec2 of(double x, double y)
    {
        return new Vec2().set(x, y);
    }

    public static Vec2 normal()
    {
        return new Vec2();
    }

    public Vec2 set(double ix, double iy)
    {
        x = ix;
        y = iy;
        return this;
    }

    public Vec2 trs(double mx, double my)
    {
        x += mx;
        y += my;
        return this;
    }

    public Vec2 mul(double mx, double my)
    {
        x *= mx;
        y *= my;
        return this;
    }

    public Vec2 sub(double mx, double my)
    {
        x /= mx;
        y /= my;
        return this;
    }

    public double x()
    {
        return x;
    }

    public double y()
    {
        return y;
    }

}
