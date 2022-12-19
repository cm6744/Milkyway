package cm.milkyway.opengl.render.g2d;

import cm.milkyway.Milkyway;
import cm.milkyway.physics.shapes.Flat;

/**
 * All changing method are private
 * IMMUTABLE.
 */
public class AreaStatic implements Area
{

    public static AreaStatic vertex(BufferTex tex, double u1, double v1, double u2, double v2)
    {
        return create(tex).vertex(u1, v1, u2, v2);
    }

    public static AreaStatic vertex01(BufferTex tex, double u1, double v1, double u2, double v2)
    {
        return create(tex).vertex01(u1, v1, u2, v2);
    }

    public static AreaStatic dim(BufferTex tex, double u1, double v1, double uw, double vh)
    {
        return create(tex).dim(u1, v1, uw, vh);
    }

    public static AreaStatic dim01(BufferTex tex, double u1, double v1, double uw, double vh)
    {
        return create(tex).dim01(u1, v1, uw, vh);
    }

    public static AreaStatic create(BufferTex tex)
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
    BufferTex buffer = Milkyway.graphics.newTex();
    //these too

    protected AreaStatic pushTexture(BufferTex buf)
    {
        buffer = buf;
        return this;
    }

    public BufferTex texture()
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
        return vertex(u1 * fw(), v1 * fh(), u2 * fw(), v2 * fh());
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

    public double fw()
    {
        return Milkyway.gl2d.texw(buffer);
    }

    public double fh()
    {
        return Milkyway.gl2d.texh(buffer);
    }

    public double w()
    {
        return uv.width();
    }

    public double h()
    {
        return uv.height();
    }

    public AreaStatic copy()
    {
        AreaStatic area = create(buffer);
        area.uv.copy(uv);
        return area;
    }

    //You can extend AreaStatic, override this method, to make your own render effect
    //Such as AreaAnimated
    public void render(double x1, double y1, double w, double h)
    {
        Milkyway.gl2d.dim(buffer, x1, y1, w, h, uv.x, uv.y, uv.w, uv.h);
    }

}
