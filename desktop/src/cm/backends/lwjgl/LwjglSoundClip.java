package cm.backends.lwjgl;

import cm.milkyway.lang.io.Access;
import cm.milkyway.opengl.audio.Sound;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class LwjglSoundClip implements Sound
{

    public Music music;
    //most times, this will be disposed in SoundDevice. if not, call it
    boolean isDisposed;

    public LwjglSoundClip(Access path)
    {
        music = Gdx.audio.newMusic(Gdx.files.absolute(path.path()));

        LwjglEnvironment.autoDispose(this);
    }

    public void dispose()
    {
        if(isDisposed) {
            return;
        }
        music.dispose();
    }

}
