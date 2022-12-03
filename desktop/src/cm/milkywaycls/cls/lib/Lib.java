package cm.milkywaycls.cls.lib;

import cm.milkywaycls.cls.method.Method;

public abstract class Lib
{

    Libs libs;

    public void put(String key, Method<?> method)
    {
        libs.methodMap.put(key, method);
    }

    protected abstract void load();

}
