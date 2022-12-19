package cm.milkywayx.widgetx.widget;

import cm.milkyway.Milkyway;
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

    public void renderThen(double x, double y, double w, double h)
    {
        Milkyway.gl2d.dim(texture(EMPTY), renderBox);
        Milkyway.gl2d.dim(texture(FULL), renderBox.xc(), renderBox.yc(), renderBox.w() * pro, renderBox.h());
    }

}
