package cm.milkywaylib.base;

import cm.milkywaygl.Platform;
import cm.milkywaygl.TaskCaller;
import cm.milkywaygl.interfac.GLRenderable;
import cm.milkywaygl.interfac.GLTimeline;
import cm.milkywaygl.maths.check.Box4;
import cm.milkywaygl.maths.check.Effect;
import cm.milkywaygl.maths.check.Vec2;
import cm.milkywaygl.render.GL;
import cm.milkywaygl.render.wrapper.Area;
import cm.milkywaygl.util.container.List;
import cm.milkywaygl.util.container.Map;
import cm.milkywaytype.stg.Player;

public class RenderBuffer extends GLTimeline implements GLRenderable
{

    //default part.
    public static final String DEFAULT = "default";

    //null values default!
    protected Map<String, Area> textures = new Map<>();

    protected Box4 box4 = Box4.normalInset();
    protected Vec2 vec2 = Vec2.normal();
    protected Effect effect = Effect.normal();
    protected int time;
    protected boolean forRemove;

    public RenderBuffer()
    {
        if(!TaskCaller.isPreInitialized()) {
            Platform.error("cannot create buffer before pre-init end!");
        }
    }

    public void tick()
    {
        //ticking Areas...
        List<Area> lst = textures.toList();
        for(int i = 0; i < lst.size(); i++) {
            lst.get(i).tick();
        }

        super.tick();
    }

    public void tickThen()
    {
        box4().loc(vec2.applyX(box4.x()), vec2.applyY(box4.y()));
    }

    public void render()
    {
        GL.gl.save();

        GL.gl.curState().rotate(effect.rotation(), box4.x(), box4.y());
        GL.gl.curState().opacity(effect.opacity());
        double x = box4.xc();
        double y = box4.yc();
        double w = box4.width();
        double h = box4.height();
        renderThen(x, y, w, h);

        GL.gl.read();
    }

    public void renderThen(double x, double y, double w, double h)
    {
        if(texture() != null) {
            GL.gl2.dim(texture(), x, y, w, h);
        }
    }

    public Box4 box4()
    {
        return box4;
    }

    public Vec2 vec2()
    {
        return vec2;
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
        setTexture(tex, DEFAULT);
    }

    public void setTexture(Area tex, String part)
    {
        textures.put(part, tex);
    }

    public Area texture()
    {
        return texture(DEFAULT);
    }

    public Area texture(String part)
    {
        return textures.get(part);
    }

}
