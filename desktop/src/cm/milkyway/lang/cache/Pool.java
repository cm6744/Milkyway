package cm.milkyway.lang.cache;

import cm.milkyway.lang.Provider;
import cm.milkyway.lang.container.Queue;

//cache some always-created objects.
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
