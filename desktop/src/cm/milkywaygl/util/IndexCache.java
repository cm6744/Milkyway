package cm.milkywaygl.util;

import cm.milkywaygl.util.container.List;

public class IndexCache<E>
{

    public static final int NULL = -1;

    List<E> lst = new List<>();

    public IntBuffer gen(E e)
    {
        lst.add(e);
        return IntBuffer.create(lst.last());
    }

    public E get(IntBuffer i)
    {
        return get(i.value());
    }

    public E get(int i)
    {
        return lst.get(i);
    }

    public int size()
    {
        return lst.size();
    }

}
