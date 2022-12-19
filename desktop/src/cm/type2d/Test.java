package cm.type2d;

import cm.backends.Backends;
import cm.milkyway.Milkyway;
import cm.milkyway.TaskCaller;
import cm.milkyway.opengl.render.g2d.AreaAnimated;
import cm.milkyway.opengl.render.g2d.BufferTex;
import cm.milkywayx.widgetx.base.SceneManager;
import cm.milkyway.opengl.input.InputCallbackToMap;
import cm.milkyway.opengl.json.JsonObject;
import cm.milkyway.opengl.render.Preference;
import cm.type2d.res.Loading;

public class Test
{
    public static void main(String[] args)
    {
        Backends.set("GDX");

        JsonObject jf = Milkyway.json.read("property.json");
        Preference pref = new Preference();
        //String lang = jf.entry("language").asString();
        pref.width = jf.entry("width").asInt();
        pref.height = jf.entry("height").asInt();
        pref.winWidth = jf.entry("win_width").asInt();
        pref.winHeight = jf.entry("win_height").asInt();
        pref.cursor = jf.entry("cursor").asString();
        pref.icon = jf.entry("icon").asString();
        pref.title = jf.entry("title").asString();
        pref.fullScreen = jf.entry("full_screen").asBool();
        pref.bitDepth = jf.entry("color_bit").asInt();
        pref.refreshRate = jf.entry("refresh").asInt();
        pref.fps = jf.entry("fps").asInt();

        TaskCaller.register(() -> {
            Milkyway.gl2d.setSmooth(false);
            SceneManager.scene(new Loading());
        }, TaskCaller.INIT);

        TaskCaller.register(SceneManager::tick, TaskCaller.TICK);

        TaskCaller.register(SceneManager::render, TaskCaller.RENDER);

        Milkyway.window.create(pref, new InputCallbackToMap());

    }
}
