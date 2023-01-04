package cm.type2d.world.entity;

import cm.milkyway.opengl.render.g2d.Color;
import cm.milkyway.opengl.render.graphics.Graphics2D;
import cm.type2d.room.Room;
import cm.type2d.world.FixedWorld;

public class Land extends Entity
{

    public Land(FixedWorld wd, Room r, double sx, double sy, double w, double h)
    {
        super(wd, r, sx, sy, w, h);
        renderBox.copy(bound);
    }

    public void tickThen()
    {
    }

    public void render(Graphics2D g, double x, double y, double w, double h)
    {
        g.drawRect(Color.C1111, x,y,w,h);
    }

    public boolean needToSave()
    {
        return false;
    }

    public String name()
    {
        return "land";
    }

}
