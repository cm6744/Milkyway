package cm.milkywaylib.linklib;

import cm.milkywaygl.inter.GLRenderable;
import cm.milkywaygl.inter.GLTimeline;
import cm.milkywaygl.maths.check.Box4;
import cm.milkywaygl.maths.check.Effect;
import cm.milkywaygl.maths.check.Vec2;
import cm.milkywaygl.render.GL;
import cm.milkywaygl.util.IntBuffer;

public class RenderBuffer extends GLTimeline implements GLRenderable
{

    protected IntBuffer texture;
    protected Box4 box4;
    protected Vec2 vec2;
    protected Effect effect;
    protected int time;
    protected boolean forRemove;

    public RenderBuffer()
    {
        box4 = Box4.normal();
        vec2 = Vec2.normal();
        effect = Effect.normal();
    }

    public void tickThen()
    {
        box4().loc(vec2.applyX(box4.x()), vec2.applyY(box4.y()));
    }

    public void render()
    {
        GL.gl.cacheState();
        GL.gl.curState().rotate(effect.rotation(), box4.x(), box4.y());
        GL.gl.curState().opacity(effect.opacity());
        renderThen();
        GL.gl.readState();
    }

    public void renderThen()
    {
        GL.gl2.dim(texture, box4.xc(), box4.yc(), box4.width(), box4.height());
    }

    public void pushTexture(IntBuffer tex)
    {
        texture = tex;
    }

    public IntBuffer texture()
    {
        return texture;
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

}
