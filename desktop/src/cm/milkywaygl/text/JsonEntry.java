package cm.milkywaygl.text;

public class JsonEntry
{

    Boolean v_bool;
    Double v_double;
    Integer v_int;
    String v_str;

    public JsonEntry(int i)
    {
        v_int = i;
    }

    public JsonEntry(double i)
    {
        v_double = i;
    }

    public JsonEntry(boolean i)
    {
        v_bool = i;
    }

    public JsonEntry(String i)
    {
        v_str = i;
    }

    public int toInt()
    {
        return v_int;
    }

    public double toDouble()
    {
        return v_double;
    }

    public String toString()
    {
        return v_str;
    }

    public boolean toBool()
    {
        return v_bool;
    }

}
