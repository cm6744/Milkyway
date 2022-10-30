package cm.milkywaygl.util;

public class IntBuffer
{

    int value;

    public static IntBuffer newBuf(int i)
    {
        IntBuffer buf = new IntBuffer();
        buf.setValue(i);
        return buf;
    }

    public static IntBuffer newBuf()
    {
        return new IntBuffer();
    }

    public void setValue(int val)
    {
        value = val;
    }

    public int value()
    {
        return value;
    }

}
