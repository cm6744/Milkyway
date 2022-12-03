package cm.milkywaycls.cls.lib;

import cm.milkywaycls.cls.method.Method;
import cm.milkywaycls.cls.value.CLSMethodArg;
import cm.milkywaycls.cls.value.CLSValue;
import cm.milkywaytool.container.Map;

public class Libs
{

    protected Map<String, Method<?>> methodMap = new Map<>();

    public void load(Lib lib)
    {
        lib.libs = this;
        lib.load();
    }

    public CLSValue invoke(String methodName, CLSMethodArg ps)
    {
        Method<?> method = methodMap.get(methodName);
        method.invoke(ps);
        return method.retVal();
    }

    public Method<?> get(String key)
    {
        return methodMap.get(key);
    }

}
