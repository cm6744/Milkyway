package cm.type2d.render.hud;

import cm.milkyway.opengl.render.g2d.Tex;
import cm.milkyway.opengl.render.graphics.Graphics2D;
import cm.type2d.world.entity.LivingAttribute;
import cm.type2d.world.entity.Player;

public class PlayerLabel
{

    Player player;

    public PlayerLabel(Player p)
    {
        player = p;
    }

    public void render(Graphics2D g)
    {
        Tex tex = Huds.GLOBAL.tex;

        double sx = 3;//start x
        double sy = 525;//start y
        double bw = 152;//label w
        double bh = 16;//label h
        double bSide = 3;//side frame widths in labels.
        double space = 2;//spaces between labels.
        double ptrW = 4;
        LivingAttribute a = player.getAttribute();
        double per1 = a.health / a.healthMax;
        //label health
        g.draw(tex, sx, sy, bw, bh, 0, 145, 76, 8);
        g.draw(tex, sx, sy, bw * per1, bh, 0, 165, 76 * per1, 8);
        double per2 = a.hunger / a.hungerMax;
        sy += space + bh;
        g.draw(tex, sx, sy, bw, bh, 0, 145, 76, 8);
        g.draw(tex, sx, sy, bw * per2, bh, 0, 176, 76 * per2, 8);
        double per3 = a.thirst / a.thirstMax;
        sy += space + bh;
        g.draw(tex, sx, sy, bw, bh, 0, 145, 76, 8);
        g.draw(tex, sx, sy, bw * per3, bh, 0, 187, 76 * per3, 8);
        double per4 = a.sanity / a.sanityMax;
        sy += space + bh;
        g.draw(tex, sx, sy, bw, bh, 0, 145, 76, 8);
        g.draw(tex, sx, sy, bw * per4, bh, 0, 198, 76 * per4, 8);

    }

}
