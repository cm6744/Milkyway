package cm.milkywaygl.render;

import cm.milkywaygl.render.g3d.Camera;
import cm.milkywaygl.render.g3d.Model3D;
import cm.milkywaygl.render.g3d.Model3DObject;

public interface GL3D extends Batch
{

    void render(Model3DObject id, double x, double y, double z);

    Model3D genModel(double w, double h, double d);

    Camera camera();

}
