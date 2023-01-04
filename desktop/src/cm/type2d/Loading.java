package cm.type2d;

import cm.milkyway.opengl.render.graphics.Graphics2D;
import cm.milkywayx.widgetx.scene.Scene;
import cm.milkywayx.widgetx.scene.SceneManager;

public class Loading extends Scene
{

    boolean done;

    public void tickThen()
    {
        Load.loader.update();

        if(Load.loader.isDone() && !done) {
            done = true;
            SceneManager.scene(new StatsTicker());
        }
    }

    public void render(Graphics2D g)
    {
        super.render(g);
    }

}
