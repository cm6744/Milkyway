package cm.glbackends.gdx.sound;

import cm.milkywaygl.TaskCaller;
import cm.glbackends.BasePath;
import cm.milkywaygl.sound.Sound;
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
