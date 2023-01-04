package cm.milkyway.opengl.input;

public class InputMap
{

    public static InputCache cache;

    public static boolean isOn(Key code)
    {
        return cache.isOn(code);
    }

    public static int downTime(Key code)
    {
        return cache.downTime(code);
    }

    public static boolean isClick(Key code)
    {
        return cache.isClick(code);
    }

    public static double x()
    {
        return cache.x();
    }

    public static double y()
    {
        return cache.y();
    }

    public static double scroll()
    {
        return cache.scroll();
    }

}
