package cm.typestg.test;

import cm.milkyway.Milkyway;
import cm.milkyway.TaskCaller;
import cm.backends.Backends;
import cm.milkyway.opengl.input.InputCallbackToMap;
import cm.milkyway.opengl.json.JsonObject;
import cm.milkyway.opengl.render.Preference;
import cm.milkyway.opengl.audio.AudioDevice;
import cm.milkywayx.widgetx.base.SceneManager;

public class Main
{

    static int defFps;
    static GL3Performed performed;
    static AudioDevice music;

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
        defFps = jf.entry("fps").asInt();
        pref.fps = 60;

        TaskCaller.register(() -> {
            SceneManager.scene(new SceneLoad());
        }, TaskCaller.INIT);

        TaskCaller.register(SceneManager::tick, TaskCaller.TICK);

        TaskCaller.register(SceneManager::render, TaskCaller.RENDER);
        TaskCaller.setDefaultFps(defFps, 48);

        new Thread(() -> {
            Milkyway.window.create(pref, new InputCallbackToMap());
        }).start();


    }
}
