package cm.milkywaytool.physics;

import cm.milkywaytool.maths.VecMth;

public class Physics
{

    public static boolean boundPoint(Rect b, double x, double y)
    {
        return x >= b.xc() && y >= b.yc() && x <= b.xc2() && y <= b.yc2();
    }

    public static boolean boundAsCir(Rect b1, Rect b2)
    {
        if(!boundAsRct(b1, b2)) {
            return false;
        }
        return VecMth.noSqrtCheckBound(b1.x(), b1.y(), b2.x(), b2.y(), (b1.w() + b2.w()) / 2);
    }

    public static boolean boundAsRct(Rect b1, Rect b2)
    {
        double width1 = b1.w();
        double height1 = b1.h();
        double width2 = b2.w();
        double height2 = b2.h();
        double x1 = b1.xc();
        double y1 = b1.yc();
        double x2 = b2.xc();
        double y2 = b2.yc();

        width2 += x2;
        height2 += y2;
        width1 += x1;
        height1 += y1;

        return ((width2 < x2 || width2 > x1) && (height2 < y2 || height2 > y1)
                && (width1 < x1 || width1 > x2) && (height1 < y1 || height1 > y2));
    }

}
