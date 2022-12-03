package cm.test;

import cm.milkywaygl.Milkyway;
import cm.milkywaygl.TaskCaller;
import cm.glbackends.Backends;
import cm.milkywaygl.input.InputCallbackToMap;
import cm.milkywaygl.json.JsonObject;
import cm.milkywaygl.render.Preference;
import cm.milkywaygl.sound.SoundDevice;
import cm.milkywaylib.base.SceneManager;

public class Main
{

    static int defFps;
    static GL3Performed performed;
    static SoundDevice music;

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

        Milkyway.window.create(pref, new InputCallbackToMap());

    }
}
