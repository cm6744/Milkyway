package cm.milkywaygl.util;

import cm.milkywaygl.interfac.GLReturnVal;
import cm.milkywaygl.util.container.Queue;

public class Pool<E>
{

    Queue<E> frees = new Queue<>();
    GLReturnVal<E> creator;

    public Pool(GLReturnVal<E> ret)
    {
        creator = ret;
    }

    public void fill(int size)
    {
        for(int i = 0; i < size; i++) {
            frees.offer(creator.create());
        }
    }

    public E get()
    {
        E o = frees.take();
        return o == null ? creator.create() : o;
    }

    public void reuse(E obj)
    {
        frees.offer(obj);
    }

    public int freeSize()
    {
        return frees.size();
    }

}
