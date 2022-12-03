package cm.glbackends.gdx;

import cm.glbackends.gdx.sound.SoundDeviceGdx;
import cm.glbackends.gdx.sound.SoundGdx;
import cm.milkywaygl.sound.Audio;
import cm.milkywaygl.sound.Sound;
import cm.milkywaygl.sound.SoundDevice;

public class AudioGdx implements Audio
{

    public SoundDevice newDevice()
    {
        return new SoundDeviceGdx();
    }

    public Sound newSound(String path)
    {
        return new SoundGdx(path);
    }

}
