package cm.milkyway.opengl.render.g2d;

import cm.milkyway.Milkyway;

/**
 * All changing method are private
 * IMMUTABLE.
 */
public class AreaColored implements Area
{

    Color4 color;

    public static AreaColored create(Color4 c)
    {
        return create().push(c);
    }

    public static AreaColored create()
    {
        return new AreaColored();
    }

    private AreaColored push(Color4 c)
    {
        color = c;
        return this;
    }

    public BufferTex texture()
    {
        return null;
    }

    public double fw()
    {
        return 0;
    }

    public double fh()
    {
        return 0;
    }

    public double w()
    {
        return 0;
    }

    public double h()
    {
        return 0;
    }

    public AreaColored copy()
    {
        return create(color);
    }

    public void render(double x1, double y1, double w, double h)
    {
        Milkyway.glBase.state().color(color);
        Milkyway.glShape.dim(x1, y1, w, h);
        Milkyway.glBase.state().clear();
    }

}
