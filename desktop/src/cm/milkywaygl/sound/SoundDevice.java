package cm.milkywaygl.sound;

import cm.milkywaytool.Disposable;

public interface SoundDevice extends Disposable
{

    void start();

    void pause();

    void restart();

    void loop(Sound sound);

    void play(Sound sound);

    void setVol(double nv);

    double getVolume();

    default void translateVol(double mv)
    {
        setVol(getVolume() + mv);
    }

}
