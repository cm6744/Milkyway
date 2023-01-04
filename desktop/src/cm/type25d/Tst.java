package cm.type25d;

import cm.backends.lwjgl.*;
import cm.backends.tipy.DefaultTipyReader;
import cm.milkyway.eventbus.Eventbus;
import cm.milkyway.lang.io.AccessLocal;
import cm.milkyway.opengl.render.Preference;
import cm.milkyway.opengl.render.graphics.Graphics2D;
import cm.milkyway.tipy.Tipy;
import cm.milkywayx.widgetx.scene.SceneManager;

public class Tst
{
    public static LwjglWindow window;

    static Graphics2D graphics2D;

    public static void main(String[] args)
    {
        Tipy jf = new DefaultTipyReader().read(new AccessLocal("property.tipy"));
        Preference pref = new Preference();
        //String lang = jf.get("language").getString();
        int[] size = jf.getArrayInt("win_size");
        pref.width = size[0];
        pref.height = size[1];
        pref.winWidth = size[2];
        pref.winHeight = size[3];
        pref.cursor = new AccessLocal(jf.getString("cursor"));
        pref.icon = new AccessLocal(jf.getString("icon"));
        pref.title = jf.getString("title");
        pref.fullScreen = jf.getBoolean("full_screen");
        pref.bitDepth = jf.getInt("color_bit");
        pref.refreshRate = jf.getInt("refresh");
        pref.fps = jf.getInt("fps");

        Eventbus.register((t) -> {
            while(!Load.loader.isDone()) {
                Load.loader.update();
            }
            graphics2D = window.getG2D();
            graphics2D.getContext().getWindow().getInputRegistry().register(new LwjglInputCallbackCache());
            SceneManager.scene(new ScenePainter());
        }, Eventbus.INIT);

        Eventbus.register((t) -> SceneManager.tick(), Eventbus.TICK);

        Eventbus.register((t) -> {
            graphics2D.getContext().clear();
            SceneManager.render(graphics2D);
            graphics2D.getContext().paint();
        }, Eventbus.RENDER);

        window = new LwjglWindow();
        //pref.fullScreen = true;
        window.create(pref);
    }
}
