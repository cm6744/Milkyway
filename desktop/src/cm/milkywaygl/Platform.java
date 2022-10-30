package cm.milkywaygl;

public class Platform
{

    public static boolean exited;

    public static void log(String mes)
    {
        System.out.println("[INFO] " + mes);
    }

    public static void throwExc(Exception e)
    {
        error(e.getMessage());
    }

    public static void error(String mes)
    {
        System.err.println("[ERROR] " + mes);
    }

    public static long getTickNano()
    {
        return System.nanoTime();
    }

    public static long getTickNs()
    {
        return System.currentTimeMillis();
    }

    public static String getPlatformLocal()
    {
        return System.getProperty("os.name");
    }

    public static void exit()
    {
        exited = true;
        System.exit(0);
    }

}
