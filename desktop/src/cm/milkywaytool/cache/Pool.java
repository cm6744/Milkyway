package cm.milkywaytool.cache;

import cm.milkywaytool.Provider;
import cm.milkywaytool.container.Queue;

public class Pool<E>
{

    Queue<E> frees = new Queue<>();
    Provider<E> creator;

    public Pool(Provider<E> ret)
    {
        creator = ret;
    }

    public void fill(int size)
    {
        for(int i = 0; i < size; i++) {
            frees.offer(creator.get());
        }
    }

    public E get()
    {
        E o = frees.take();
        if(o == null) {
            return creator.get();
        }
        return o;
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
