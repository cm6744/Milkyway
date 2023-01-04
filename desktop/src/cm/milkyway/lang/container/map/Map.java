package cm.milkyway.lang.container.map;

import cm.milkyway.lang.container.Iterable;
import cm.milkyway.lang.container.Iterator;
import cm.milkyway.lang.container.list.List;
import cm.milkyway.lang.maths.Mth;

public class Map<K, V> implements Iterable<Node<K, V>>, MapForm<K, V>
{

    protected boolean comp(K k1, K k2)
    {
        return k1 == k2 || k1.equals(k2);
    }

    public static final int DEF_UPSIZE = 16;

    List<List<Node<K, V>>> nodes = new List<>();
    List<Node<K, V>> cache = new List<>();
    int maxSize;

    public Map()
    {
        capUp();
    }

    private void capUp()
    {
        for(int i = 0; i < DEF_UPSIZE; i++)
        {
            nodes.add(new List<>());
        }
        maxSize += DEF_UPSIZE;
        for(int i = 0; i < maxSize; i++)
        {
            nodes.get(i).clear();
        }
        Node<K, V> n;
        for(int i = 0; i < cache.size(); i++)
        {
            n = cache.get(i);
            nodes.get(pos(n.key)).add(n);
        }
    }

    private int pos(K key)
    {
        return (int) Mth.abs(key.hashCode() % maxSize);
    }

    public int size()
    {
        return cache.size();
    }

    public V remove(K key)
    {
        int pos = pos(key);
        List<Node<K, V>> lst = nodes.get(pos);

        Node<K, V> n;
        for(int i = 0; i < lst.size(); i++)
        {
            n = lst.get(i);
            if(comp(key, n.key)) {
                lst.remove(i);
                return n.val;
            }
        }

        return null;
    }

    public void put(K key, V v)
    {
        int p = pos(key);
        List<Node<K, V>> lst = nodes.get(p);
        Node<K, V> n;
        for(int i = 0; i < lst.size(); i++)
        {
            n = lst.get(i);
            if(comp(n.key, key))
            {
                return;
            }
        }
        n = new Node<>(key, v);
        lst.add(n);
        cache.add(n);

        if(lst.size() >= 16)
        {
            capUp();
        }
    }

    public V get(K key)
    {
        int pos = pos(key);
        List<Node<K, V>> lst = nodes.get(pos);

        Node<K, V> n;
        for(int i = 0; i < lst.size(); i++)
        {
            n = lst.get(i);
            if(comp(key, n.key)) {
                return n.val;
            }
        }

        return null;
    }

    public void iterate(Iterator<Node<K, V>> itr, boolean opposite)
    {
        cache.iterate(itr, opposite);
    }

    public List<Node<K, V>> toList()
    {
        return cache;
    }

}
