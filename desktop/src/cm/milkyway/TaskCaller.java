package cm.milkyway;

import cm.milkyway.lang.Platform;
import cm.milkyway.opengl.syncs.DirectSync;
import cm.milkyway.opengl.syncs.Sync;
import cm.milkyway.lang.Task;
import cm.milkyway.lang.container.List;

public class TaskCaller
{

    public static final int INIT = 0;
    public static final int TICK = 1;
    public static final int RENDER = 2;
    public static final int DISPOSE = 3;
    public static final int INIT_PRE = -1;
    public static int time;
    static List<Task> onInit = new List<>();
    static List<Task> onInitPre = new List<>();
    static List<Task> onTick = new List<>();
    static List<Task> onRender = new List<>();
    static List<Task> onDispose = new List<>();
    private static boolean preInitEnd = false;
    static Sync tickSync = new DirectSync();
    static Sync renderSync = new DirectSync();
    static Task tickTask = () -> {
        doRunIn(onTick);
        time++;
    };
    static Task renderTask = () -> {
        Milkyway.glBase.clear();
        doRunIn(onRender);
        Milkyway.glBase.freeAll();
    };

    public static void register(Task run, int type)
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
    }

    public static void setDefaultFps(int tick, int render)
    {
        renderSync.setFps(render);
        tickSync.setFps(tick);
    }

    static void doRunIn(List<Task> lst)
    {
        for(int i = 0; i < lst.size(); i++) {
            lst.get(i).run();
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
        Milkyway.window.destroy();
    }

    public static void tick()
    {
        tickSync.run(tickTask);
    }

    public static void render()
    {
        renderSync.run(renderTask);
    }

    public static Sync renderSync()
    {
        return renderSync;
    }

    public static Sync tickSync()
    {
        return tickSync;
    }

    public static void checkPreInit()
    {
        if(!preInitEnd) {
            Platform.error("pre-init has not done! Check if you do sth wrong before #init");
        }
    }

}
