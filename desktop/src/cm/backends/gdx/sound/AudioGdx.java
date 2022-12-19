package cm.backends.gdx.sound;

import cm.milkyway.opengl.audio.Audio;
import cm.milkyway.opengl.audio.Sound;
import cm.milkyway.opengl.audio.AudioDevice;

public class AudioGdx implements Audio
{

    public AudioDevice newDevice()
    {
        return new SoundDeviceGdx();
    }

    public Sound newSound(String path)
    {
        return new SoundGdx(path);
    }

}
