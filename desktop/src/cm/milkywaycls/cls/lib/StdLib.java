package cm.milkywaycls.cls.lib;

import cm.milkywaycls.cls.method.VoidMethod;
import cm.milkywaycls.cls.value.CLSMethodArg;
import cm.milkywaygl.Platform;

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
