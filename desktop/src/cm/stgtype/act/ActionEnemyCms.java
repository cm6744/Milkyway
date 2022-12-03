package cm.stgtype.act;

import cm.milkywaycls.cls.compile.CLS;
import cm.milkywaycls.cls.compile.CLSCompiler;
import cm.milkywaycls.cls.lib.MathLib;
import cm.milkywaycls.cls.lib.StdLib;
import cm.stgtype.Enemy;

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
