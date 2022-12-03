package cm.milkywaylib.base;

import cm.milkywaytool.maths.VecMth;

public class VecInfo
{

    double degree;
    double speed;

    public static VecInfo of(double speed, double degree)
    {
        return new VecInfo().setSpeed(speed).setDegree(degree);
    }

    public static VecInfo degree(double degree)
    {
        return of(0, degree);
    }

    public static VecInfo speed(double speed)
    {
        return of(speed, 0);
    }

    public static VecInfo normal()
    {
        return new VecInfo();
    }

    public VecInfo vec(double sp, double dr)
    {
        setSpeed(sp);
        setDegree(dr);
        return this;
    }

    public VecInfo mvVec(double msp, double mdr)
    {
        mvSpeed(msp);
        mvDegree(mdr);
        return this;
    }

    public VecInfo mulVec(double sp, double dr)
    {
        mulSpeed(sp);
        mulDegree(dr);
        return this;
    }

    public VecInfo mvSpeed(double m)
    {
        speed += m;
        return this;
    }

    public VecInfo setSpeed(double s)
    {
        speed = s;
        return this;
    }

    public VecInfo mulSpeed(double s)
    {
        speed *= s;
        return this;
    }

    public VecInfo mvDegree(double m)
    {
        degree += m;
        return this;
    }

    public VecInfo setDegree(double s)
    {
        degree = s;
        return this;
    }

    public VecInfo mulDegree(double s)
    {
        degree *= s;
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
        return VecMth.vectorX(x, speed, degree);
    }

    public double applyY(double y)
    {
        return VecMth.vectorY(y, speed, degree);
    }

    public VecInfo copy(VecInfo vec)
    {
        vec(vec.speed(), vec.degree());
        return this;
    }

}
