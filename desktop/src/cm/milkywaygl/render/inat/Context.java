package cm.milkywaygl.render.inat;

import cm.milkywaygl.Platform;
import cm.milkywaygl.render.nnat.InputCallback;
import cm.milkywaygl.render.nnat.TaskCaller;
import cm.milkywaygl.resource.Path;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.Pixmap;

public class Context
{

    static int width;
    static int height;
    static int winWidth;
    static int winHeight;
    static boolean fullScreen;
    static Preference pref;

    static Lwjgl3ApplicationConfiguration config;

    public static void create(Preference preference, InputCallback callback)
    {
        pref = preference;
        fullScreen = pref.fullScreen;
        width = pref.width;
        height = pref.height;
        winWidth = fullScreen ? width : pref.winWidth;
        winHeight = fullScreen ? height : pref.winHeight;

        config = new Lwjgl3ApplicationConfiguration();
        config.setWindowedMode(winWidth, winHeight);
        config.setResizable(false);
        config.setTitle(pref.title);
        config.setDecorated(!fullScreen);
        config.useVsync(true);
        config.setIdleFPS(pref.fps);
        config.setForegroundFPS(pref.fps);
        config.setWindowIcon(Path.jar(pref.icon));

        if(fullScreen) {
            Graphics.DisplayMode[] modes = Lwjgl3ApplicationConfiguration.getDisplayModes();
            for(Graphics.DisplayMode m : modes) {
                if(m.width == width && m.height == height) {
                    config.setFullscreenMode(m);
                    break;
                }
            }
        }

        TaskCaller.register(() -> {
            if(pref.cursor != null) {
                Pixmap pm = new Pixmap(Path._libJar(pref.cursor));
                Gdx.graphics.setCursor(Gdx.graphics.newCursor(pm, pm.getWidth() / 2, pm.getHeight() / 2));
            }
            Gdx.input.setInputProcessor(new BuiltinInput(callback));
        }, TaskCaller.INIT);

        Platform.log("Window was created.");
        new Lwjgl3Application(new BuiltinNative(), config);
    }

    public static double width()
    {
        return width;
    }

    public static double height()
    {
        return height;
    }

    public static double winWidth()
    {
        return winWidth;
    }

    public static double winHeight()
    {
        return winHeight;
    }

    public static boolean isFullScreen()
    {
        return fullScreen;
    }

}
