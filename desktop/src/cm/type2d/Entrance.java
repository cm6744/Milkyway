package cm.type2d;

import cm.backends.lwjgl.*;
import cm.backends.tipy.DefaultTipyReader;
import cm.milkyway.eventbus.Eventbus;
import cm.milkyway.lang.io.AccessLocal;
import cm.milkyway.opengl.render.graphics.Graphics2D;
import cm.milkyway.tipy.Tipy;
import cm.milkywayx.widgetx.scene.SceneManager;
import cm.milkyway.opengl.render.Preference;

public class Entrance
{

    public static Graphics2D graphics2D;

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
        pref.fps = jf.getInt("fps");

        Eventbus.register((t) -> {
            graphics2D = new LwjglGraphics2D();
            //graphics2D.setShader(LwjglShader.file("shaders/spot.vert", "shaders/spot.frag"));
            graphics2D.getContext().getWindow().getInputRegistry().register(new LwjglInputCallbackCache());
            SceneManager.scene(new Loading());
        }, Eventbus.INIT);

        Eventbus.register((t) -> SceneManager.tick(), Eventbus.TICK);

        Eventbus.register((t) -> SceneManager.render(graphics2D), Eventbus.RENDER);

        new LwjglWindow().create(pref);

    }

}
