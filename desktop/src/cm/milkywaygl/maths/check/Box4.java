package cm.milkywaygl.maths.check;

import cm.milkywaygl.Platform;
import cm.milkywaygl.maths.VMaths;

public abstract class Box4
{

    //STATIC METHODS

    double x, y, width, height;

    public static Box4 normal()
    {
        return new BoxInset();
    }

    public static Box4 inset(double w, double h)
    {
        return new BoxInset().setSize(w, h);
    }

    public static Box4 inset(double x, double y, double w, double h)
    {
        return inset(w, h).loc(x, y);
    }

    public static Box4 offset(double w, double h)
    {
        return new BoxOffset().setSize(w, h);
    }

    public static Box4 offset(double x, double y, double w, double h)
    {
        return offset(w, h).loc(x, y);
    }

    //END REGION

    static boolean noSqrtCheckBound(double x1, double y1, double x2, double y2, double distance)
    {
        double xDistance = x1 - x2;
        double yDistance = y1 - y2;

        return xDistance * xDistance + yDistance * yDistance <= distance * distance;
    }

    public Box4 setSize(double w, double h)
    {
        setWidth(w);
        setHeight(h);
        return this;
    }

    public Box4 mvSize(double mw, double mh)
    {
        mvWidth(mw);
        mvHeight(mh);
        return this;
    }

    public Box4 mvWidth(double m)
    {
        width += m;
        return this;
    }

    public Box4 setWidth(double v)
    {
        width = v;
        return this;
    }

    public Box4 mvHeight(double m)
    {
        height += m;
        return this;
    }

    public Box4 setHeight(double v)
    {
        height = v;
        return this;
    }

    public Box4 mvX(double mx)
    {
        x += mx;
        return this;
    }

    public Box4 mvY(double my)
    {
        y += my;
        return this;
    }

    public Box4 setX(double v)
    {
        x = v;
        return this;
    }

    public Box4 setY(double v)
    {
        y = v;
        return this;
    }

    public Box4 mvLoc(double mx, double my)
    {
        mvX(mx);
        mvY(my);
        return this;
    }

    public Box4 loc(double x, double y)
    {
        setX(x);
        setY(y);
        return this;
    }

    public abstract double x();

    public abstract double y();

    public double xc()
    {
        return x() - width / 2;
    }

    public double yc()
    {
        return y() - height / 2;
    }

    public double xc2()
    {
        return x() + width / 2;
    }

    public double yc2()
    {
        return y() + height / 2;
    }

    public double width()
    {
        return width;
    }

    public double height()
    {
        return height;
    }

    public boolean boundPoint(double x, double y)
    {
        return x >= xc() && y >= yc() && x <= xc2() && y <= yc2();
    }

    public boolean boundAsCir(Box4 box4)
    {
        if(!boundAsRct(box4)) {
            return false;
        }
        return noSqrtCheckBound(x, y, box4.x, box4.y, (width / 2 + box4.width / 2));
    }

    public boolean boundAsRct(Box4 box4)
    {
        double width1 = width;
        double height1 = height;
        double width2 = box4.width;
        double height2 = box4.height;
        double x1 = xc();
        double y1 = yc();
        double x2 = box4.xc();
        double y2 = box4.yc();

        width2 += x2;
        height2 += y2;
        width1 += x1;
        height1 += y1;

        return ((width2 < x2 || width2 > x1) && (height2 < y2 || height2 > y1) && (width1 < x1 || width1 > x2) && (height1 < y1 || height1 > y2));
    }

    public Box4 rotate(double cex, double cey, double degree)
    {
        double dist = VMaths.distanceBetweenAB(x(), y(), cex, cey);
        double deg = VMaths.degreeBetweenAB(x(), y(), cex, cey);
        deg += degree;
        loc(VMaths.vectorX(cex, dist, deg), VMaths.vectorY(cey, dist, deg));
        return this;
    }

    public Box4 copy(Box4 box)
    {
        loc(box.x(), box.y());
        setSize(box.width(), box.height());
        return this;
    }

    private static class BoxOffset extends Box4
    {

        public double x()
        {
            return x + width / 2;
        }

        public double y()
        {
            return y + height / 2;
        }

    }

    private static class BoxInset extends Box4
    {

        public double x()
        {
            return x;
        }

        public double y()
        {
            return y;
        }

    }

}
