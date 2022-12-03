package cm.milkywaytool.maths;

import cm.milkywaytool.physics.Rect;
import cm.milkywaytool.physics.Vec2;

public class VecMth
{

    public static double opposite(double degree)
    {
        return 180 + degree;
    }

    public static double vectorX(double x, double speed, double degree)
    {
        return x + speed * Mth.cos(Mth.toRadians(degree));
    }

    public static double vectorY(double y, double speed, double degree)
    {
        return y + speed * Mth.sin(Mth.toRadians(degree));
    }

    public static double degreeBetweenAB(double x1, double y1, double x2, double y2)
    {
        return Mth.toDegree(Mth.tan2(y1 - y2, x1 - x2));
    }

    public static double degreeBetweenAB(Rect b1, Rect b2)
    {
        return degreeBetweenAB(b1.x(), b1.y(), b2.x(), b2.y());
    }

    public static double distanceBetweenAB(double x1, double y1, double x2, double y2)
    {
        double xDistance = x1 - x2;
        double yDistance = y1 - y2;

        return Mth.sqrt(xDistance * xDistance + yDistance * yDistance);
    }

    public static double distanceBetweenAB(Rect b1, Rect b2)
    {
        return distanceBetweenAB(b1.x(), b1.y(), b2.x(), b2.y());
    }

    public static boolean noSqrtCheckBound(double x1, double y1, double x2, double y2, double distance)
    {
        double xDistance = x1 - x2;
        double yDistance = y1 - y2;

        return xDistance * xDistance + yDistance * yDistance <= distance * distance;
    }

    public static double dot(Vec2 v1, Vec2 v2)
    {
        return Mth.abs(v1.x() * v2.x() + v1.y() * v2.y());
    }

}
