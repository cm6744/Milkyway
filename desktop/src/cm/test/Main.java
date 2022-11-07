package cm.test;

import cm.milkywaygl.Platform;
import cm.milkywaygl.render.GL;
import cm.milkywaygl.render.inat.Context;
import cm.milkywaygl.render.inat.Preference;
import cm.milkywaygl.render.nnat.InputCallback;
import cm.milkywaygl.render.nnat.TaskCaller;
import cm.milkywaygl.render.wrapper.Color4;
import cm.milkywaygl.render.wrapper.Font2;
import cm.milkywaygl.render.wrapper.FontType;
import cm.milkywaygl.sound.SoundDevice;
import cm.milkywaygl.text.I18n;
import cm.milkywaygl.text.JsonEntry;
import cm.milkywaygl.text.JsonFile;
import cm.milkywaylib.linklib.SceneManager;

public class Main
{

    static int defFps;

    public static void main(String[] args)
    {
        JsonFile jf = JsonFile.load("property.json");
        jf.openReading();
        Preference pref = new Preference();
        String lang = jf.entry("language").toString();
        pref.width = jf.entry("width").toInt();
        pref.height = jf.entry("height").toInt();
        pref.winWidth = jf.entry("win-width").toInt();
        pref.winHeight = jf.entry("win-height").toInt();
        pref.cursor = jf.entry("cursor").toString();
        pref.icon = jf.entry("icon").toString();
        pref.title = jf.entry("title").toString();
        defFps = jf.entry("fps").toInt();

        TaskCaller.register(GL::create, TaskCaller.INIT);
        TaskCaller.register(() -> {
            //I18n.load("texts/" + lang + ".json");
            Assets.loadAll();
            SceneManager.scene(new SceneLoad());
            SoundDevice bg = new SoundDevice();
            bg.startDevice();
            bg.loop("sounds/th08_13.wav");
        }, TaskCaller.INIT);

        TaskCaller.register(() -> {
            GL.gl.dispose();
            Platform.exit();
        }, TaskCaller.DISPOSE);

        TaskCaller.register(SceneManager::tick, TaskCaller.TICK);

        TaskCaller.register(SceneManager::render, TaskCaller.RENDER);

        Context.create(pref, new InputCallback());

    }
}
