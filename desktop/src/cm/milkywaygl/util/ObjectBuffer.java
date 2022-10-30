package cm.milkywaygl.util;

public class ObjectBuffer<E>
{

    E obj;

    public static <E> ObjectBuffer<E> newBuf()
    {
        return new ObjectBuffer<>();
    }

    public void setObject(E o)
    {
        obj = o;
    }

    public E object()
    {
        return obj;
    }

}
