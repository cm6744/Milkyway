package cm.milkywaygl.sound;

import cm.milkywaygl.Platform;
import cm.milkywaygl.inter.GLTickable;
import cm.milkywaygl.multithread.ThreadManager;
import cm.milkywaygl.resource.Resource;
import cm.milkywaygl.resource.StreamManager;

import javax.sound.sampled.*;
import java.io.IOException;

public class SoundDevice
{

    SoundPlayer device;
    double volume;
    boolean volChanged;
    boolean pause;
    double maxVol;
    double minVol;

    public void startDevice()
    {
        device = new SoundPlayer();
        ThreadManager.join(device);
        ThreadManager.start(device);
    }

    public void killDevice()
    {
        ThreadManager.dispose(device);
    }

    public void pause()
    {
        pause = true;
    }

    public void runAgain()
    {
        pause = false;
    }

    public void loop(String sound, int times)
    {
        if(times < 0) {
            Platform.error("Zero times is endless loop. No negative.");
        }
        device.submit(sound, times);
    }

    public void play(String sound)
    {
        loop(sound, 1);
    }

    public void loop(String sound)
    {
        loop(sound, 0);
    }

    public void setVol(double nv)
    {
        volChanged = true;
        if(nv >= maxVol) {
            volume = maxVol;
        }
        else if(nv <= minVol) {
            volume = minVol;
        }
        else {
            volume = nv;
        }
    }

    public void translateVol(double mv)
    {
        setVol(volume + mv);
    }

    public double getVolume()
    {
        return volume;
    }

    private class SoundPlayer implements GLTickable
    {

        String nowPlay;
        int loopTimes;

        public void submit(String aul, int loop)
        {
            nowPlay = aul;
            loopTimes = loop;
        }

        public void tick()
        {
            do {
                while(nowPlay == null) {
                    ThreadManager.sleep(this, 1);
                }
                SourceDataLine dataLine = null;
                AudioInputStream aul;
                StreamManager<AudioInputStream> cls = Resource.audioStm(nowPlay);

                try {
                    aul = cls.get();
                    if(aul == null) {
                        continue;
                    }

                    AudioFormat format = aul.getFormat();
                    dataLine = AudioSystem.getSourceDataLine(format);
                    dataLine.open();

                    dataLine.start();
                    FloatControl fc = (FloatControl) dataLine.getControl(FloatControl.Type.MASTER_GAIN);
                    maxVol = fc.getMaximum();
                    minVol = fc.getMinimum();
                    volume = maxVol;
                    volChanged = true;

                    byte[] buf = new byte[512];
                    int len;
                    while((len = aul.read(buf)) != -1) {
                        if(volChanged) {
                            volChanged = false;
                            if(volume <= maxVol && volume >= minVol) {
                                fc.setValue((float) volume);
                            }
                        }
                        while(pause) {
                            ThreadManager.sleep(this, 1);
                        }
                        dataLine.write(buf, 0, len);
                    }
                }
                catch(IOException | LineUnavailableException e) {
                    Platform.throwExc(e);
                }
                finally {
                    if(dataLine != null) {
                        dataLine.drain();
                        dataLine.close();
                    }
                    cls.close();
                }
                loopTimes--;
                while(loopTimes == 0) {
                    ThreadManager.sleep(this, 10);
                }
            } while(ThreadManager.isNotDisposed(this));
        }

    }

}
