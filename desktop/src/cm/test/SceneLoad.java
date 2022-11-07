package cm.test;

import cm.milkywaygl.render.GL;
import cm.milkywaygl.render.nnat.TaskCaller;
import cm.milkywaylib.linklib.Scene;
import cm.milkywaylib.linklib.SceneManager;

public class SceneLoad extends Scene
{

    public void tickThen()
    {

        if(TaskCaller.isTickCalm()) {
            Assets.loading.update();
        }

        if(Assets.loading.isDone()) {
            SceneManager.scene(new SceneMine());
        }
    }

    public void render()
    {
        double pro = Assets.loading.progress();
        GL.gl2.begin();
        GL.gl2.dim(Assets.loadingBG, 0, 0, 800, 600);
        GL.gl2.dim(Assets.loadingFont, 5, 550, 128, 32);
        GL.gl2.dim(Assets.white, 5, 590, 790 * pro, 5);
        GL.gl2.end();
    }

}
