package cm.milkywayx.widgetx.base;

import cm.milkyway.opengl.render.g3d.Model3DObject;
import cm.milkyway.lang.maths.shapes.Vec3;
import cm.milkyway.opengl.render.graphics.Graphics3D;
import cm.milkywayx.widgetx.Renderable3D;

public class ModelBuffer implements Renderable3D
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
        pos.add(axisSpeed.x(), axisSpeed.y(), axisSpeed.z());
    }

    public void render(Graphics3D g)
    {
        g.draw(bind, pos.x(), pos.y(), pos.z());
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
