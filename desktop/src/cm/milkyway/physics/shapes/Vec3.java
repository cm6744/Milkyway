package cm.milkyway.physics.shapes;

public class Vec3
{

    double x, y, z;

    public static Vec3 of(double x, double y, double z)
    {
        return new Vec3().set(x, y, z);
    }

    public static Vec3 normal()
    {
        return new Vec3();
    }

    public Vec3 set(double ix, double iy, double iz)
    {
        x = ix;
        y = iy;
        z = iz;
        return this;
    }

    public Vec3 trs(double mx, double my, double mz)
    {
        x += mx;
        y += my;
        z += mz;
        return this;
    }

    public Vec3 mul(double mx, double my, double mz)
    {
        x *= mx;
        y *= my;
        z *= mz;
        return this;
    }

    public Vec3 sub(double mx, double my, double mz)
    {
        x /= mx;
        y /= my;
        z /= mz;
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

    public double z()
    {
        return z;
    }

}
