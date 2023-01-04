package cm.milkyway.opengl.render.graphics;

import cm.milkyway.opengl.render.g3d.Camera;
import cm.milkyway.opengl.render.g3d.Model3D;
import cm.milkyway.opengl.render.g3d.Model3DObject;

/** May not be implemented in some backends! */
public abstract class Graphics3D extends Context3DProvider
{

    public abstract void draw(Model3DObject o, double x, double y, double z);

    public abstract Camera getCamera();

    public abstract Model3D genModel(double w, double h, double d);

}
