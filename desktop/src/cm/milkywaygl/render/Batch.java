package cm.milkywaygl.render;

import cm.milkywaygl.Milkyway;
import cm.milkywaytool.Disposable;

public interface Batch extends Disposable
{

    void nbegin();

    void nend();

    void init();

    default void begin()
    {
        Milkyway.glBase.begin(this);
        nbegin();
    }

    default void end()
    {
        nend();
        Milkyway.glBase.end(this);
    }

}
