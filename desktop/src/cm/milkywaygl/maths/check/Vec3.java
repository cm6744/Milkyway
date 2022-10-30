package cm.milkywaygl.maths.check;

public class Vec3
{

    double x, y, z;

    public static Vec3 of(double x, double y, double z)
    {
        return new Vec3().vec(x, y, z);
    }

    public static Vec3 normal()
    {
        return new Vec3();
    }

    public Vec3 vec(double ix, double iy, double iz)
    {
        x = ix;
        y = iy;
        z = iz;
        return this;
    }

    public Vec3 mvVec(double mx, double my, double mz)
    {
        x += mx;
        y += my;
        z += mz;
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
