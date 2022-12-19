package cm.milkyway.opengl.syncs;

import cm.milkyway.lang.Platform;
import cm.milkyway.lang.Task;

public class DirectSync implements Sync
{

    double fpsTime = 16.67;
    int defFps;
    int nowFps;
    long calcTime;
    int updateFrame;
    boolean init;

    public void run(Task task)
    {
        if(!init) {
            calcTime = Platform.getTickMill();
            init = true;
        }

        task.run();
        updateFrame++;

        if(Platform.getTickMill() - calcTime > 1000) {//fps count
            nowFps = updateFrame;
            updateFrame = 0;
            calcTime += 1000;
        }
    }

    public int fps()
    {
        return nowFps;
    }

    public int defaultFps()
    {
        return defFps;
    }

    public void setFps(int fps)
    {
        defFps = fps;
        fpsTime = toCPUSleepTime(fps);
    }

    public boolean isCalm()
    {
        return true;
    }

}
