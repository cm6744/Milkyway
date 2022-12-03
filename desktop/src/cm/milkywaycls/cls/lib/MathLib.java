package cm.milkywaycls.cls.lib;

import cm.milkywaycls.cls.method.NumMethod;
import cm.milkywaycls.cls.value.CLSMethodArg;
import cm.milkywaytool.maths.Mth;

public class MathLib extends Lib
{

    public void load()
    {
        put("rand", new Ran());
        put("add", new Add());
        put("mul", new Mul());
        put("pow", new Pow());
        put("mod", new Mod());
    }

    private static class Ran extends NumMethod
    {
        public void invoke(CLSMethodArg ps)
        {
            retVal().set(Mth.random(ps.vDouble(0), ps.vDouble(1)));
        }
    }

    private static class Add extends NumMethod
    {
        public void invoke(CLSMethodArg ps)
        {
            retVal().set(ps.vDouble(0) + ps.vDouble(1));
        }
    }

    private static class Mul extends NumMethod
    {
        public void invoke(CLSMethodArg ps)
        {
            retVal().set(ps.vDouble(0) * ps.vDouble(1));
        }
    }

    private static class Mod extends NumMethod
    {
        public void invoke(CLSMethodArg ps)
        {
            retVal().set(ps.vDouble(0) % ps.vDouble(1));
        }
    }

    private static class Pow extends NumMethod
    {
        public void invoke(CLSMethodArg ps)
        {
            retVal().set(Math.pow(ps.vDouble(0), ps.vDouble(1)));
        }
    }

}
