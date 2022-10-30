package cm.milkywaygl.sound;

public class ClipPlayer
{

    private static boolean opened;
    private static SoundDevice clipThread;

    public static void openIf()
    {
        if(!opened) {
            clipThread = new SoundDevice();
            clipThread.startDevice();
        }
        opened = true;
    }

    public static void play(String sound)
    {
        openIf();
        clipThread.play(sound);
    }

}
