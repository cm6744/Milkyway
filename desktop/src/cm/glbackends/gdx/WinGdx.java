package cm.glbackends.gdx;

import cm.milkywaygl.Milkyway;
import cm.milkywaygl.Platform;
import cm.milkywaygl.TaskCaller;
import cm.glbackends.BasePath;
import cm.milkywaygl.input.InputCallback;
import cm.milkywaygl.input.InputMap;
import cm.milkywaygl.input.InputRegistry;
import cm.milkywaygl.render.Preference;
import cm.milkywaygl.render.Window;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Graphics;
import com.badlogic.gdx.graphics.Pixmap;

public class WinGdx implements Window
{

    int width;
    int height;
    boolean fullScreen;
    Preference pref;
    IPGdx input;

    Lwjgl3ApplicationConfiguration config;

    public void create(Preference preference, InputCallback callback)
    {
        pref = preference;
        fullScreen = pref.fullScreen;
        width = pref.width;
        height = pref.height;

        config = new Lwjgl3ApplicationConfiguration();
        config.setWindowedMode(pref.winWidth, pref.winHeight);
        config.setResizable(true);
        config.setTitle(pref.title);
        if(fullScreen) {
            config.setDecorated(false);
        }
        config.useVsync(false);
        config.setIdleFPS(pref.fps);
        config.setForegroundFPS(pref.fps);
        config.setWindowIcon(BasePath.jar(pref.icon));

        if(fullScreen) {
            Graphics.DisplayMode[] modes = Lwjgl3ApplicationConfiguration.getDisplayModes();
            for(Graphics.DisplayMode m : modes) {
                Platform.log("Search display mode result: " + m.toString());
                if(m.width == pref.winWidth && m.height == pref.winHeight
                        && m.refreshRate == preference.refreshRate && m.bitsPerPixel == preference.bitDepth) {
                    config.setFullscreenMode(m);
                    config.useVsync(true);
                    break;
                }
            }
        }

        TaskCaller.register(() -> {
            //set cursor
            if(pref.cursor != null) {
                Pixmap pm = new Pixmap(Gdx.files.absolute(BasePath.jar(pref.cursor)));
                Gdx.graphics.setCursor(
                        Gdx.graphics.newCursor(
                                pm,
                                pm.getWidth() / 2,
                                pm.getHeight() / 2
                        )
                );
            }
            //local input
            input = new IPGdx();
            input.register(callback);
            Gdx.input.setInputProcessor(input);
            Platform.log("Input collector created.");
            //local
            GdxGLCreator.create();
            //bind to
            Milkyway.input = input;
        }, TaskCaller.INIT_PRE);
        //ticking input map state
        TaskCaller.register(() -> {
            InputMap.keyStateUpdate();
            Milkyway.glBase.updateFrameData();
        }, TaskCaller.TICK);

        Platform.log("Window was created.");
        new Lwjgl3Application(new ILGdx(), config);
    }

    public double width()
    {
        return width;
    }

    public double height()
    {
        return height;
    }

    public double winWidth()
    {
        return Gdx.graphics.getWidth();
    }

    public double winHeight()
    {
        return Gdx.graphics.getHeight();
    }

    public boolean isFullScreen()
    {
        return fullScreen;
    }

    public InputRegistry getInputRegistry()
    {
        return input;
    }

    public Preference getPreference()
    {
        return pref;
    }

    public void destroy()
    {
        ((Lwjgl3Graphics) Gdx.graphics).getWindow().dispose();
    }

}
