package cm.stgtype.act;

import cm.milkywaycls.cls.compile.CLS;
import cm.milkywaycls.cls.compile.CLSCompiler;
import cm.milkywaycls.cls.compile.CLSCompiler900;
import cm.milkywaycls.cls.lib.MathLib;
import cm.milkywaycls.cls.lib.StdLib;
import cm.stgtype.Bullet;

public class ActionBulletCms extends Action<Bullet>
{

    CLS cls;

    public ActionBulletCms(String file)
    {
        CLSCompiler compiler = new CLSCompiler900();
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
