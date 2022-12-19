package cm.milkywayx.scriptx.cls.method;

import cm.milkywayx.scriptx.cls.value.CLSBool;

public abstract class BoolMethod extends Method<CLSBool>
{

    public BoolMethod()
    {
        retVal = new CLSBool(false);
    }

}
