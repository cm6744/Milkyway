package cm.milkywaylib.stg;

import cm.milkywaygl.util.container.List;

public class ActionMap<E>
{

    List<Action<E>> items = new List<>();

    public ActionMap(Action<E>... acts)
    {
        items.addAll(acts);
    }

    public Action<E> get(int i)
    {
        return items.get(i);
    }

}
