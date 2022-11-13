package cm.milkywaygl.render.wrapper;

import cm.milkywaygl.maths.check.Box4;
import cm.milkywaygl.render.GL;
import cm.milkywaygl.util.IntBuffer;

/**
 * A mutable class!
 * So be careful
 */
public class Area
{

    public static Area vertex(IntBuffer tex, double u1, double v1, double u2, double v2)
    {
        return create(tex).vertex(u1, v1, u2, v2);
    }

    public static Area vertex01(IntBuffer tex, double u1, double v1, double u2, double v2)
    {
        return create(tex).vertex01(u1, v1, u2, v2);
    }

    public static Area dim(IntBuffer tex, double u1, double v1, double uw, double vh)
    {
        return create(tex).dim(u1, v1, uw, vh);
    }

    public static Area dim01(IntBuffer tex, double u1, double v1, double uw, double vh)
    {
        return create(tex).dim01(u1, v1, uw, vh);
    }

    public static Area create(IntBuffer tex)
    {
        return create().pushTexture(tex).full();
    }

    public static Area create()
    {
        return new Area();
    }

    //do not change its value
    //mustn't immediately link object when copying!!!
    Box4 uv = Box4.normalOffset();
    IntBuffer buffer = IntBuffer.create();
    //these too

    private Area pushTexture(IntBuffer buf)
    {
        buffer = buf;
        return this;
    }

    public IntBuffer texture()
    {
        return buffer;
    }

    private Area vertex(double u1, double v1, double u2, double v2)
    {
        double w = u2 - u1;
        double h = v2 - v1;
        uv.loc(u1, v1);
        uv.setSize(w, h);
        return this;
    }

    private Area vertex01(double u1, double v1, double u2, double v2)
    {
        return vertex(u1 * fw(), v1 * fh(), u2 * fw(), v2 * fh());
    }

    private Area dim01(double u1, double v1, double uw, double vh)
    {
        return vertex01(u1, v1, u1 + uw, v1 + vh);
    }

    private Area dim(double u1, double v1, double uw, double vh)
    {
        return vertex(u1, v1, u1 + uw, v1 + vh);
    }

    public Area full()
    {
        dim01(0, 0, 1, 1);
        return this;
    }

    public double fw()
    {
        return GL.gl2.texw(buffer);
    }

    public double fh()
    {
        return GL.gl2.texh(buffer);
    }

    public double w()
    {
        return uv.width();
    }

    public double h()
    {
        return uv.height();
    }

    public Box4 uv()
    {
        return uv;
    }

    public Area copy()
    {
        Area area = create(buffer);
        area.uv.copy(area.uv);
        return this;
    }

    //if necessary, extend
    public void tick()
    {
    }

    //You can extend Area, override this method, to make your own render effect
    //Such as AnimatedArea
    public void renderVertex(double x1, double y1, double x2, double y2)
    {
        GL.gl2.vertex(buffer, x1, y1, x2, y2, uv.xc(), uv.yc(), uv.xc2(), uv.yc2());
    }

}
