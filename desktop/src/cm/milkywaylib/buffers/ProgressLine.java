package cm.milkywaylib.buffers;

import cm.milkywaygl.render.GL;
import cm.milkywaylib.base.RenderBuffer;

public class ProgressLine extends RenderBuffer
{

    public static final String EMPTY = "empty";
    public static final String FULL = "full";

    double pro;

    public void value(double v)
    {
        pro = v;
    }

    public void upBy(double v)
    {
        pro += v;
    }

    public void renderThen(double x, double y, double w, double h)
    {
        GL.gl2.dim(texture(EMPTY), box4);
        GL.gl2.dim(texture(FULL), box4.xc(), box4.yc(), box4.width() * pro, box4.height());
    }

}
