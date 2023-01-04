package cm.milkywayx.widgetx.base;

import cm.milkyway.opengl.render.g2d.Area;
import cm.milkyway.lang.container.map.Map;
import cm.milkyway.lang.maths.shapes.Rect;
import cm.milkyway.opengl.render.graphics.Graphics2D;
import cm.milkywayx.widgetx.Renderable2D;

public class RenderBuffer extends Timeline implements Renderable2D
{

    //null values default!
    protected Map<String, Area> textures = new Map<>();
    private Area defaultCache;

    protected Rect renderBox = new Rect();
    protected VecInfo vecInfo = VecInfo.normal();
    protected Effect effect = Effect.normal();
    protected boolean forRemove;

    public RenderBuffer()
    {
    }

    public void tickThen()
    {
        renderBox.loc(vecInfo.applyX(renderBox.x()), vecInfo.applyY(renderBox.y()));
    }

    public void render(Graphics2D g)
    {
        g.setRotation(effect.rotation());
        g.setOpacity(effect.opacity());

        double x = renderBox.xc();
        double y = renderBox.yc();
        double w = renderBox.w();
        double h = renderBox.h();

        renderThen(g, x, y, w, h);

        g.setRotation(0);
        g.setOpacity(1);
    }

    public void renderThen(Graphics2D g, double x, double y, double w, double h)
    {
        if(texture() != null) {
            texture().render(g, x, y, w, h);
        }
    }

    public Rect box()
    {
        return renderBox;
    }

    public VecInfo vec()
    {
        return vecInfo;
    }

    public Effect effect()
    {
        return effect;
    }

    public void cancelRemove()
    {
        forRemove = false;
    }

    public void remove()
    {
        forRemove = true;
    }

    public boolean isForRemove()
    {
        return forRemove;
    }

    //TEXTURES

    public void setTexture(Area tex)
    {
        defaultCache = tex;
    }

    public void setTexture(Area tex, String part)
    {
        textures.put(part, tex);
    }

    public Area texture()
    {
        return defaultCache;
    }

    public Area texture(String part)
    {
        return textures.get(part);
    }

}
