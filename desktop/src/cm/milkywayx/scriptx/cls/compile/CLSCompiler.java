package cm.milkywayx.scriptx.cls.compile;

import cm.milkywayx.scriptx.cls.lib.Lib;
import cm.milkywayx.scriptx.cls.lib.Libs;
import cm.milkyway.Milkyway;
import cm.milkyway.lang.container.List;

public abstract class CLSCompiler
{

    public static CLSCompiler standardCompiler()
    {
        return new CLSCompiler900();
    }

    Libs libs = new Libs();

    public void using(Lib l)
    {
        libs.load(l);
    }

    public CLS compileFile(String file)
    {
        return compileText(Milkyway.accessor.read(file));
    }

    public CLS compileText(List<String> file)
    {
        CLSState state = new CLSState();

        file.iterate((s, i) -> {
            compile(state, s, i);
        }, false);

        flushCache();

        return new CLS(state);
    }

    protected abstract void compile(CLSState state, String s, int ln);

    protected abstract void flushCache();

    //needs to be impl.
    //catch a token during precompile, and do sth;
    //return false to skip.
    protected boolean catchToken(CLSState state, String s, int ln)
    {
        return false;
    }

    protected void runToken(CLSState state, String s, int ln)
    {
    }

}
