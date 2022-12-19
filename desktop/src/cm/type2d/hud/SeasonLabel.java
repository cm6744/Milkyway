package cm.type2d.hud;

import cm.milkyway.Milkyway;
import cm.milkyway.opengl.render.g2d.BufferTex;
import cm.type2d.logic.Seasons;
import cm.type2d.logic.Times;

public class SeasonLabel
{

    public void render()
    {
        BufferTex tex = Huds.GLOBAL.tex;

        double sx = 5;//start x
        double sy = 5;//start y
        double bw = 76;//label w
        double bh = 8;//label h
        double bSide = 3;//side frame widths in labels.
        double space = 2;//spaces between labels.
        double ptrW = 4;
        double per1 = Times.dayPer();
        //daytime label
        Milkyway.gl2d.dim(tex, sx, sy, bw, bh, 0, 122, bw, bh);
        //pointer
        CtUtil.ct(tex, (sx + bSide + per1 * (bw - bSide * 2)), ((sy) + bh / 2), ptrW, bh, 77, 122, ptrW, bh);
        double per2 = Times.yearPer();
        //season label
        Milkyway.gl2d.dim(tex, sx, (sy + bh + space), bw, bh, 0, 132, bw, bh);
        //pointer
        CtUtil.ct(tex, (sx + bSide + per2 * (bw - bSide * 2)), ((sy + bh + space) + bh / 2), ptrW, bh, 77, 122, ptrW, bh);

        Milkyway.glBase.state().font(Huds.FONT_ALL);
        Milkyway.glText.text(Times.days + " Days", (sx + bw + space), sy, false);
        Milkyway.glText.text(Seasons.byDay().spellName(), (sx + bw + space), (sy + bh + space), false);
    }

}
