package cm.backends.gdx.g2d;

import cm.milkyway.Milkyway;
import cm.milkyway.TaskCaller;
import cm.milkyway.opengl.render.g2d.BufferTex;

public class BufferTexGdx implements BufferTex
{

    public com.badlogic.gdx.graphics.Texture nativeTex;

    public BufferTexGdx set(com.badlogic.gdx.graphics.Texture t)
    {
        nativeTex = t;
        TaskCaller.register(nativeTex::dispose, TaskCaller.DISPOSE);
        return this;
    }

    public double w()
    {
        return Milkyway.gl2d.texw(this);
    }

    public double h()
    {
        return Milkyway.gl2d.texh(this);
    }

}
