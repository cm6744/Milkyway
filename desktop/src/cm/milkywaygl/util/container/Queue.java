package cm.milkywaygl.util.container;

import cm.milkywaygl.interfac.GLIterable;
import cm.milkywaygl.interfac.GLIterator;

public class Queue<E> implements GLIterable<E>
{

    private final List<E> allObj = new List<>();

    public void offer(E obj)
    {
        allObj.add(obj);
    }

    public E take()
    {
        if(allObj.size() == 0) {
            return null;
        }
        return allObj.remove(0);
    }

    public int size()
    {
        return allObj.size();
    }

    public void iterate(GLIterator<E> itr, boolean opposite)
    {
        allObj.iterate(itr, opposite);
    }

}
