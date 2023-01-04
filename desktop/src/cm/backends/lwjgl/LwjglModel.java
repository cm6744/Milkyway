package cm.backends.lwjgl;

import cm.milkyway.opengl.render.g2d.Tex;
import cm.milkyway.opengl.render.g2d.Color;
import cm.milkyway.opengl.render.g3d.Model3D;
import cm.milkyway.opengl.render.g3d.Model3DObject;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;

public class LwjglModel implements Model3D
{

    public Model nativeMd;
    public Material material;

    public LwjglModel set(Model md, Material m)
    {
        nativeMd = md;
        material = m;

        LwjglEnvironment.autoDispose(this);

        return this;
    }

    public void dispose()
    {
        nativeMd.dispose();
    }

    public Model3DObject newInstance()
    {
        return new LwjglModelObject().set(new ModelInstance(nativeMd), this);
    }

    public void bind(Color c)
    {
        material.set(ColorAttribute.createDiffuse((float) c.red(), (float) c.green(), (float) c.blue(), (float) c.alpha()));
    }

    public void bind(Tex texture)
    {
        material.set(TextureAttribute.createDiffuse(((LwjglTexture) texture).tex));
    }

}
