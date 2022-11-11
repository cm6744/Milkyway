package cm.milkywaygl;

import cm.milkywaygl.interfac.GLTickable;
import cm.milkywaygl.util.container.List;

public class TaskCaller
{

    public static final int INIT = 0;
    public static final int TICK = 1;
    public static final int RENDER = 2;
    public static final int DISPOSE = 3;
    public static final int INIT_PRE = -1;
    private static final double nanoSec = 1000000000.0;
    public static int time;
    public static int nowFpsUpdate;
    public static int nowFpsRender;
    static List<GLTickable> onInit = new List<>();
    static List<GLTickable> onInitPre = new List<>();
    static List<GLTickable> onTick = new List<>();
    static List<GLTickable> onRender = new List<>();
    static List<GLTickable> onDispose = new List<>();
    static double fpsTimeTick;
    static double fpsTimeRender;
    static int loopTicks;
    static long lastTick;
    static int maxLoop = 10;
    private static int updateFrame;
    private static int renderFrame;
    private static long timeTask;
    private static boolean firstTick = true;
    private static boolean preInitEnd = false;

    public static void register(GLTickable run, int type)
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
            case INIT_PRE:
                onInitPre.add(run);
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

    static void doRunIn(List<GLTickable> lst)
    {
        for(int i = 0; i < lst.size(); i++) {
            lst.get(i).tick();
        }
    }

    public static void init()
    {
        Platform.log("Pre-init started.");
        doRunIn(onInitPre);
        preInitEnd = true;
        Platform.log("Pre-init ended.");

        Platform.log("Init started.");
        doRunIn(onInit);
        Platform.log("Init ended.");
    }

    public static void dispose()
    {
        doRunIn(onDispose);
    }

    public static void tick()
    {
        if(firstTick) {
            timeTask = Platform.getTickMill();
            lastTick = Platform.getTickNano();
        }

        //without gdx, we need to handle the threading.
        //but now it is useless.
        while(Platform.getTickNano() > lastTick && loopTicks < maxLoop) {

            //time++ after ticking
            doRunIn(onTick);
            time++;
            updateFrame++;

            lastTick += fpsTimeTick;
            loopTicks++;

            if(loopTicks >= maxLoop && Platform.getTickNano() - lastTick > fpsTimeTick) {
                lastTick = Platform.getTickNano();//If there are still too much time to catch up with, fail it
            }
        }

        firstTick = false;
        loopTicks = 0;

        if(Platform.getTickMill() - timeTask > 1000) {//fps count
            //nowFpsUpdate = updateFrame;
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

    public static boolean isPreInitialized()
    {
        return preInitEnd;
    }

}
