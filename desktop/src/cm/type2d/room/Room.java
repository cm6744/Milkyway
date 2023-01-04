package cm.type2d.room;

import cm.milkyway.lang.container.list.List;
import cm.milkyway.opengl.render.g2d.Tex;
import cm.milkyway.opengl.render.graphics.Graphics2D;
import cm.milkywayx.widgetx.Renderable2D;
import cm.milkywayx.widgetx.base.Timeline;
import cm.type2d.render.CtUtil;
import cm.type2d.world.FixedWorld;
import cm.type2d.world.entity.Entity;
import cm.type2d.world.entity.Land;

public class Room extends Timeline implements Renderable2D
{

    public FixedWorld world;
    public List<Entity> entities = new List<>();
    public Tex back;
    public Tex front;
    public Tex above;
    public String name;

    public Room(String n)
    {
        name = n;
    }

    public void set(Tex l1, Tex l2, Tex l3)
    {
        back = l1;
        front = l2;
        above = l3;
    }

    public void dim(double x, double y, double w, double h)
    {
        new Land(world, this, x, y, w, h).spawn();
    }

    public void pushWorld(FixedWorld w)
    {
        world = w;
    }

    public void onWorldStart(FixedWorld world)
    {
    }

    public void onLoadIntoWorld(FixedWorld world)
    {
    }

    public void onRemoveOutOfWorld(FixedWorld world)
    {
    }

    public void onWorldGenerate(FixedWorld world)
    {
    }

    public void tick()
    {
        tickThen();
        promote();
        CtUtil.fastTick(entities);
    }

    public void tickThen()
    {
    }

    public void render(Graphics2D g)
    {
        if(back != null)
            g.draw(back, 0, 0, g.getContext().width(), g.getContext().height());
        if(front != null)
            g.draw(front, 0, 0, g.getContext().width(), g.getContext().height());
        CtUtil.fastRender(g, entities);
        if(above != null)
            g.draw(above, 0, 0, g.getContext().width(), g.getContext().height());
    }

}
