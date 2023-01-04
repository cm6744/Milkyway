package cm.type2d.render;

import cm.milkyway.lang.container.list.List;
import cm.milkyway.opengl.render.g2d.Tex;
import cm.milkyway.opengl.render.graphics.Graphics2D;
import cm.milkywayx.widgetx.Renderable2D;
import cm.milkywayx.widgetx.Tickable;

public class CtUtil
{

    public static void ct(Graphics2D g, Tex tex, double x, double y, double w, double h, double u, double v, double uw, double vh)
    {
        g.draw(tex, x - w / 2, y - h / 2, w, h, u, v, uw, vh);
    }

    public static void ct(Graphics2D g, Tex tex, double x, double y, double w, double h)
    {
        g.draw(tex, x - w / 2, y - h / 2, w, h);
    }

    public static void fastRender(Graphics2D g, List<? extends Renderable2D> bufs)
    {
        Renderable2D buf;
        for(int i = bufs.last(); i >= 0; i--) {
            buf = bufs.get(i);
            if(buf == null) {
                continue;
            }
            buf.render(g);
        }
    }

    public static void fastTick(List<? extends Tickable> bufs)
    {
        Tickable buf;
        for(int i = bufs.last(); i >= 0; i--) {
            buf = bufs.get(i);
            if(buf == null) {
                continue;
            }
            buf.tick();
        }
    }

}
