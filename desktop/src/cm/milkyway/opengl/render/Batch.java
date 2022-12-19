package cm.milkyway.opengl.render;

import cm.milkyway.Milkyway;
import cm.milkyway.lang.Disposable;

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
