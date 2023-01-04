package cm.type2d.registry;

import cm.milkyway.lang.container.map.Map;

public class Register<E>
{

    Map<String, E> map = new Map<>();

    public void call()
    {
    }

    public void register(String name, E o)
    {
        map.put(name, o);
    }

    public E get(String name)
    {
        return map.get(name);
    }

    public int size()
    {
        return map.size();
    }

    public Map<String, E> map()
    {
        return map;
    }

}
