package cm.milkywaygl.render;

import cm.milkywaygl.render.g2d.Color4;
import cm.milkywaygl.render.g2d.Font2;

public class State2D
{

    public double rotation;
    public double alpha = 1.0;
    public boolean mirror = false;
    public Color4 colorNow = null;
    public Font2 fontNow = null;

    public void clear()
    {
        clearOpacity();
        clearRot();
        mirrored(false);
        colorNow = null;
        fontNow = null;
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
        rotation += degree;
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
        colorNow = c4f;
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
        colorNow = state.colorNow;
        fontNow = state.fontNow;
        return this;
    }

}
