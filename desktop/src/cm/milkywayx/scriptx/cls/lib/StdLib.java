package cm.milkywayx.scriptx.cls.lib;

import cm.milkywayx.scriptx.cls.method.VoidMethod;
import cm.milkywayx.scriptx.cls.value.CLSMethodArg;
import cm.milkyway.lang.Platform;

public class StdLib extends Lib
{

    public void load()
    {
        put("print", new Print());
    }

    private static class Print extends VoidMethod
    {
        public void invoke(CLSMethodArg ps)
        {
            Platform.log(ps.vString(0));
        }
    }

}
