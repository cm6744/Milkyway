package cm.milkyway.lang.container.list;

public abstract class AbstractList<E> implements ArrayForm<E>
{

    public int last()
    {
        return size() - 1;
    }

    public E lastObj()
    {
        return get(last());
    }

    public E firstObj()
    {
        return get(0);
    }

    public E take()
    {
        return remove(0);
    }

    public E remove(E e)
    {
        return remove(indexOf(e));
    }

    public boolean contains(E e)
    {
        return indexOf(e) >= 0;
    }

    public boolean isEmpty()
    {
        return size() == 0;
    }

    public void addAll(ArrayForm<E> e)
    {
        for(int i = 0; i < e.size(); i++)
        {
            add(e.get(i));
        }
    }

}
