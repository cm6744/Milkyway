package cm.milkywayx.scriptx.cls.method;

import cm.milkywayx.scriptx.cls.value.CLSMethodArg;
import cm.milkywayx.scriptx.cls.value.CLSValue;

public abstract class Method<E extends CLSValue>
{

    E retVal;

    //set return val in invoke
    public abstract void invoke(CLSMethodArg ps);

    public E retVal()
    {
        return retVal;
    }

}
