package cm.glbackends.gdx;

import cm.glbackends.gdx.g2d.BufferTexGdx;
import cm.glbackends.gdx.g2d.Color4Gdx;
import cm.glbackends.gdx.g2d.FontTypeGdx;
import cm.milkywaygl.render.g2d.BufferTex;
import cm.milkywaygl.render.g2d.Color4;
import cm.milkywaygl.render.g2d.FontType;
import cm.milkywaygl.render.g2d.Graphics;

public class GraphicsGdx implements Graphics
{

    public BufferTex newTex()
    {
        return new BufferTexGdx();
    }

    public Color4 newColor(double r, double g, double b, double a)
    {
        return new Color4Gdx(r, g, b, a);
    }

    public Color4 newColor(double r, double g, double b)
    {
        return new Color4Gdx(r, g, b, 1);
    }

    public FontType newType(String ttf, String desc)
    {
        return new FontTypeGdx(ttf, desc);
    }

}
