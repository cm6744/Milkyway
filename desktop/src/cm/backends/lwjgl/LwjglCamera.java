package cm.backends.lwjgl;

import cm.milkyway.opengl.render.g3d.Camera;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.math.Vector3;

public class LwjglCamera implements Camera
{

    public PerspectiveCamera camera;
    public double fogFrom;
    Vector3 camPos;
    Vector3 camLook;

    public LwjglCamera(PerspectiveCamera c)
    {
        camera = c;
        camPos = c.position;
        camLook = c.direction;
    }

    public void sight(double near, double far, double fogDist)
    {
        camera.far = (float) far;
        camera.near = (float) near;
        camera.update();
        fogFrom = fogDist;
    }

    public void lookPos(double x, double y, double z)
    {
        camera.lookAt((float) x, (float) y, (float) z);
        camera.update();
        camLook.set((float) x, (float) y, (float) z);
    }

    public void lookPosTranslate(double x, double y, double z)
    {
        lookPos((float) (x + camLook.x), (float) (y + camLook.y), (float) (z + camLook.z));
    }

    public void pos(double x, double y, double z)
    {
        camera.position.set((float) x, (float) y, (float) z);
        camera.update();
        camPos = camera.position;
    }

    public void posTranslate(double x, double y, double z)
    {
        lookPos((float) (x + camPos.x), (float) (y + camPos.y), (float) (z + camPos.z));
    }

}
