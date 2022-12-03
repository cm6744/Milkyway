package cm.glbackends.gdx.g2d;

import cm.milkywaygl.Milkyway;
import cm.milkywaygl.TaskCaller;
import cm.milkywaygl.render.g2d.BufferTex;

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
