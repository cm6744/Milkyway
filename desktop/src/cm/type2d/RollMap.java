package cm.type2d;

import cm.milkyway.Milkyway;
import cm.milkywayx.widgetx.base.Scene;
import cm.milkyway.physics.shapes.Rect;
import cm.milkyway.opengl.render.g2d.BufferTex;

public class RollMap extends Scene
{

    BufferTex tex;

    Rect rect;

    public RollMap(BufferTex t, Rect re)
    {
        tex = t;
        rect = re;
    }

    public Rect rect()
    {
        return rect;
    }

    public void tickThen()
    {

    }

    public void render()
    {
        Milkyway.gl2d.dim(tex, rect);
    }

}
