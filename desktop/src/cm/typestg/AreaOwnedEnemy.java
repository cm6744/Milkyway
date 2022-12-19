package cm.typestg;

import cm.milkyway.Milkyway;
import cm.milkyway.lang.maths.Mth;
import cm.milkyway.opengl.render.g2d.AreaAnimated;
import cm.milkyway.opengl.render.g2d.AreaStatic;
import cm.milkyway.opengl.render.g2d.BufferTex;

public class AreaOwnedEnemy extends AreaStatic
{

    BufferTex magic;
    AreaAnimated glowing;

    double rot = Mth.random() < 0.5 ? -5 : 5;
    double rotation;

    public static AreaStatic create(BufferTex tex, BufferTex mag)
    {
        AreaOwnedEnemy owned = new AreaOwnedEnemy();
        owned.magic = mag;
        owned.glowing = new AreaAnimated(tex, 4, 1, 0).perTime(5);
        return owned;
    }

    public void render(double x1, double y1, double w, double h)
    {
        Milkyway.glBase.state().rotate(rotation += rot);
        Milkyway.gl2d.dim(magic, x1 - w / 2, y1 - h / 2, w * 2, h * 2);
        Milkyway.glBase.state().clear();
        glowing.render(x1, y1, w, h);
    }

    public BufferTex texture()
    {
        return glowing.texture();
    }

    public double fw()
    {
        return glowing.fw();
    }

    public double fh()
    {
        return glowing.fh();
    }

    public double w()
    {
        return glowing.w();
    }

    public double h()
    {
        return glowing.h();
    }

    public AreaOwnedEnemy copy()
    {
        AreaOwnedEnemy ownedEnemy = new AreaOwnedEnemy();
        ownedEnemy.glowing = glowing.copy();
        ownedEnemy.magic = magic;
        return ownedEnemy;
    }

}
