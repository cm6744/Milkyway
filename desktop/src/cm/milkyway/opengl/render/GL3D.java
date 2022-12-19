package cm.milkyway.opengl.render;

import cm.milkyway.opengl.render.g3d.Camera;
import cm.milkyway.opengl.render.g3d.Model3D;
import cm.milkyway.opengl.render.g3d.Model3DObject;

/** May not be implemented in some backends! */
public interface GL3D extends Batch
{

    void render(Model3DObject id, double x, double y, double z);

    Model3D genModel(double w, double h, double d);

    Camera camera();

}
