package cm.milkywaylib.linklib;

import cm.milkywaygl.GLRenderable;
import cm.milkywaygl.maths.check.Effect;
import cm.milkywaygl.maths.check.Vec3;
import cm.milkywaygl.render.GL;
import cm.milkywaygl.util.IntBuffer;

public class ModelBuffer implements GLRenderable
{

    Vec3 axisSpeed;
    Vec3 pos;
    Effect effect;
    IntBuffer bind;

    public ModelBuffer()
    {
        axisSpeed = Vec3.normal();
        pos = Vec3.normal();
        effect = Effect.normal();
    }

    public void tick()
    {
        pos.mvVec(axisSpeed.x(), axisSpeed.y(), axisSpeed.z());
    }

    public void render()
    {
        GL.gl.cacheState();
        GL.gl.opacity(effect.opacity());
        GL.gl3.render(bind, pos.x(), pos.y(), pos.z());
        GL.gl.readState();
    }

    public void pushBind(IntBuffer b)
    {
        bind = b;
    }

    public IntBuffer bind()
    {
        return bind;
    }

    public Vec3 axisSpeed()
    {
        return axisSpeed;
    }

    public Vec3 pos()
    {
        return pos;
    }

    public Effect effect()
    {
        return effect;
    }

}
