package cm.milkywaytool.container;

import java.util.HashSet;

public class Set<E>
{

    private final java.util.Set<E> set = new HashSet<>();

    public void add(E obj)
    {
        set.add(obj);
    }

    public boolean contains(E obj)
    {
        return set.contains(obj);
    }

    public int size()
    {
        return set.size();
    }

}
