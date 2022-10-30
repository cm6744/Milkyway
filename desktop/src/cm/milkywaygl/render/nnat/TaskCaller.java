package cm.milkywaygl.render.nnat;

import cm.milkywaygl.GLRunnable;
import cm.milkywaygl.Platform;
import cm.milkywaygl.util.container.List;

public class TaskCaller
{

    public static final int INIT = 0;
    public static final int TICK = 1;
    public static final int RENDER = 2;
    public static final int DISPOSE = 3;
    private static final double nanoSec = 1000000000.0;
    public static int globalTime;
    public static int nowFpsUpdate;
    public static int nowFpsRender;
    static List<GLRunnable> onInit = new List<>();
    static List<GLRunnable> onTick = new List<>();
    static List<GLRunnable> onRender = new List<>();
    static List<GLRunnable> onDispose = new List<>();
    static double fpsTimeTick;
    static double fpsTimeRender;
    static int loopTicks;
    static long lastTick;
    static int maxLoop = 10;
    private static int updateFrame;
    private static int renderFrame;
    private static long timeTask;
    private static boolean firstTick = true;

    public static void register(GLRunnable run, int type)
    {
        switch(type) {
            case INIT:
                onInit.add(run);
                break;
            case TICK:
                onTick.add(run);
                break;
            case RENDER:
                onRender.add(run);
                break;
            case DISPOSE:
                onDispose.add(run);
                break;
        }

        Platform.log("TaskCaller registered new event, type: " + type);
    }

    public static void setDefaultFps(int tick, int render)
    {
        nowFpsUpdate = tick;
        fpsTimeTick = nanoSec / tick;
        nowFpsRender = render;
        fpsTimeRender = nanoSec / render;
    }

    static void doRunIn(List<GLRunnable> lst)
    {
        for(int i = 0; i < lst.size(); i++) {
            lst.get(i).run();
        }
    }

    public static void init()
    {
        doRunIn(onInit);
    }

    public static void dispose()
    {
        doRunIn(onDispose);
    }

    public static void tick()
    {
        if(firstTick) {
            timeTask = Platform.getTickNs();
            lastTick = Platform.getTickNano();
        }

        //without gdx, we need to handle the threading.
        //but now it is useless.
        while(Platform.getTickNano() > lastTick && loopTicks < maxLoop) {

            doRunIn(onTick);
            globalTime++;
            updateFrame++;

            lastTick += fpsTimeTick;
            loopTicks++;

            if(loopTicks >= maxLoop && Platform.getTickNano() - lastTick > fpsTimeTick) {
                lastTick = Platform.getTickNano();//If there are still too much time to catch up with, fail it
            }
        }

        firstTick = false;
        loopTicks = 0;

        if(Platform.getTickNs() - timeTask > 1000) {//fps count
            nowFpsUpdate = updateFrame;
            nowFpsRender = renderFrame;
            updateFrame = 0;
            renderFrame = 0;
            timeTask += 1000;
        }
    }

    public static void render()
    {
        doRunIn(onRender);
        renderFrame++;
    }

    public static boolean isTickCalm()
    {
        return loopTicks == 1;
    }

}
