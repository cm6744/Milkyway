package cm.typestg;

import cm.milkyway.opengl.render.g2d.Tex;
import cm.milkyway.opengl.render.graphics.Graphics2D;
import cm.milkywayx.widgetx.widget.Bounder;
import cm.milkyway.lang.maths.shapes.Rect;
import cm.typestg.act.Action;

public class Bullet extends Bounder<Rect>
{

    public static int COLOR_MAX = 16;
    public static int TYPE_MAX = 1024;
    public static int UV_SIZE_TILE = 16;
    public static double FOGGY_TIME = 14;
    public static Tex FOG;

    Action<Bullet> action;
    int color, type;

    public int mark;

    public static final int PLAYER = 1;
    public static final int ENEMY = 0;
    public static final int NONE = -1;

    public boolean rotating;
    public double rotateSpeed;
    public boolean rotatable;

    double alive;

    public double aliveTime()
    {
        return alive;
    }

    public void setAliveTime(double al)
    {
        alive = al;
    }

    protected void initBound()
    {
        bound = new Rect();
        allowBoundRotate = true;
    }

    public void tickThen()
    {
        super.tickThen();

        if(rotatable) {
            if(!rotating) {
                effect.setRotation(vecInfo.degree());
            }
            else {
                effect.mvRotation(rotateSpeed);
            }
        }

        if(action != null) {
            action.buf = this;
            action.tick();
        }

        //if w != h, allow rotation
        //meant to decrease interaction CPU gap.
        allowBoundRotate = (renderBox.w() != renderBox.h());

        if(time() > alive) {
            remove();
        }
    }

    public void renderThen(Graphics2D g, double x, double y, double w, double h)
    {
        if(time() <= FOGGY_TIME) {
            g.setOpacity(time() / FOGGY_TIME);
        }
        g.draw(texture(), renderBox);
        g.setOpacity(1);
    }

    public void dye(int col)
    {
        color = col;
    }

    public void retype(int typ)
    {
        type = typ;
    }

    public int color()
    {
        return color;
    }

    public int type()
    {
        return type;
    }

    public void act(Action<Bullet> act)
    {
        action = act;
    }

    public Action<Bullet> action()
    {
        return action;
    }

    public boolean canBound()
    {
        return true;
    }

}
