package cm.backends.lwjgl;

import cm.milkyway.opengl.render.g3d.Model3DObject;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.ModelInstance;

public class LwjglModelObject implements Model3DObject
{

    public ModelInstance nativeIns;
    public Material material;

    public LwjglModelObject set(ModelInstance n, LwjglModel model)
    {
        nativeIns = n;
        material = n.getMaterial(model.material.id);
        return this;
    }

}
