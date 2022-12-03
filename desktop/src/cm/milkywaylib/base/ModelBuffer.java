package cm.milkywaylib.base;

import cm.milkywaygl.Milkyway;
import cm.milkywaygl.render.g3d.Model3DObject;
import cm.milkywaytool.physics.Vec3;

public class ModelBuffer
{

    Vec3 axisSpeed;
    Vec3 pos;
    Effect effect;
    Model3DObject bind;

    public ModelBuffer()
    {
        axisSpeed = Vec3.normal();
        pos = Vec3.normal();
        effect = Effect.normal();
    }

    public void tick()
    {
        pos.trs(axisSpeed.x(), axisSpeed.y(), axisSpeed.z());
    }

    public void render()
    {
        Milkyway.glBase.curState().clear();

        Milkyway.glBase.curState().opacity(effect.opacity());
        Milkyway.gl3d.render(bind, pos.x(), pos.y(), pos.z());

        Milkyway.glBase.curState().clear();
    }

    public void pushBind(Model3DObject b)
    {
        bind = b;
    }

    public Model3DObject bind()
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
