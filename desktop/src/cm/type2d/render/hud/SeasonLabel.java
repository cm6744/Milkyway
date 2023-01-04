package cm.type2d.render.hud;

import cm.milkyway.opengl.render.g2d.Tex;
import cm.milkyway.opengl.render.g2d.Color;
import cm.milkyway.opengl.render.graphics.Graphics2D;
import cm.type2d.render.CtUtil;
import cm.type2d.world.time.Seasons;
import cm.type2d.world.time.Times;

public class SeasonLabel
{

    public void render(Graphics2D g)
    {
        Tex tex = Huds.GLOBAL.tex;

        double sx = 3;//start x
        double sy = 5;//start y
        double bw = 152;//label w
        double bh = 16;//label h
        double bSide = 3;//side frame widths in labels.
        double space = 2;//spaces between labels.
        double ptrW = 4;
        double per1 = Times.dayPer();
        //daytime label
        g.draw(tex, sx, sy, bw, bh, 0, 122, 76, 8);
        //pointer
        CtUtil.ct(g, tex, (sx + bSide + per1 * (bw - bSide * 2)), ((sy) + bh / 2), ptrW, bh, 77, 122, ptrW, 8);
        //text
        g.drawText(Huds.FONT_ALL, Color.C1111, Times.days + " Days", (sx + bw + space), sy, false);

        sy += space + bh;

        double per2 = Times.yearPer();
        //season label
        g.draw(tex, sx, sy, bw, bh, 0, 132, 76, 8);
        //pointer
        CtUtil.ct(g, tex, (sx + bSide + per2 * (bw - bSide * 2)), (sy + bh / 2), ptrW, bh, 77, 122, ptrW, 8);
        //text
        g.drawText(Huds.FONT_ALL, Color.C1111, Seasons.byDay().spellName(), (sx + bw + space), sy, false);

    }

}
