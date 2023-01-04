package cm.milkyway.lang.maths.shapes;

import cm.milkyway.lang.maths.VecMth;

public class Circle implements Shape
{

    double x, y, s;
    double degree;

    public boolean contains(double xi, double yi)
    {
        return VecMth.noSqrtCheckBound(x, y, xi, yi, s / 2);
    }

    public boolean interacts(Circle cir)
    {
        return VecMth.noSqrtCheckBound(x, y, cir.x, cir.y, (cir.s + s) / 2);
    }

    public double x()
    {
        return x;
    }

    public double y()
    {
        return y;
    }

    public double w()
    {
        return 0;
    }

    public double h()
    {
        return 0;
    }

    public void setX(double xi)
    {
        x = xi;
    }

    public void setY(double yi)
    {
        y = yi;
    }

    public void setWidth(double wi)
    {
        s = wi;
    }

    public void setHeight(double hi)
    {
        s = hi;
    }

    public void setRotation(double v)
    {
        degree = v;
    }

    public double degree()
    {
        return degree;
    }

}
