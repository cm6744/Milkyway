package cm.milkyway.physics.d4simple;

import cm.milkyway.lang.container.List;

public class ForceInfo
{

    public enum States {
        ON_LAND,
        REACH_TOP
    }

    List<States> states = new List<>();

    public void clear()
    {
        states.clear();
    }

    public void add(States s)
    {
        if(!has(s)) {
            states.add(s);
        }
    }

    public boolean has(States s)
    {
        return states.contains(s);
    }

}
