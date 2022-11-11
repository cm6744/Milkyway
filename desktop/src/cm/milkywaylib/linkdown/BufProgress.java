package cm.milkywaylib.linkdown;

import cm.milkywaygl.render.GL;
import cm.milkywaygl.render.wrapper.Color4;
import cm.milkywaygl.text.JsonFile;
import cm.milkywaylib.linklib.RenderBuffer;

public class BufProgress extends RenderBuffer
{

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
        if(texture != null) {
            GL.gl2.dim01(texture, x, y, w, h, 0, 0, 1, 1 / 2.0);
            GL.gl2.dim01(texture, x, y, w * pro, h, 0, 1 / 2.0, 1, 1 / 2.0);
        }
    }

}
