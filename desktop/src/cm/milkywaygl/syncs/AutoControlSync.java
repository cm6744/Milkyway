package cm.milkywaygl.syncs;

import cm.milkywaygl.Platform;
import cm.milkywaytool.Task;

public class AutoControlSync implements Sync
{

    public static int maxLoop = 10;

    double fpsTime = 16.67;
    int defFps;
    int nowFps;
    int loopTicks;
    long lastTick;
    long calcTime;
    int updateFrame;
    boolean init;

    public void run(Task task)
    {
        if(!init) {
            calcTime = Platform.getTickMill();
            lastTick = Platform.getTickNano();
            init = true;
        }

        //without gdx, we need to handle the threading.
        //but now it is useless.
        //2022/11/22-PS: not so, used to control fps.
        while(Platform.getTickNano() > lastTick && loopTicks < maxLoop) {

            task.run();
            updateFrame++;

            lastTick += fpsTime;
            loopTicks++;

            if(loopTicks >= maxLoop && Platform.getTickNano() - lastTick > fpsTime) {
                lastTick = Platform.getTickNano();//If there are still too much time to catch up with, fail it
            }
        }

        loopTicks = 0;

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
        return loopTicks == 1;
    }

}
