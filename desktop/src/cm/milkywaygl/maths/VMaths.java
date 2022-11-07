package cm.milkywaygl.maths;

import cm.milkywaygl.maths.check.Box4;

public class VMaths
{

    public static double opposite(double degree)
    {
        return 180 + degree;
    }

    public static double vectorX(double x, double speed, double degree)
    {
        return x + speed * Math.cos(Maths.toRadians(degree));
    }

    public static double vectorY(double y, double speed, double degree)
    {
        return y + speed * Math.sin(Maths.toRadians(degree));
    }

    public static double degreeBetweenAB(double x1, double y1, double x2, double y2)
    {
        return Maths.toDegree(Math.atan2(y1 - y2, x1 - x2));
    }

    public static double degreeBetweenAB(Box4 b1, Box4 b2)
    {
        return degreeBetweenAB(b1.x(), b1.y(), b2.x(), b2.y());
    }

    public static double distanceBetweenAB(double x1, double y1, double x2, double y2)
    {
        int xDistance = Maths.toInt(x1 - x2);
        int yDistance = Maths.toInt(y1 - y2);

        return Maths.sqrt(xDistance * xDistance + yDistance * yDistance);
    }

    public static double distanceBetweenAB(Box4 b1, Box4 b2)
    {
        return distanceBetweenAB(b1.x(), b1.y(), b2.x(), b2.y());
    }

}
