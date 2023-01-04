package cm.type2d;

import cm.backends.lwjgl.LwjglKey;
import cm.milkyway.eventbus.Eventbus;
import cm.milkyway.opengl.input.InputMap;
import cm.milkyway.opengl.render.g2d.Color;
import cm.milkyway.opengl.render.graphics.Graphics2D;
import cm.milkywayx.widgetx.scene.Scene;
import cm.type2d.world.time.Seasons;
import cm.type2d.world.time.Times;

public class StatsTicker extends Scene
{

    Stats stats = new Stats();

    public StatsTicker()
    {
        stats.createNewWorld();
        Eventbus.register((t) -> stats.saveWorld(), Eventbus.DISPOSE);
    }

    public void tickThen()
    {
        if(InputMap.isOn(LwjglKey.key("esc"))) {
            for(int i = 0; i < 39; i++) {
                runLogic();
            }
        }
        runLogic();
    }

    void runLogic()
    {
        Times.upTime();

        stats.huds.tick();
        stats.world.tick();
    }

    public void render(Graphics2D g)
    {
        g.getContext().clear();
        if(stats.world.curRoom == null) return;

        Color filter = Seasons.getDye();
        g.setFilter(filter);
        stats.world.render(g);
        g.setFilter(1, 1, 1);

        stats.huds.render(g);
        g.getContext().paint();
    }

}
