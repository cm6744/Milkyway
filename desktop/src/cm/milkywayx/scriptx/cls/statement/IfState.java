package cm.milkywayx.scriptx.cls.statement;

import cm.milkywayx.scriptx.cls.value.CLSBoolExpr;
import cm.milkywayx.scriptx.cls.value.CLSMethodArg;

public class IfState
{

    CLSBoolExpr expr;

    public IfState(CLSMethodArg params)
    {
        expr = new CLSBoolExpr(params);
    }

    public IfState(CLSBoolExpr e)
    {
        expr = e;
    }

    public IfState getReverse()
    {
        return new IfState(expr.getReverse());
    }

    public boolean isTrue()
    {
        return expr.vBool();
    }

}
