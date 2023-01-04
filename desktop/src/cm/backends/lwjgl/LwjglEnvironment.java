package cm.backends.lwjgl;

import cm.milkyway.lang.Disposable;
import cm.milkyway.lang.container.list.List;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

public class LwjglEnvironment
{

    public static void initialize()
    {
        context = new LwjglGraphicsContext();
        cache = new LwjglInputCache();
    }

    public static LwjglGraphicsContext context;
    public static LwjglInputCache cache;
    public static List<Disposable> disposableList = new List<>();
    public static ShaderProgram curSha;

    public static void autoDispose(Disposable d)
    {
        disposableList.add(d);
    }

    public static void update()
    {
        context.flushScreenBuffer();
        cache.keyStateUpdate();
    }

}
