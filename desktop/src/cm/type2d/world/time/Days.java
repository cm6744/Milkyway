package cm.type2d.world.time;

import cm.milkywayx.lightx.EnvironmentalLight;

public class Days
{

    public static void intensityOfDay(EnvironmentalLight light)
    {
        double d = Times.time;
        if(d == 55 || d == 117) {
            light.smoothTo(0.75);
        }
        if(d == 0) {
            light.smoothTo(1);
        }
        if(d == 60) {
            light.smoothTo(0);
        }
    }

}
