package cm.typestg;

import cm.milkyway.opengl.render.g2d.Tex;
import cm.milkyway.opengl.render.graphics.Graphics2D;
import cm.milkywayx.widgetx.scene.Scene;
import cm.milkyway.lang.maths.Mth;

import static cm.typestg.Scr.scr;

public class Roller extends Scene
{

    Tex Tex;
    Tex overlay;
    double spd;
    double yOffset;
    double w, h;

    public void rolling(Tex tex, double speed)
    {
        Tex = tex;
        spd = speed;
    }

    public void overlay(Tex over)
    {
        overlay = over;
    }

    public void overlayRender(Graphics2D g)
    {
        g.setOpacity(Mth.min(1, time() * 0.05));
        g.draw(overlay, scr);
    }

    public void rollingRender(Graphics2D g)
    {
        g.setOpacity(Mth.min(1, time() * 0.05));
        g.draw(Tex, scr.xc(), scr.yc(), scr.w(), scr.h(), 0, yOffset, w, h);
    }

    public void tickThen()
    {
        w = Tex.w();
        h = Tex.h() / 2;
        yOffset -= spd;
        if(yOffset > h) {
            yOffset -= h;
        }
        if(yOffset < 0) {
            yOffset += h;
        }
    }

    public void render(Graphics2D g)
    {
    }

}
