package cm.milkyway.opengl.audio;

import cm.milkyway.lang.Disposable;

public interface AudioDevice extends Disposable
{

    //invoke after created, or it may throw an exception(in some implements).
    void start();

    //pause current audio.
    void pause();

    //restart current audio.
    void restart();

    //loop a sound.
    void loop(Sound sound);

    //play a sound single-time.
    void play(Sound sound);

    //set current audio's volume.
    void setVol(double nv);

    double getVolume();

    default void translateVol(double mv)
    {
        setVol(getVolume() + mv);
    }

}
