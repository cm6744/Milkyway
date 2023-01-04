package cm.typestg.act;

import cm.milkyway.lang.io.AccessLocal;
import cm.milkywayx.scriptx.cls.compile.CLS;
import cm.milkywayx.scriptx.cls.compile.CLSCompiler;
import cm.milkywayx.scriptx.cls.compile.CLSCompiler900;
import cm.milkywayx.scriptx.cls.lib.MathLib;
import cm.milkywayx.scriptx.cls.lib.StdLib;
import cm.typestg.Bullet;

public class ActionBulletCms extends Action<Bullet>
{

    CLS cls;

    public ActionBulletCms(String file)
    {
        CLSCompiler compiler = new CLSCompiler900();
        compiler.using(new StdLib());
        compiler.using(new MathLib());
        compiler.using(new ActionBufferLib(this));
        cls = compiler.compileFile(new AccessLocal(file));
    }

    public void tickThen()
    {
        cls.run();
    }

}
