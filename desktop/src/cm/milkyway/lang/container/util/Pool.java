package cm.milkyway.lang.container.util;

import cm.milkyway.lang.Provider;
import cm.milkyway.lang.container.list.List;

//createSaveData some always-created objects.
public class Pool<E>
{

    List<E> frees = new List<>();
    Provider<E> creator;

    public Pool(Provider<E> ret)
    {
        creator = ret;
    }

    public void fill(int size)
    {
        for(int i = 0; i < size; i++) {
            frees.add(creator.get());
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
        frees.add(obj);
    }

    public int freeSize()
    {
        return frees.size();
    }

}
