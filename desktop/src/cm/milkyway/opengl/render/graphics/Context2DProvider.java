package cm.milkyway.opengl.render.graphics;

import cm.milkyway.opengl.render.g2d.Color;

public class Context2DProvider extends ContextProvider
{

    protected static final int R = 0, G = 1, B = 2, A = 3;

    protected double rotation         = 0;
    protected double[] filter         = new double[] {1, 1, 1, 1};
    protected boolean flipX, flipY    = false;

    public void setRotation(double degree)
    {
        rotation = degree;
    }

    public void setOpacity(double v)
    {
        filter[A] = v;
    }

    public void setFlipX(boolean x)
    {
        flipX = x;
    }

    public void setFlipY(boolean y)
    {
        flipY = y;
    }

    public void setFilter(Color c)
    {
        setFilter(c.red(), c.green(), c.blue());
    }

    public void setFilter(double r, double g, double b)
    {
        filter[R] = r;
        filter[G] = g;
        filter[B] = b;
    }

    public double[] getFilter()
    {
        return filter;
    }

}
