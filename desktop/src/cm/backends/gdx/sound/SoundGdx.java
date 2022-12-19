package cm.backends.gdx.sound;

import cm.milkyway.TaskCaller;
import cm.backends.BasePath;
import cm.milkyway.opengl.audio.Sound;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class SoundGdx implements Sound
{

    public Music music;

    public SoundGdx(String path)
    {
        music = Gdx.audio.newMusic(Gdx.files.absolute(BasePath.jar(path)));
        TaskCaller.register(music::dispose, TaskCaller.DISPOSE);
    }

}
