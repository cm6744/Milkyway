package cm.milkyway.lang.maths.shapes;

public class Flat
{

    //higher-efficient
    public double x, y, w, h;

    public static Flat normal()
    {
        return new Flat();
    }

    public static Flat offset(double w, double h)
    {
        return normal().resize(w, h);
    }

    public static Flat offset(double x, double y, double w, double h)
    {
        return offset(w, h).loc(x, y);
    }

    public Flat resize(double wi, double hi)
    {
        w = wi;
        h = hi;
        return this;
    }

    public Flat loc(double xi, double yi)
    {
        x = xi;
        y = yi;
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

    public double width()
    {
        return w;
    }

    public double height()
    {
        return h;
    }

    public Flat copy(Flat flat)
    {
        loc(flat.x(), flat.y());
        resize(flat.width(), flat.height());
        return this;
    }

}
