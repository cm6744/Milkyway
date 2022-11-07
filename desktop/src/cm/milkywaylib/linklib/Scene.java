package cm.milkywaylib.linklib;

import cm.milkywaygl.inter.GLRenderable;
import cm.milkywaygl.inter.GLTimeline;
import cm.milkywaygl.maths.LimitValue;
import cm.milkywaygl.render.GL;
import cm.milkywaygl.render.inat.Context;
import cm.milkywaygl.render.wrapper.Color4;

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
