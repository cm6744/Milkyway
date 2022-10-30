package cm.milkywaygl.render;

import cm.milkywaygl.render.inat.Context;
import cm.milkywaygl.render.wrapper.Color4;
import cm.milkywaygl.util.IntBuffer;
import cm.milkywaygl.util.IntHolder;
import cm.milkywaygl.util.container.Map;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.*;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;

public class GL3 implements GLObject
{

    private static final BoundingBox bbTest = new BoundingBox();
    private static final Vector3 bbCenter = new Vector3();
    private static final Vector3 bbDim = new Vector3();
    GL gl;
    ModelBatch batch;
    PerspectiveCamera camera;
    Environment env;
    IntHolder<Model> models = new IntHolder<>();
    IntHolder<ModelInstance> instances = new IntHolder<>();
    IntHolder<Attribute> attrs = new IntHolder<>();
    Map<Model, Material> mats = new Map<>();

    public GL3(GL g)
    {
        gl = g;
    }

    public void init()
    {
        batch = new ModelBatch();
        env = new Environment();
        env.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.1f, 0.1f, 0.1f, 1));
        env.set(new ColorAttribute(ColorAttribute.Fog, 0.3f, 0.3f, 0.3f, 1));
        camera = new PerspectiveCamera(100, (int) Context.width(), (int) Context.height());
        env.add(new DirectionalLight().set(0.8f, 0.8f, 0.8f, -1f, -0.8f, -0.8f));
    }

    public void dispose()
    {
        batch.dispose();
        for(int i = 0; i < models.size(); i++) {
            models.get(i).dispose();
        }
    }

    public void begin()
    {
        gl.viewPort();
        gl.begin();
        batch.begin(camera);
    }

    public void end()
    {
        batch.end();
        gl.end();
        gl.viewPortBack();
    }

    public void render(IntBuffer id, double x, double y, double z)
    {
        ModelInstance ins = instances.get(id);
        ins.transform.setToTranslation((float) x, (float) y, (float) z);

        ins.calculateBoundingBox(bbTest);
        bbTest.getCenter(bbCenter);
        bbTest.getDimensions(bbDim);
        float bbRad = bbDim.len() / 2f;

        if(camera.frustum.sphereInFrustum(bbCenter, bbRad)) {
            batch.render(ins, env);
        }
    }

    public IntBuffer genModel(double w, double h, double d)
    {
        ModelBuilder bd = new ModelBuilder();
        Material mat = new Material(new BlendingAttribute());
        Model md = bd.createBox((float) w, (float) h, (float) d, mat,
                                VertexAttributes.Usage.Position
                                        | VertexAttributes.Usage.Normal
                                        | VertexAttributes.Usage.TextureCoordinates
        );
        mats.put(md, mat);
        return models.gen(md);
    }

    private IntBuffer genAndBind(IntBuffer model, Attribute attr)
    {
        Material mat = mats.get(models.get(model));
        mat.set(attr);
        return attrs.gen(attr);
    }

    public IntBuffer modelBindTex(IntBuffer model, IntBuffer tex)
    {
        return genAndBind(model, TextureAttribute.createDiffuse(GL.gl2._natTex(tex)));
    }

    public IntBuffer modelBindColor(IntBuffer model, Color4 c4f)
    {
        return genAndBind(model, ColorAttribute.createDiffuse(c4f._nativeColor));
    }

    public IntBuffer createObj(IntBuffer model)
    {
        return instances.gen(new ModelInstance(models.get(model)));
    }

    Vector3 camPos = new Vector3();
    Vector3 camLook = new Vector3();

    public void cameraSight(double near, double far)
    {
        camera.far = (float) far;
        camera.near = (float) near;
        camera.update();
    }

    public void cameraLookPos(double x, double y, double z)
    {
        camera.lookAt((float) x, (float) y, (float) z);
        camera.update();
        camLook.set((float) x, (float) y, (float) z);
    }

    public void cameraLookPosTranslate(double x, double y, double z)
    {
        cameraLookPos((float) (x + camLook.x), (float) (y + camLook.y), (float) (z + camLook.z));
    }

    public void cameraPos(double x, double y, double z)
    {
        camera.position.set((float) x, (float) y, (float) z);
        camera.update();
        camPos = camera.position;
    }

    public void cameraPosTranslate(double x, double y, double z)
    {
        cameraLookPos((float) (x + camPos.x), (float) (y + camPos.y), (float) (z + camPos.z));
    }

}
