package cm.type2d.world.entity;

import cm.milkyway.lang.maths.shapes.Rect;
import cm.milkyway.opengl.render.g2d.Tex;
import cm.milkyway.opengl.render.graphics.Graphics2D;
import cm.milkywayx.widgetx.Renderable2D;
import cm.milkywayx.widgetx.base.Timeline;
import cm.type2d.registry.Registry;
import cm.type2d.room.Room;
import cm.type2d.serialize.FesSerializer;
import cm.type2d.world.FixedWorld;

public abstract class Entity extends Timeline implements Renderable2D
{

    public FixedWorld world;
    public Room room;
    public Collision collision;
    public Rect bound = new Rect();
    public Rect renderBox = new Rect();
    public Tex tex;

    public Entity(FixedWorld w, Room r, double sx, double sy)
    {
        super();
        world = w;
        room = r;
        Rect[] rs = Registry.ENTITY_BOUND.get(name());
        bound.copy(rs[0]);
        bound.loc(sx, sy);
        renderBox.copy(rs[1]);
        collision = new Collision(this);
    }

    public Entity(FixedWorld wd, Room r, double sx, double sy, double w, double h)
    {
        super();
        world = wd;
        room = r;
        bound.resize(w, h);
        bound.loc(sx, sy);
        collision = new Collision(this);
    }

    public abstract String name();

    public void tickThen()
    {
    }

    public void render(Graphics2D g)
    {
        double w = renderBox.w();
        double h = renderBox.h();
        double x = bound().x() - w / 2;
        double y = bound().y() - h / 2;
        render(g, x, y, w, h);
    }

    public void render(Graphics2D g, double x, double y, double w, double h)
    {
    }

    public void spawn()
    {
        room.entities.add(this);
    }

    public void kill()
    {
        room.entities.remove(this);
    }

    public void move(Room r)
    {
        kill();
        room = r;
        spawn();
    }

    public ActionResult interact(Player player)
    {
        return ActionResult.PASS;
    }

    public boolean canBeInteracted(Player player)
    {
        return true;
    }

    public boolean canBeFind(Player player)
    {
        return true;
    }

    public boolean isHardCollision(Entity e)
    {
        return true;
    }

    public int getLevel()
    {
        return 0;
    }

    public Rect bound()
    {
        return bound;
    }

    public boolean needToSave()
    {
        return true;
    }

    public FesSerializer<? extends Entity> getSerializer()
    {
        return null;
    }

}
