package cm.milkywaygl.sound;

import cm.milkywaygl.Milkyway;
import cm.milkywaygl.TaskCaller;

public class ClipPlayer
{

    private static boolean opened;
    private static SoundDevice clipThread;

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
