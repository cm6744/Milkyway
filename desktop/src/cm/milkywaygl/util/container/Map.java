package cm.milkywaygl.util.container;

import java.util.Collection;
import java.util.HashMap;
import java.util.Set;

public class Map<K, E>
{

    private final HashMap<K, E> hsb = new HashMap<>();//@Nt

    public int size()
    {
        return hsb.size();
    }

    public List<E> values()
    {
        Collection<E> lstJ = hsb.values();
        List<E> lst = new List<>();
        for(E e : lstJ) {
            lst.add(e);
        }
        return lst;
    }

    public List<K> keys()
    {
        Set<K> lstJ = hsb.keySet();
        List<K> lst = new List<>();
        for(K e : lstJ) {
            lst.add(e);
        }
        return lst;
    }

    public void put(K key, E obj)
    {
        hsb.put(key, obj);
    }

    public E get(K key)
    {
        return hsb.get(key);
    }

    public E getOrElse(K key, E nullValue)
    {
        E o = get(key);
        return o == null ? nullValue : o;
    }

}
