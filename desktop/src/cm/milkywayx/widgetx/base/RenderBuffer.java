package cm.milkywayx.widgetx.base;

import cm.milkyway.Milkyway;
import cm.milkyway.opengl.render.g2d.Area;
import cm.milkyway.lang.container.Map;
import cm.milkyway.physics.shapes.Rect;

public class RenderBuffer extends Timeline
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

    public void render()
    {
        Milkyway.glBase.state().clear();

        Milkyway.glBase.state().rotate(effect.rotation());
        Milkyway.glBase.state().opacity(effect.opacity());

        double x = renderBox.xc();
        double y = renderBox.yc();
        double w = renderBox.w();
        double h = renderBox.h();

        renderThen(x, y, w, h);

        Milkyway.glBase.state().clear();
    }

    public void renderThen(double x, double y, double w, double h)
    {
        if(texture() != null) {
            Milkyway.gl2d.dim(texture(), x, y, w, h);
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
