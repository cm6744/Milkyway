package cm.milkywaygl.maths;

public class Maths
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

    public static int max(int value1, int value2)
    {
        return value1 > value2 ? value1 : value2;
    }

    public static double max(double value1, double value2)
    {
        return value1 > value2 ? value1 : value2;
    }

    public static int min(int value1, int value2)
    {
        return value1 < value2 ? value1 : value2;
    }

    public static double min(double value1, double value2)
    {
        return value1 < value2 ? value1 : value2;
    }

    public static int abs(int value)
    {
        return value >= 0 ? value : -value;
    }

    public static double abs(double value)
    {
        return value >= 0 ? value : -value;
    }

    public static int toInt(double value)
    {
        return (int) (value);
    }

    public static double toDouble(int value)
    {
        return value;
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

}
