package cm.milkyway.opengl.render.graphics;

import cm.milkyway.lang.maths.shapes.Rect;
import cm.milkyway.opengl.render.g2d.*;

public abstract class Graphics2D extends Context2DProvider
{

    public abstract void draw(Tex tex, double x, double y, double w, double h, double u, double v, double uw, double vh);

    public abstract void draw(Tex tex, double x, double y, double w, double h);

    public void draw(Tex tex, Rect r)
    {
        draw(tex, r.xc(), r.yc(), r.w(), r.h());
    }

    public abstract void draw(Tex tex, double x, double y);

    public abstract void drawRect(Color c, double x, double y, double w, double h);

    public void drawRect(Color c, Rect r)
    {
        drawRect(c, r.xc(), r.yc(), r.w(), r.h());
    }

    public abstract void drawOval(Color c, double x, double y, double w, double h);

    public abstract void drawLine(Color c, double x, double y, double xt, double yt);

    public abstract void drawText(Font f, Color c, String s, double x, double y, boolean center);

    //some default implements. Made codes cleaner.

    public void draw(Area area, double x, double y, double w, double h)
    {
        area.render(this, x, y, w, h);
    }

    public void draw(Area area, Rect box)
    {
        area.render(this, box.xc(), box.yc(), box.w(), box.h());
    }

    public void draw(Text text, double x, double y, boolean center)
    {
        text.render(this, x, y, center);
    }

}

