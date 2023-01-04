package cm.backends.lwjgl;

import cm.milkyway.lang.maths.Mth;
import cm.milkyway.opengl.render.Window;
import cm.milkyway.opengl.render.g3d.Camera;
import cm.milkyway.opengl.render.g3d.Model3D;
import cm.milkyway.opengl.render.g3d.Model3DObject;
import cm.milkyway.opengl.render.graphics.Graphics3D;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.BlendingAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.environment.DirectionalLight;
import com.badlogic.gdx.graphics.g3d.utils.DefaultShaderProvider;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.math.collision.BoundingBox;

import static com.badlogic.gdx.math.Matrix4.*;

public class LwjglGraphics3D extends Graphics3D implements LwjglBlockedQueue.LwjglBatch
{

    //CONST, USING GET MODEL DATA
    private static final BoundingBox bbTest = new BoundingBox();
    private static final Vector3 bbCenter = new Vector3();
    private static final Vector3 bbDim = new Vector3();

    ModelBatch batch;
    PerspectiveCamera camera;
    Environment env;
    LwjglCamera cameraLwjgl;

    public LwjglGraphics3D()
    {
        context = LwjglEnvironment.context;
        batch = new ModelBatch();
        env = new Environment();
        env.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.1f, 0.1f, 0.1f, 1));
        env.add(new DirectionalLight().set(0.5f, 0.5f, 0.7f, -1f, -0f, -1f));
        Window win = context.getWindow();
        camera = new PerspectiveCamera(100, (int) win.width(), (int) win.height());
        cameraLwjgl = new LwjglCamera(camera);

        LwjglEnvironment.autoDispose(this);
    }

    public void nbegin()
    {
        batch.begin(camera);
    }

    public void nend()
    {
        batch.end();
    }

    public void dispose()
    {
        batch.dispose();
    }

    public void draw(Model3DObject o, double x, double y, double z)
    {
        LwjglBlockedQueue.ensure(this);
        ((LwjglGraphicsContext) context).viewportByDefault();

        ModelInstance ins = ((LwjglModelObject) o).nativeIns;
        ins.transform.setToTranslation((float) x, (float) y, (float) z);

        ins.calculateBoundingBox(bbTest);
        bbTest.getCenter(bbCenter);
        bbTest.getDimensions(bbDim);
        float bbRad = bbDim.len() / 2f;

        if(camera.frustum.sphereInFrustum(bbCenter, bbRad)) {
            float[] xyz = ins.transform.val;
            float dist = camera.position.dst(xyz[M03], xyz[M13], xyz[M23]);
            BlendingAttribute attr = ((LwjglModelObject) o).material.get(BlendingAttribute.class, BlendingAttribute.Type);
            attr.opacity = (float) (distancedFog(dist));
            batch.render(ins, env);
        }
    }

    private double distancedFog(double dist)
    {
        if(dist >= cameraLwjgl.fogFrom) {
            return Mth.max(0, 1 - (dist - cameraLwjgl.fogFrom) * 0.001);
        }
        return 1;
    }

    public Camera getCamera()
    {
        return cameraLwjgl;
    }

    public Model3D genModel(double w, double h, double d)
    {
        ModelBuilder bd = new ModelBuilder();
        Material mat = new Material(new BlendingAttribute());
        Model md = bd.createBox((float) w, (float) h, (float) d, mat,
                                VertexAttributes.Usage.Position
                                        | VertexAttributes.Usage.Normal
                                        | VertexAttributes.Usage.TextureCoordinates
        );
        return new LwjglModel().set(md, mat);
    }

}
