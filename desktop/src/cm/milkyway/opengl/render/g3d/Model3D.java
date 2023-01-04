package cm.milkyway.opengl.render.g3d;

import cm.milkyway.lang.Disposable;
import cm.milkyway.opengl.render.g2d.Tex;
import cm.milkyway.opengl.render.g2d.Color;

/** May not be implemented in some backends! */
public interface Model3D extends Disposable
{

    Model3DObject newInstance();

    void bind(Tex texture);

    void bind(Color color4);

}
