package cm.milkywayx.scriptx.cls.compile;

import cm.milkywayx.scriptx.cls.statement.IfManage;

public class CLS
{

    CLSState state;

    public CLS(CLSState s)
    {
        state = s;
    }

    public void run()
    {
        state.lines.iterate((o, i) -> {
            IfManage ifm = state.ifStates.get(i);
            if(ifm.isTrue()) {
                o.run();
            }
        }, false);
    }

}
