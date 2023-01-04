package cm.milkyway.lang;

public class Platform
{

    public static boolean exited;

    public static void log(String mes)
    {
        System.out.println("[INFO] " + mes);
    }

    public static void error(String mes)
    {
        System.err.println("[ERROR] " + mes);
    }

    public static long getTickNano()
    {
        return System.nanoTime();
    }

    public static long getTickMill()
    {
        return System.currentTimeMillis();
    }

    public static void exit()
    {
        exited = true;
        System.exit(0);
    }

}
