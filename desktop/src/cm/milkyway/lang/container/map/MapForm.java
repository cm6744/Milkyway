package cm.milkyway.lang.container.map;

public interface MapForm<K, V>
{

    int size();

    V remove(K key);

    void put(K key, V v);

    V get(K key);

}
