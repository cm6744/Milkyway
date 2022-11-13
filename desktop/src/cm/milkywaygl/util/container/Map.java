package cm.milkywaygl.util.container;

import cm.milkywaygl.interfac.GLIterable;
import cm.milkywaygl.interfac.GLIterator;

import java.util.HashMap;

public class Map<K, E> implements GLIterable<E>
{

    private final List<E> allObj = new List<>();
    private final HashMap<K, E> hash = new HashMap<>();//@Nt

    public int size()
    {
        return hash.size();
    }

    public void put(K key, E obj)
    {
        E old = hash.get(key);
        if(old != null) {
            allObj.remove(old);
        }

        allObj.add(obj);
        hash.put(key, obj);
    }

    public boolean has(K key)
    {
        return hash.get(key) != null;
    }

    public E get(K key)
    {
        return hash.get(key);
    }

    public E getOrElse(K key, E nullValue)
    {
        E o = get(key);
        return o == null ? nullValue : o;
    }

    public void iterate(GLIterator<E> itr, boolean opposite)
    {
        allObj.iterate(itr, opposite);
    }

    public List<E> toList()
    {
        return allObj;
    }

}
