package cm.type2d.hud;

import cm.milkyway.Milkyway;
import cm.milkyway.opengl.render.g2d.BufferTex;
import cm.milkyway.opengl.render.g2d.Font2;
import cm.type2d.item.PlayerInv;
import cm.type2d.res.Load;

public class Huds
{

    public static Huds GLOBAL;
    public static Font2 FONT_ALL = Load.loader.getFont("all");

    BufferTex tex;
    double days;
    SeasonLabel seasonLabel;
    InvRenderer invRenderer;
    public double pInvLeft;
    public double pInvTop;
    public double pInvSpaceOrg;
    public double pInvSpace;
    public double pInvSize;
    public double pInvW;
    public double pInvH;

    public Huds(BufferTex b)
    {
        tex = b;
        GLOBAL = this;
        seasonLabel = new SeasonLabel();
        invRenderer = new PlayerInvRenderer(PlayerInv.GLOBAL);
    }

    public void tick()
    {
        invRenderer.tick();
    }

    public void render()
    {
        double w = Milkyway.glBase.width();
        double h = Milkyway.glBase.height();
        //inventory
        double ih = 44;
        double iw = 484;
        pInvSpace = 8;
        pInvSpaceOrg = 6;
        pInvSize = 32;
        pInvW = iw;
        pInvH = ih;
        pInvLeft = w / 2 - iw / 2;
        pInvTop = h - ih;
        CtUtil.ct(tex, w / 2, h - ih / 2, iw, ih, 0, 0, iw, ih);
        invRenderer.render(w / 2 - (iw / 2 - pInvSpaceOrg), h - (ih - pInvSpaceOrg), pInvSize, pInvSize, pInvSpace);
        //top indices(now disabled)
        //CtUtil.ct(tex, w / 2, h - ih / 2 * zoom, iw * zoom, ih * zoom, 0, ih, iw, ih);
        seasonLabel.render();
    }

}
