package cm.milkyway.lang.maths.shapes;

import cm.milkyway.lang.maths.Mth;
import cm.milkyway.lang.maths.VecMth;

public class Rect implements Shape
{

    double x, y, w, h;
    double degree;
    Vec2 axisX = Vec2.normal();
    Vec2 axisY = Vec2.normal();
    Vec2 centers = Vec2.normal();

    public static Rect normal()
    {
        return new Rect();
    }

    public static Rect sized(double w, double h)
    {
        Rect r = normal();
        r.resize(w, h);
        return r;
    }

    public static Rect offset(double x, double y, double w, double h)
    {
        Rect r = sized(w, h);
        r.loc(w / 2 + x, h / 2 + y);
        return r;
    }

    public static Rect inset(double x, double y, double w, double h)
    {
        Rect r = sized(w, h);
        r.loc(x, y);
        return r;
    }

    private void calcAxis()
    {
        double rad = Mth.toRadians(degree);
        axisX.set(Mth.cos(rad), Mth.sin(rad));
        axisY.set(-Mth.sin(rad), Mth.cos(rad));
    }

    public boolean contains(double x, double y)
    {
        return x >= xc() && y >= yc() && x <= xc2() && y <= yc2();
    }

    public boolean interacts(Rect rect)
    {
        if(rect.degree == 0 && degree == 0) {
            return noDegreeCheck(rect);
        }

        calcAxis();
        centers.set(x - rect.x, y - rect.y);
        double xx = VecMth.dot(rect.axisX, axisX);
        double yy = VecMth.dot(rect.axisY, axisY);
        double xy = VecMth.dot(rect.axisX, axisY);
        double yx = VecMth.dot(rect.axisY, axisX);
        double w2 = w / 2;
        double h2 = h / 2;
        double rw2 = rect.w / 2;
        double rh2 = rect.h / 2;

        if(VecMth.dot(centers, axisX) > w2 + rw2 * xx + rh2 * yx) {
            return false;
        }
        else if(VecMth.dot(centers, axisY) > h2 + rw2 * xy + rh2 * yy) {
            return false;
        }
        else if(VecMth.dot(centers, rect.axisX) > rw2 + w2 * xx + h2 * xy) {
            return false;
        }
        return !(VecMth.dot(centers, rect.axisY) > rh2 + w2 * yx + h2 * yy);
    }

    private boolean noDegreeCheck(Rect rect)
    {
        return noDegreeCheck(xc(), yc(), w, h, rect.xc(), rect.yc(), rect.w, rect.h);
    }

    private boolean noDegreeCheck(double x1, double y1, double width1, double height1, double x2, double y2, double width2, double height2)
    {
        width2 += x2;
        height2 += y2;
        width1 += x1;
        height1 += y1;

        return ((width2 < x2 || width2 > x1) && (height2 < y2 || height2 > y1)
                && (width1 < x1 || width1 > x2) && (height1 < y1 || height1 > y2));
    }

    public void setRotation(double deg)
    {
        degree = deg;
    }

    public double degree()
    {
        return degree;
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
        w = wi;
    }

    public void setHeight(double hi)
    {
        h = hi;
    }

    public double x()
    {
        return x;
    }

    public double y()
    {
        return y;
    }

    public double xc()
    {
        return x - w / 2;
    }

    public double yc()
    {
        return y - h / 2;
    }

    public double xc2()
    {
        return x + w / 2;
    }

    public double yc2()
    {
        return y + h / 2;
    }

    public double w()
    {
        return w;
    }

    public double h()
    {
        return h;
    }

    public Rect copy(Rect rect)
    {
        loc(rect.x, rect.y);
        resize(rect.w, rect.h);
        degree = rect.degree;
        return rect;
    }

    public String toString()
    {
        return "Rect{" +
                "x=" + x +
                ", y=" + y +
                ", w=" + w +
                ", h=" + h +
                '}';
    }

}
