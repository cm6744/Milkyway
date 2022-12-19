package cm.milkyway.opengl.input;

import cm.milkyway.lang.container.List;

public abstract class InputRegistry
{

    protected List<InputCallback> callers = new List<>();

    public void register(InputCallback cb)
    {
        callers.add(cb);
    }

    public void unregister(InputCallback cb)
    {
        callers.remove(cb);
    }

}
