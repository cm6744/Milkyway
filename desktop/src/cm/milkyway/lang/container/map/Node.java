package cm.milkyway.lang.container.map;

public class Node<K, V>
{

    K key;
    V val;

    public Node(K k, V v)
    {
        key = k;
        val = v;
    }

    public K key()
    {
        return key;
    }

    public V value()
    {
        return val;
    }

}
