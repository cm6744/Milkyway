package cm.milkywaygl.sound;

public interface Audio
{

    SoundDevice newDevice();

    Sound newSound(String path);

}
