package cm.type2d.res;

import cm.milkywayx.widgetx.base.Scene;
import cm.milkywayx.widgetx.base.SceneManager;
import cm.type2d.MainLogic;

public class Loading extends Scene
{

    public void tickThen()
    {
        Load.loader.update();

        if(Load.loader.isDone()) {
            SceneManager.scene(new MainLogic());
        }
    }

    public void render()
    {
        super.render();
    }

}
