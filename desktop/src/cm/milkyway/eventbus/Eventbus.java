package cm.milkyway.eventbus;

import cm.milkyway.lang.Platform;

public class Eventbus
{

    public static String INIT_PRE = "pre-init";
    public static String INIT = "init";
    public static String TICK = "tick";
    public static String RENDER = "render";
    public static String DISPOSE = "dispose";

    static EventRegister register = new EventRegister();
    static EventbusTimer timer = new EventbusTimer();

    public static void register(EventSubscriber ess, String type)
    {
        register.register(ess, type);
    }

    public static EventRegister register()
    {
        return register;
    }

    public static EventbusTimer getTimer()
    {
        return timer;
    }

    public static void init()
    {
        Platform.log("Pre-init started.");
        register.dispatch(INIT_PRE, null);
        Platform.log("Pre-init ended.");

        Platform.log("Init started.");
        register.dispatch(INIT, null);
        Platform.log("Init ended.");
    }

    public static void dispose()
    {
        register.dispatch(DISPOSE, null);
    }

    public static void tick()
    {
        register.dispatch(TICK, null);
        timer.promote();
    }

    public static void render()
    {
        register.dispatch(RENDER, null);
    }

}
