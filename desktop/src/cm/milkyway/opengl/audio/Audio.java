package cm.milkyway.opengl.audio;

public interface Audio
{

    //get an audio player.
    AudioDevice newDevice();

    //get a sound.
    Sound newSound(String path);

}
