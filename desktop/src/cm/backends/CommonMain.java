package cm.backends;

import cm.backends.lwjgl.LwjglInputCallbackCache;
import cm.backends.lwjgl.LwjglWindow;
import cm.milkyway.eventbus.EventbusTimer;
import cm.milkyway.lang.maths.Mth;
import cm.milkyway.eventbus.Eventbus;
import cm.milkyway.opengl.render.Preference;
import cm.milkyway.opengl.render.Window;
import cm.milkyway.opengl.render.g2d.Color;
import cm.milkyway.opengl.render.graphics.Graphics2D;
import cm.milkyway.opengl.render.graphics.Graphics3D;

public class CommonMain
{

    static Window window;

    public static void main(String[] args)
    {
        //Eventbus is a dispatcher, you can register listeners.They will be called at correct time.
        Eventbus.register((t) -> {
            //Register your tick handler here
        }, Eventbus.TICK);

        Eventbus.register((t) -> {
            //Register your init handler here

            window.getInputRegistry().register(new LwjglInputCallbackCache());
            //the LwjglInputCallbackCache, is a default implement of InputCallback, which can createSaveData your input
            //to #InputMap.
        }, Eventbus.INIT);

        Eventbus.register((t) -> {
            Graphics2D g2d = window.getG2D();
            Graphics3D g3d = window.getG3D();
            g2d.getContext().clear();//clear the screen
            double ci = Mth.sin(Eventbus.getTimer().time() * 0.001) + 1;
            g2d.drawRect(new Color(ci, 1 - ci, 0), 0, 0, 213, 480);
            g2d.drawRect(new Color(0, ci, 1 - ci), 213, 0, 213, 480);
            g2d.drawRect(new Color(1 - ci, 0, ci), 426, 0, 216, 480);
            g2d.getContext().paint();//paint to screen
            //do not forget clear and paint-- or you won't get graphics on screen.
            //like this, just render a rectangle
            //Register your render handler here
        }, Eventbus.RENDER);

        Preference preference = new Preference();
        preference.width = 640;
        preference.height = 480;
        //render logical size

        preference.winWidth = 640;
        preference.winHeight = 480;
        //actual size, used for window's size and fullscreen size,
        //logical size is your canvas, put objects on it;
        //and Milkyway will zoom it and fit to actual size, in correct percent.

        preference.title = "Common Entrance";

        preference.refreshRate = 60;
        preference.bitDepth = 24;
        //using for fullscreen mode.

        //preference.fullScreen = true;

        //preference.cursor = new AccessLocal("xxx.png");//a jar based path
        //preference.icon = new AccessAbsolute("C:/ProgramTest/xxx.png");//an absolute path

        window = new LwjglWindow();
        window.create(preference);
        //create a window.
        //****you shouldn't write any code after this, because this will block the main thread.****
        //start a new thread and create is also triable.
    }

}
