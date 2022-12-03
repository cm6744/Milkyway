package cm.milkywaygl.render.g3d;

import cm.milkywaygl.render.g2d.BufferTex;
import cm.milkywaygl.render.g2d.Color4;

public interface Model3D
{

    Model3DObject newInstance();

    void bind(BufferTex texture);

    void bind(Color4 color4);

}
