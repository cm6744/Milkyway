package cm.milkywayx.scriptx.cls.statement;

public class WhileState
{

    IfState state;

    public WhileState(IfState s)
    {
        state = s;
    }

    public boolean canRun()
    {
        return state.isTrue();
    }

}
