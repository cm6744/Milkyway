package cm.backends.gdx.g3d;

import cm.milkyway.opengl.render.g3d.Model3DObject;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.ModelInstance;

public class ModelInsGdx implements Model3DObject
{

    public ModelInstance nativeIns;
    public Material material;

    public ModelInsGdx set(ModelInstance n, ModelGdx model)
    {
        nativeIns = n;
        material = n.getMaterial(model.material.id);
        return this;
    }

}
