package cm.milkywaygl.syncs;

import cm.milkywaytool.Task;

public interface Sync
{

    double nanoSec = 1000000000.0;

    default double toCPUSleepTime(int fps)
    {
        return nanoSec / fps;
    }

    void run(Task task);

    void setFps(int fps);

    int fps();

    int defaultFps();

    boolean isCalm();

}
