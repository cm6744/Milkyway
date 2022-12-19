package cm.milkyway.opengl.render;

import cm.milkyway.opengl.render.g2d.Color4;
import cm.milkyway.opengl.render.g2d.Font2;

public class State2D
{

    public double rotation;
    public double alpha = 1.0;
    public boolean mirror = false;
    public double r, g, b, a;
    public Font2 fontNow = null;
    public double apr, apg, apb;

    public void clear()
    {
        clearOpacity();
        clearRot();
        mirrored(false);
        r = g = b = a = 0;
        fontNow = null;
        apr = apg = apb = 1;
    }

    public void clearRot()
    {
        rotation = 0;
    }

    public void clearOpacity()
    {
        alpha = 1;
    }

    public void rotate(double degree)
    {
        rotation = degree;
    }

    public void opacity(double v)
    {
        alpha = v;
    }

    public void mirrored(boolean mir)
    {
        mirror = mir;
    }

    public void color(Color4 c4f)
    {
        r = c4f.red();
        g = c4f.green();
        b = c4f.blue();
        a = c4f.alpha();
    }

    public void color(double rc, double gc, double bc, double ac)
    {
        r = rc;
        g = gc;
        b = bc;
        a = ac;
    }

    public void dye(Color4 c4f)
    {
        apr = c4f.red();
        apg = c4f.green();
        apb = c4f.blue();
    }

    public void dye(double rc, double gc, double bc)
    {
        apr = rc;
        apg = gc;
        apb = bc;
    }

    public void font(Font2 f2f)
    {
        fontNow = f2f;
    }

    public State2D copy(State2D state)
    {
        rotation = state.rotation;
        alpha = state.alpha;
        mirror = state.mirror;
        r = state.r;
        g = state.g;
        b = state.b;
        a = state.a;
        fontNow = state.fontNow;
        return this;
    }

}
