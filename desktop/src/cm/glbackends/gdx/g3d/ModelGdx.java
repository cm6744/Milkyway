package cm.glbackends.gdx.g3d;

import cm.milkywaygl.TaskCaller;
import cm.glbackends.gdx.g2d.BufferTexGdx;
import cm.glbackends.gdx.g2d.Color4Gdx;
import cm.milkywaygl.render.g2d.BufferTex;
import cm.milkywaygl.render.g2d.Color4;
import cm.milkywaygl.render.g3d.Model3D;
import cm.milkywaygl.render.g3d.Model3DObject;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;

public class ModelGdx implements Model3D
{

    public com.badlogic.gdx.graphics.g3d.Model nativeMd;
    public Material material;

    public ModelGdx set(com.badlogic.gdx.graphics.g3d.Model md,
                        Material m)
    {
        nativeMd = md;
        material = m;
        TaskCaller.register(md::dispose, TaskCaller.DISPOSE);
        return this;
    }

    public Model3DObject newInstance()
    {
        return new ModelInsGdx().set(new ModelInstance(nativeMd), this);
    }

    public void bind(Color4 color4)
    {
        material.set(ColorAttribute.createDiffuse(((Color4Gdx) color4)._nativeColor));
    }

    public void bind(BufferTex texture)
    {
        material.set(TextureAttribute.createDiffuse(((BufferTexGdx) texture).nativeTex));
    }

}
