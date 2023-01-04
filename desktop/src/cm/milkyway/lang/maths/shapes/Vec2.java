package cm.milkyway.lang.maths.shapes;

import cm.milkyway.lang.maths.Mth;

import java.io.Serializable;

public class Vec2 implements Serializable
{

    double x, y;

    public static Vec2 xy(double x, double y)
    {
        return new Vec2(x, y);
    }

    public static Vec2 of(double ln, double dg)
    {
        Vec2 v = normal();
        v.setRot(ln, dg);
        return v;
    }

    public static Vec2 normal()
    {
        return new Vec2(0, 0);
    }

    Vec2(double i, double j)
    {
        x = i;
        y = j;
    }

    public double radian()
    {
        return Math.atan2(y, x);
    }

    public double degree()
    {
        return Mth.toDegree(radian());
    }

    public double ln()
    {
        return Mth.sqrt(lnPow());
    }

    public double lnPow()
    {
        return x * x + y * y;
    }

    public void setLn(double v)
    {
        setRot(v, degree());
    }

    public void setRot(double ln, double deg)
    {
        x = Mth.cos(Mth.toRadians(deg)) * ln;
        y = Mth.sin(Mth.toRadians(deg)) * ln;
    }

    public void refresh()
    {
        x = y = 0;
    }

    public void set(double ix, double iy)
    {
        x = ix;
        y = iy;
    }

    public void add(double mx, double my)
    {
        x += mx;
        y += my;
    }

    public void sub(double mx, double my)
    {
        x -= mx;
        y -= my;
    }

    public void mul(double mx, double my)
    {
        x *= mx;
        y *= my;
    }

    public void div(double mx, double my)
    {
        x /= mx;
        y /= my;
    }

    public void set(Vec2 v)
    {
        set(v.x, v.y);
    }

    public void add(Vec2 v)
    {
        add(v.x, v.y);
    }

    public void sub(Vec2 v)
    {
        sub(v.x, v.y);
    }

    public void mul(Vec2 v)
    {
        mul(v.x, v.y);
    }

    public void div(Vec2 v)
    {
        div(v.x, v.y);
    }

    public double x()
    {
        return x;
    }

    public double y()
    {
        return y;
    }

    public String toString()
    {
        return "Vec2{" +
                "degree=" + degree() +
                ", length=" + ln() +
                '}';
    }

}
