package cm.milkyway.opengl.render.g3d;

import cm.milkyway.opengl.render.g2d.BufferTex;
import cm.milkyway.opengl.render.g2d.Color4;

/** May not be implemented in some backends! */
public interface Model3D
{

    Model3DObject newInstance();

    void bind(BufferTex texture);

    void bind(Color4 color4);

}
