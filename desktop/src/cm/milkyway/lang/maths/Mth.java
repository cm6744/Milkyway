package cm.milkyway.lang.maths;

public class Mth
{

    public static final int MAX = Integer.MAX_VALUE;
    public static final int MIN = Integer.MIN_VALUE;

    public static final double PI = 3.1415926535;
    private static final double RPI = 180 / PI;

    private static final java.util.Random jRandom = new java.util.Random();

    public static double random()
    {
        return random(0, 1);
    }

    //in [0, 1] ,double value.
    public static double random(double min, double max)
    {
        return jRandom.nextDouble() * (max - min) + min;
    }

    //Native java Random impl.
    public static int randomInt(int min, int max)
    {
        return jRandom.nextInt(max) - min;
    }

    public static double max(double value1, double value2)
    {
        return Math.max(value1, value2);
    }

    public static double min(double value1, double value2)
    {
        return Math.min(value1, value2);
    }

    public static double minAbs(double value1, double value2)
    {
        return min(abs(value1), abs(value2));
    }

    public static double maxAbs(double value1, double value2)
    {
        return max(abs(value1), abs(value2));
    }

    public static double abs(double value)
    {
        return value >= 0 ? value : -value;
    }

    public static boolean similarCompare(double value1, double value2, double maxField)
    {
        return abs(value1 - value2) < maxField;
    }

    public static double toRadians(double degree)
    {
        return degree / RPI;
    }

    public static double toDegree(double theta)
    {
        return theta * RPI;
    }

    public static double sqrt(double value)
    {
        return Math.sqrt(value);
    }

    public static double sin(double value)
    {
        return Math.sin(value);
    }

    public static double cos(double value)
    {
        return Math.cos(value);
    }

    public static double tan(double value)
    {
        return Math.tan(value);
    }

    public static double tan2(double v1, double v2)
    {
        return Math.atan2(v1, v2);
    }

    public static double pow(double v, double mi)
    {
        return Math.pow(v, mi);
    }

}
