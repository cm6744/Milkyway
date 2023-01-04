package cm.milkyway.opengl.render.g2d;

import cm.milkyway.opengl.render.graphics.Graphics2D;

/**
 * All changing method are private
 * IMMUTABLE.
 */
public class AreaColored implements Area
{

    Color color;

    public static AreaColored create(Color c)
    {
        return create().push(c);
    }

    public static AreaColored create()
    {
        return new AreaColored();
    }

    private AreaColored push(Color c)
    {
        color = c;
        return this;
    }

    public Tex texture()
    {
        return null;
    }

    public AreaColored copy()
    {
        return create(color);
    }

    public void render(Graphics2D g, double x, double y, double w, double h)
    {
        g.drawRect(color, x, y, w, h);
    }

}
