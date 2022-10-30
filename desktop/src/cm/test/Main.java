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
import cm.milkywaygl.text.JsonFile;
import cm.milkywaylib.linklib.SceneManager;

public class Main
{

    static SceneMine sceneMine;

    public static void main(String[] args)
    {
        JsonFile jf = JsonFile.load("property.json");
        Preference pref = new Preference();
        String lang = jf.getString("language");
        pref.width = jf.getInt("width");
        pref.height = jf.getInt("height");
        pref.winWidth = jf.getInt("win-width");
        pref.winHeight = jf.getInt("win-height");
        pref.cursor = jf.getString("cursor");
        pref.icon = jf.getString("icon");
        pref.title = jf.getString("title");

        TaskCaller.register(GL::create, TaskCaller.INIT);
        TaskCaller.register(() -> {
            I18n.load("texts/" + lang + ".json");
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