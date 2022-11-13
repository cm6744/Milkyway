package cm.milkywaylib.base;

import cm.milkywaygl.interfac.GLRenderable;
import cm.milkywaygl.interfac.GLTimeline;

public class Scene extends GLTimeline implements GLRenderable
{

    protected int time;
    protected Shadow shadow = new Shadow();

    public void init()
    {
    }

    public void tickThen()
    {
    }

    public void render()
    {
    }

    public void promote()
    {
        time++;
    }

    public int time()
    {
        return time;
    }

    public Shadow shadow()
    {
        return shadow;
    }

}
