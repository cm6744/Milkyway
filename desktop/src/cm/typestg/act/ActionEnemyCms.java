package cm.typestg.act;

import cm.milkywayx.scriptx.cls.compile.CLS;
import cm.milkywayx.scriptx.cls.compile.CLSCompiler;
import cm.milkywayx.scriptx.cls.lib.MathLib;
import cm.milkywayx.scriptx.cls.lib.StdLib;
import cm.typestg.Enemy;

public class ActionEnemyCms extends Action<Enemy>
{

    CLS cls;

    public ActionEnemyCms(String file)
    {
        CLSCompiler compiler = new ActionCompiler();
        compiler.using(new StdLib());
        compiler.using(new MathLib());
        compiler.using(new ActionBufferLib(this));
        cls = compiler.compileFile(file);
    }

    public void tickThen()
    {
        cls.run();
    }

}
