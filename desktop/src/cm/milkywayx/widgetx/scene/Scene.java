package cm.milkywayx.widgetx.scene;

import cm.milkyway.opengl.render.graphics.Graphics2D;
import cm.milkywayx.widgetx.Renderable2D;
import cm.milkywayx.widgetx.base.Timeline;

public class Scene extends Timeline implements Renderable2D
{

    protected Shadow shadow = new Shadow();

    public void init()
    {
    }

    public void tickThen()
    {
    }

    public void render(Graphics2D g)
    {
    }

    public Shadow shadow()
    {
        return shadow;
    }

}
