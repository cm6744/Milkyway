package cm.milkyway;

import cm.backends.Backends;
import cm.milkyway.opengl.input.InputCallback;
import cm.milkyway.opengl.render.Preference;
import cm.milkyway.opengl.render.Window;

public class CommonMain
{

    public static void main(String[] args)
    {
        Backends.set("GDX");
        //set the backends you'll use.
        //now there's only GDX.

        TaskCaller.register(() -> {
            //Register your tick handler here
        }, TaskCaller.TICK);

        TaskCaller.register(() -> {
            //Register your init handler here
        }, TaskCaller.INIT);

        TaskCaller.register(() -> {
            //Register your render handler here
        }, TaskCaller.RENDER);

        Preference preference = new Preference();
        preference.width = 640;
        preference.height = 480;
        //render logical size

        preference.winWidth = 640;
        preference.winHeight = 480;
        //actual size, used for window's size and fullscreen size,
        //logical size is your canvas, put objects on it;
        //and Milkyway will zoom it and fit to actual size, in correct percent.

        preference.title = "Common Test";

        preference.refreshRate = 60;
        preference.bitDepth = 24;
        //using for fullscreen mode.

        preference.fullScreen = true;

        preference.cursor = "xxx";
        preference.icon = "xxx";
        //this is a jar-based path.
        //every resource, will be found start at jar file.

        //for example:
        //C:\Program Files\Common.jar
        //if you want to search:
        //C:\Program Files\textures\cursor.png
        //you need to type:
        //textures/cursor.png

        Window window = Milkyway.window;
        //this const was initialized when you chose backend.

        window.create(preference, new InputCallback());
        //create a window.
        //you shouldn't write any code after this, because this will block the main thread.
        //start a new thread and create is also triable.

        //the input callback, used to reflect your input to InputMap.
        //you can also customize, just extend it.
    }

}
