package cm.milkywaygl.render.nativegl;

import cm.milkywaygl.Platform;
import cm.milkywaygl.input.InputCallback;
import cm.milkywaygl.input.InputMap;
import cm.milkywaygl.input.Key;
import cm.milkywaygl.input.SimpleInputCallback;
import cm.milkywaygl.TaskCaller;
import cm.milkywaygl.render.GL;
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
    static BuiltinInput input;

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
        if(fullScreen) {
            config.setDecorated(false);
        }
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
            //set cursor
            if(pref.cursor != null) {
                Pixmap pm = new Pixmap(Path._libJar(pref.cursor));
                Gdx.graphics.setCursor(
                        Gdx.graphics.newCursor(
                                pm,
                                pm.getWidth() / 2,
                                pm.getHeight() / 2
                        )
                );
            }
            //create input
            input = new BuiltinInput();
            input.register(new SimpleInputCallback());
            Gdx.input.setInputProcessor(input);
            Platform.log("Input collector created.");
            //create GL
            GL.create();

        }, TaskCaller.INIT_PRE);
        //ticking input map state
        TaskCaller.register(InputMap::keyStateUpdate, TaskCaller.TICK);

        //dispose resources
        TaskCaller.register(() -> {
            GL.gl.dispose();
            Platform.exit();
        }, TaskCaller.DISPOSE);

        Platform.log("Window was created.");
        new Lwjgl3Application(new BuiltinListener(), config);
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

    public static BuiltinInput getInputRegistry()
    {
        return input;
    }

}
