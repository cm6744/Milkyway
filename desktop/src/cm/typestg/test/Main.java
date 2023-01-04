package cm.typestg.test;

import cm.backends.lwjgl.*;
import cm.backends.tipy.DefaultTipyReader;
import cm.milkyway.eventbus.Eventbus;
import cm.milkyway.lang.io.AccessLocal;
import cm.milkyway.opengl.render.Preference;
import cm.milkyway.opengl.audio.SoundDevice;
import cm.milkyway.opengl.render.graphics.Graphics2D;
import cm.milkyway.tipy.Tipy;
import cm.milkywayx.widgetx.scene.SceneManager;

public class Main
{

    static Graphics2D g;
    static int defFps;
    static GL3Performed performed;
    static SoundDevice music;

    public static void main(String[] args)
    {
        Tipy jf = new DefaultTipyReader().read(new AccessLocal("property.json"));
        Preference pref = new Preference();
        //String lang = jf.get("language").getString();
        pref.width = jf.getInt("width");
        pref.height = jf.getInt("height");
        pref.winWidth = jf.getInt("win_width");
        pref.winHeight = jf.getInt("win_height");
        pref.cursor = new AccessLocal(jf.getString("cursor"));
        pref.icon = new AccessLocal(jf.getString("icon"));
        pref.title = jf.getString("title");
        pref.fullScreen = jf.getBoolean("full_screen");
        pref.bitDepth = jf.getInt("color_bit");
        pref.refreshRate = jf.getInt("refresh");
        pref.fps = 60;

        Eventbus.register((t) -> {
            g = new LwjglGraphics2D();
            SceneManager.scene(new SceneLoad());
            LwjglEnvironment.context.getWindow().getInputRegistry().register(new LwjglInputCallbackCache());
        }, Eventbus.INIT);

        Eventbus.register((t) -> SceneManager.tick(), Eventbus.TICK);

        Eventbus.register((t) -> SceneManager.render(g), Eventbus.RENDER);
        //Eventbus.setDefaultFps(defFps, 48);

        new Thread(() -> {
            new LwjglWindow().create(pref);
        }).start();


    }
}
