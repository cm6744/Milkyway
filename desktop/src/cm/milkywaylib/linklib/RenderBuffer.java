package cm.milkywaylib.linklib;

import cm.milkywaygl.GLRenderable;
import cm.milkywaygl.maths.check.Box4;
import cm.milkywaygl.maths.check.Effect;
import cm.milkywaygl.maths.check.Vec2;
import cm.milkywaygl.render.GL;
import cm.milkywaygl.util.IntBuffer;

public class RenderBuffer implements GLRenderable
{

    protected IntBuffer texture;
    protected Box4 box4;
    protected Vec2 vec2;
    protected Effect effect;

    public RenderBuffer()
    {
        box4 = Box4.normal();
        vec2 = Vec2.normal();
        effect = Effect.normal();
    }

    public void tick()
    {
        box4().loc(vec2.applyX(box4.x()), vec2.applyY(box4.y()));
    }

    public void render()
    {
        GL.gl.cacheState();
        GL.gl.rotate(effect.rotation(), box4.x(), box4.y());
        GL.gl.opacity(effect.opacity());
        implRender();
        GL.gl.readState();
    }

    public void implRender()
    {
        GL.gl8.draw(texture, box4.xc(), box4.yc(), box4.width(), box4.height());
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

}
