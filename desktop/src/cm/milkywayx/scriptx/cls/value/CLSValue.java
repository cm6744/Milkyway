package cm.milkywayx.scriptx.cls.value;

public class CLSValue
{

    public enum Type
    {
        NONE,
        STRING,
        DOUBLE,
        BOOLEAN,
        KEYWORD
    }

    public Type type()
    {
        return Type.NONE;
    }

    public int vInt()
    {
        return (int) vDouble();
    }

    public double vDouble()
    {
        return 0;
    }

    public void set(double d)
    {
    }

    public boolean vBool()
    {
        return false;
    }

    public void set(boolean d)
    {
    }

    public String vString()
    {
        return null;
    }

    public void set(String d)
    {
    }

    public CLSValue copy()
    {
        return null;
    }

}
