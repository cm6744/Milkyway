package cm.test;

import cm.milkywaygl.render.GL;
import cm.milkywaygl.render.nnat.TaskCaller;
import cm.milkywaylib.linklib.Scene;
import cm.milkywaylib.linklib.SceneManager;

public class SceneLoad extends Scene
{

    public void tick()
    {

        if(TaskCaller.isTickCalm())
        Assets.loading.update();

        if(Assets.loading.isDone()) {
            SceneManager.scene(new SceneMine());
        }
    }

    public void render()
    {
        double pro = Assets.loading.progress();
        if(pro > 0) {
            GL.gl2.begin();
            GL.gl8.draw(Assets.background1, 0, 0, 800, 600);
            GL.gl2.end();
        }
    }

}
