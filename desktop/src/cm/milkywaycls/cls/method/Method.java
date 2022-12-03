package cm.milkywaycls.cls.method;

import cm.milkywaycls.cls.value.CLSMethodArg;
import cm.milkywaycls.cls.value.CLSValue;

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
