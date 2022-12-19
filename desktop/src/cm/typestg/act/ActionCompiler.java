package cm.typestg.act;

import cm.milkywayx.scriptx.cls.compile.CLSCompiler900;
import cm.milkywayx.scriptx.cls.compile.CLSState;
import cm.milkywayx.scriptx.cls.value.CLSMethodArg;
import cm.milkyway.lang.text.Data;

public class ActionCompiler extends CLSCompiler900
{

    protected boolean catchToken(CLSState state, String s, int ln)
    {
        return Data.startIgnSpace(s, "precompile_act_b") || Data.startIgnSpace(s, "precompile_act_e");
    }

    protected void runToken(CLSState state, String s, int ln)
    {
        CLSMethodArg arg = getParams(state, s, ',');
        if(Data.startIgnSpace(s, "precompile_act_b")) {
            ActionBufferLib.bas.put(arg.vString(0), new ActionBulletCms(arg.vString(1)));
        }
        else if(Data.startIgnSpace(s, "precompile_act_e")) {
            ActionBufferLib.eas.put(arg.vString(0), new ActionEnemyCms(arg.vString(1)));
        }
    }

}
