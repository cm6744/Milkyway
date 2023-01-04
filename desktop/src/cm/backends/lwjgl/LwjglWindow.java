package cm.backends.lwjgl;

import cm.milkyway.eventbus.Eventbus;
import cm.milkyway.lang.Platform;
import cm.milkyway.opengl.input.InputRegistry;
import cm.milkyway.opengl.render.Preference;
import cm.milkyway.opengl.render.Window;
import cm.milkyway.opengl.render.graphics.Graphics2D;
import cm.milkyway.opengl.render.graphics.Graphics3D;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.Pixmap;

public class LwjglWindow implements Window
{

    LwjglGraphics2D g2d;
    LwjglGraphics3D g3d;

    public static Window instance;

    public LwjglWindow()
    {
        if(instance != null) {
            throw new RuntimeException("Could not create multi windows!");
        }
        instance = this;
    }

    int width;
    int height;
    boolean fullScreen;
    Preference pref;
    LwjglInputProcessor input;

    Lwjgl3ApplicationConfiguration config;

    public void create(Preference preference)
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
        if(pref.icon.isExist()) {
            config.setWindowIcon(pref.icon.path());
        }

        if(fullScreen) {
            fullscreen();
        }

        Eventbus.register((t) -> initPreTasks(), Eventbus.INIT_PRE);
        Eventbus.register((t) -> tickTasks(), Eventbus.TICK);
        Eventbus.register((t) -> destroy(), Eventbus.DISPOSE);

        Platform.log("Window was created.");
        new Lwjgl3Application(new LwjglApplicationListener(), config);
    }

    private void tickTasks()
    {
        LwjglEnvironment.update();
    }

    private void initPreTasks()
    {
        LwjglEnvironment.initialize();
        if(pref.cursor.isExist()) {
            Pixmap pm = new Pixmap(Gdx.files.absolute(pref.cursor.path()));
            Gdx.graphics.setCursor(Gdx.graphics.newCursor(pm, pm.getWidth() / 2, pm.getHeight() / 2));
        }
        //local input
        input = new LwjglInputProcessor();
        Gdx.input.setInputProcessor(input);
        Platform.log("Input collector created.");

        g2d = new LwjglGraphics2D();
        g3d = new LwjglGraphics3D();
    }

    public Graphics2D getG2D()
    {
        return g2d;
    }

    public Graphics3D getG3D()
    {
        return g3d;
    }

    private void fullscreen()
    {
        Graphics.DisplayMode[] modes = Lwjgl3ApplicationConfiguration.getDisplayModes();
        for(Graphics.DisplayMode m : modes) {
            Platform.log("Search display mode result: " + m.toString());
            if(     m.width == pref.winWidth
                    && m.height == pref.winHeight
                    && m.refreshRate == pref.refreshRate
                    && m.bitsPerPixel == pref.bitDepth) {
                config.setFullscreenMode(m);
                config.useVsync(true);
                break;
            }
        }
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
        LwjglEnvironment.disposableList.iterate((o, i) -> o.dispose(), false);
    }

}
