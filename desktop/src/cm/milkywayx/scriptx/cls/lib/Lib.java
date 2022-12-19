package cm.milkywayx.scriptx.cls.lib;

import cm.milkywayx.scriptx.cls.method.Method;

public abstract class Lib
{

    Libs libs;

    public void put(String key, Method<?> method)
    {
        libs.methodMap.put(key, method);
    }

    protected abstract void load();

}
