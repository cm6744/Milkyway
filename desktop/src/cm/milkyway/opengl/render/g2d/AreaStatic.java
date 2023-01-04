package cm.milkyway.opengl.render.g2d;

import cm.milkyway.lang.maths.shapes.Flat;
import cm.milkyway.opengl.render.graphics.Graphics2D;

/**
 * All changing method are private
 * IMMUTABLE.
 */
public class AreaStatic implements Area
{

    public static AreaStatic vertex(Tex tex, double u1, double v1, double u2, double v2)
    {
        return create(tex).vertex(u1, v1, u2, v2);
    }

    public static AreaStatic vertex01(Tex tex, double u1, double v1, double u2, double v2)
    {
        return create(tex).vertex01(u1, v1, u2, v2);
    }

    public static AreaStatic dim(Tex tex, double u1, double v1, double uw, double vh)
    {
        return create(tex).dim(u1, v1, uw, vh);
    }

    public static AreaStatic dim01(Tex tex, double u1, double v1, double uw, double vh)
    {
        return create(tex).dim01(u1, v1, uw, vh);
    }

    public static AreaStatic create(Tex tex)
    {
        return create().pushTexture(tex).full();
    }

    public static AreaStatic create()
    {
        return new AreaStatic();
    }

    //do not change its value
    //mustn't immediately link object when copying!!!
    Flat uv = Flat.normal();
    Tex buffer;
    //these too

    protected AreaStatic pushTexture(Tex buf)
    {
        buffer = buf;
        return this;
    }

    public Tex texture()
    {
        return buffer;
    }

    protected AreaStatic vertex(double u1, double v1, double u2, double v2)
    {
        double w = u2 - u1;
        double h = v2 - v1;
        uv.loc(u1, v1);
        uv.resize(w, h);
        return this;
    }

    protected AreaStatic vertex01(double u1, double v1, double u2, double v2)
    {
        return vertex(u1 * buffer.w(), v1 * buffer.h(), u2 * buffer.w(), v2 * buffer.h());
    }

    protected AreaStatic dim01(double u1, double v1, double uw, double vh)
    {
        return vertex01(u1, v1, u1 + uw, v1 + vh);
    }

    protected AreaStatic dim(double u1, double v1, double uw, double vh)
    {
        return vertex(u1, v1, u1 + uw, v1 + vh);
    }

    protected AreaStatic full()
    {
        dim01(0, 0, 1, 1);
        return this;
    }

    public AreaStatic copy()
    {
        AreaStatic area = create(buffer);
        area.uv.copy(uv);
        return area;
    }

    //You can extend AreaStatic, override this method, to make your own render effect
    //Such as AreaAnimated
    public void render(Graphics2D g, double x, double y, double w, double h)
    {
        g.draw(buffer, x, y, w, h, uv.x, uv.y, uv.w, uv.h);
    }

}
