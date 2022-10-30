package cm.milkywaygl.render.nnat;

import cm.milkywaygl.maths.check.Box4;
import cm.milkywaygl.render.GL;
import cm.milkywaygl.render.GL2;
import cm.milkywaygl.render.wrapper.Color4;
import cm.milkywaygl.render.wrapper.Font2;
import cm.milkywaygl.util.IntBuffer;

public class GL8
{

    public GL2 gl2;

    public GL8(GL2 g)
    {
        gl2 = g;
    }

    public void drawText(String text, double x, double y, boolean center)
    {
        gl2.drawText(text, x, y, center);
    }

    public void drawTextShadowed(String text, double x, double y, double ofs, Font2 shadow, boolean center)
    {
        GL.gl.cacheState();
        GL.gl.font(shadow);
        gl2.drawText(text, x + ofs, y + ofs, center);
        GL.gl.readState();
        drawText(text, x, y, center);
    }

    public void drawVX(IntBuffer id, double x, double y, double x2, double y2, double u, double v, double u2, double v2)
    {
        gl2.draw(id, x, y, x2, y2, u, v, u2, v2);
    }

    public void drawUV(IntBuffer id, double x, double y, double w, double h, double u, double v, double uw, double vh)
    {
        drawVX(id, x, y, x + w, y + h, u, v, u + uw, v + vh);
    }

    public void drawUVPer(IntBuffer id, double x, double y, double w, double h, double u, double v, double uw, double vh)
    {
        double wi = gl2.texw(id);
        double hi = gl2.texh(id);
        drawUV(id, x, y, w, h, u * wi, v * hi, wi * uw, hi * vh);
    }

    public void draw(IntBuffer id, double x, double y, double w, double h)
    {
        drawUV(id, x, y, w, h, 0, 0, gl2.texw(id), gl2.texh(id));
    }

    public void draw(IntBuffer id, double x, double y)
    {
        draw(id, x, y, gl2.texw(id), gl2.texh(id));
    }

    //MATHS.BOX4 TO DRAW

    public void draw(IntBuffer id, Box4 b)
    {
        draw(id, b.xc(), b.yc(), b.width(), b.height());
    }

    public void draw(IntBuffer id, Box4 b, Box4 uv)
    {
        drawUV(id, b.xc(), b.yc(), b.width(), b.height(), uv.xc(), uv.yc(), uv.width(), uv.height());
    }

}
