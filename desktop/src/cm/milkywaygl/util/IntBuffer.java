package cm.milkywaygl.util;

public class IntBuffer
{

    public static IntBuffer create(int v)
    {
        return create().setValue(v);
    }

    public static IntBuffer create()
    {
        return new IntBuffer();
    }

    int val = IndexCache.NULL;

    public IntBuffer setValue(int v)
    {
        val = v;
        return this;
    }

    public int value()
    {
        return val;
    }

}
