package cm.milkywaygl.maths.check;

import cm.milkywaygl.maths.VMaths;

public class Vec2
{

    double degree;
    double speed;

    public static Vec2 of(double speed, double degree)
    {
        return new Vec2().setSpeed(speed).setDegree(degree);
    }

    public static Vec2 degree(double degree)
    {
        return of(0, degree);
    }

    public static Vec2 speed(double speed)
    {
        return of(speed, 0);
    }

    public static Vec2 normal()
    {
        return new Vec2();
    }

    public Vec2 vec(double sp, double dr)
    {
        setSpeed(sp);
        setDegree(dr);
        return this;
    }

    public Vec2 mvVec(double msp, double mdr)
    {
        mvSpeed(msp);
        mvDegree(mdr);
        return this;
    }

    public Vec2 mvSpeed(double m)
    {
        speed += m;
        return this;
    }

    public Vec2 setSpeed(double s)
    {
        speed = s;
        return this;
    }

    public Vec2 mvDegree(double m)
    {
        degree += m;
        return this;
    }

    public Vec2 setDegree(double s)
    {
        degree = s;
        return this;
    }

    public double speed()
    {
        return speed;
    }

    public double degree()
    {
        return degree;
    }

    public double applyX(double x)
    {
        return VMaths.vectorX(x, speed, degree);
    }

    public double applyY(double x)
    {
        return VMaths.vectorY(x, speed, degree);
    }

}
