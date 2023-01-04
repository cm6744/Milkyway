package cm.milkywayx.widgetx.widget;

import cm.milkyway.opengl.render.graphics.Graphics2D;
import cm.milkywayx.widgetx.base.RenderBuffer;

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

    public void renderThen(Graphics2D g, double x, double y, double w, double h)
    {
        g.draw(texture(EMPTY), renderBox);
        g.draw(texture(FULL), renderBox.xc(), renderBox.yc(), renderBox.w() * pro, renderBox.h());
    }

}
