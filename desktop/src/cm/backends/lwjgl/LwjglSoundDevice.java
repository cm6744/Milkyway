package cm.backends.lwjgl;

import cm.milkyway.opengl.audio.SoundDevice;
import cm.milkyway.opengl.audio.Sound;
import com.badlogic.gdx.audio.Music;

public class LwjglSoundDevice implements SoundDevice
{

    Music music;

    public void start()
    {
    }

    public void dispose()
    {
        if(music != null) {
            music.stop();
        }
    }

    public void pause()
    {
        if(music != null) {
            music.pause();
        }
    }

    public void restart()
    {
        if(music != null) {
            music.play();
        }
    }

    public void loop(Sound sound)
    {
        play(sound);
        music.setLooping(true);
    }

    public void play(Sound sound)
    {
        music = ((LwjglSoundClip) sound).music;
        music.setPosition(0);
        music.play();
    }

    public void setVol(double nv)
    {
        if(music != null) {
            music.setVolume((float) nv);
        }
    }

    public double getVolume()
    {
        if(music == null) {
            return 0;
        }
        return music.getVolume();
    }

}
