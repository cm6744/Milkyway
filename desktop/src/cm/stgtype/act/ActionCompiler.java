package cm.stgtype.act;

import cm.milkywaycls.cls.compile.CLSCompiler900;
import cm.milkywaycls.cls.compile.CLSState;
import cm.milkywaycls.cls.value.CLSMethodArg;
import cm.milkywaytool.text.Data;

public class ActionCompiler extends CLSCompiler900
{

    protected boolean catchToken(CLSState state, String s, int ln)
    {
        return Data.startIgnSpace(s, "precompile_act");
    }

    protected void runToken(CLSState state, String s, int ln)
    {
        CLSMethodArg arg = getParams(state, s, ',');
        ActionBufferLib.bas.put(arg.vString(0), new ActionBulletCms(arg.vString(1)));
    }

}
