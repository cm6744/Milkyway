package cm.milkywayx.scriptx.cls.statement;

import cm.milkyway.lang.container.list.List;

public class IfManage
{

    List<IfState> states = new List<>();

    public void append(IfState state)
    {
        states.add(state);
    }

    public void append(List<IfState> state)
    {
        state.iterate((o, i) -> {
            append(o);
        }, false);
    }

    public boolean isTrue()
    {
        for(int i = 0; i < states.size(); i++) {
            if(!states.get(i).isTrue()) {
                return false;
            }
        }
        return true;
    }

}
