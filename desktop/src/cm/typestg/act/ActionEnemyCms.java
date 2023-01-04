package cm.typestg.act;

import cm.milkyway.lang.io.AccessLocal;
import cm.milkywayx.scriptx.cls.compile.CLS;
import cm.milkywayx.scriptx.cls.compile.CLSCompiler;
import cm.milkywayx.scriptx.cls.lib.MathLib;
import cm.milkywayx.scriptx.cls.lib.StdLib;
import cm.typestg.Enemy;

import static cm.typestg.Scr.scr;

public class ActionEnemyCms extends Action<Enemy>
{

    CLS cls;

    public ActionEnemyCms(String file)
    {
        CLSCompiler compiler = new ActionCompiler();
        compiler.using(new StdLib());
        compiler.using(new MathLib());
        compiler.using(new ActionBufferLib(this));
        cls = compiler.compileFile(new AccessLocal(file));
    }

    public void tickThen()
    {
        if(buf.bound().interacts(scr)) {
            cls.run();
        }
    }

}
