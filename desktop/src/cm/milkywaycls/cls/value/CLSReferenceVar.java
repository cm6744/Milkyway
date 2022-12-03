package cm.milkywaycls.cls.value;

import cm.milkywaycls.cls.compile.CLSState;

public class CLSReferenceVar extends CLSValue
{

    String name;
    CLSState state;

    public CLSReferenceVar(String n, CLSState st)
    {
        name = n;
        state = st;
    }

    public CLSValue get()
    {
        return state.vars.get(name);
    }

    public Type type()
    {
        return state.vars.get(name).type();
    }

    public double vDouble()
    {
        return state.vars.get(name).vDouble();
    }

    public boolean vBool()
    {
        return state.vars.get(name).vBool();
    }

    public String vString()
    {
        return state.vars.get(name).vString();
    }

    public CLSValue copy()
    {
        return new CLSReferenceVar(name, state);
    }

}
