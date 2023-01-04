package cm.type2d.render.hud;

import cm.milkyway.opengl.render.g2d.Tex;
import cm.milkyway.opengl.render.g2d.Font;
import cm.milkyway.opengl.render.graphics.Graphics2D;
import cm.type2d.Load;
import cm.type2d.render.CtUtil;
import cm.type2d.render.InvRenderer;
import cm.type2d.render.PickableInvRenderer;
import cm.type2d.world.entity.Player;

public class Huds
{

    public static Huds GLOBAL;
    public static Font FONT_ALL = Load.loader.getFont("all");

    Tex tex;
    double days;
    SeasonLabel seasonLabel;
    PlayerLabel playerLabel;
    InvRenderer invRenderer;
    public double pInvLeft;
    public double pInvTop;
    public double pInvSpaceOrg;
    public double pInvSpace;
    public double pInvSize;
    public double pInvW;
    public double pInvH;

    public Huds(Tex b, Player player)
    {
        tex = b;
        GLOBAL = this;
        seasonLabel = new SeasonLabel();
        playerLabel = new PlayerLabel(player);
        invRenderer = new PickableInvRenderer(player.inv);
    }

    public void tick()
    {
        invRenderer.tick();
    }

    public void render(Graphics2D g)
    {
        double w = g.getContext().width();
        double h = g.getContext().height();
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
        CtUtil.ct(g, tex, w / 2, h - ih / 2, iw, ih, 0, 0, 242, 22);
        invRenderer.render(g, w / 2 - (iw / 2 - pInvSpaceOrg), h - (ih - pInvSpaceOrg), pInvSize, pInvSize, pInvSpace);
        //top indices(now disabled)
        //CtUtil.ct(tex, w / 2, h - ih / 2 * zoom, iw * zoom, ih * zoom, 0, ih, iw, ih);
        seasonLabel.render(g);
        playerLabel.render(g);
    }

}
