package cm.milkyway.opengl.audio;

import cm.milkyway.Milkyway;
import cm.milkyway.TaskCaller;

//this is a default, static implement for fast playing effect sounds.
//just directly invoke #play
public class ClipPlayer
{

    private static boolean opened;
    private static AudioDevice clipThread;

    public static void openIf()
    {
        TaskCaller.checkPreInit();
        if(!opened) {
            clipThread = Milkyway.audio.newDevice();
            clipThread.start();
        }
        opened = true;
    }

    public static void play(Sound sound)
    {
        openIf();
        clipThread.play(sound);
    }

}
