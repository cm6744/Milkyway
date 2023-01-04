package cm.typestg;

import cm.milkyway.lang.maths.Mth;
import cm.milkyway.opengl.render.g2d.Tex;
import cm.milkyway.opengl.render.graphics.Graphics2D;
import cm.milkyway.opengl.render.g2d.AreaAnimated;
import cm.milkyway.opengl.render.g2d.AreaStatic;
import cm.milkyway.opengl.render.g2d.Tex;

public class AreaOwnedEnemy extends AreaStatic
{

    Tex magic;
    AreaAnimated glowing;

    double rot = Mth.random() < 0.5 ? -5 : 5;
    double rotation;

    public static AreaStatic create(Tex tex, Tex mag)
    {
        AreaOwnedEnemy owned = new AreaOwnedEnemy();
        owned.magic = mag;
        owned.glowing = new AreaAnimated(tex, 4, 1, 0).perTime(5);
        return owned;
    }

    public void render(Graphics2D g, double x, double y, double w, double h)
    {
        g.setRotation(rotation += rot);
        g.draw(magic, x - w / 2, y - h / 2, w * 2, h * 2);
        g.setRotation(0);
        glowing.render(g, x, y, w, h);
    }

    public Tex texture()
    {
        return glowing.texture();
    }

    public AreaOwnedEnemy copy()
    {
        AreaOwnedEnemy ownedEnemy = new AreaOwnedEnemy();
        ownedEnemy.glowing = glowing.copy();
        ownedEnemy.magic = magic;
        return ownedEnemy;
    }

}
