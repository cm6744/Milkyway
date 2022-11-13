package cm.test;

import cm.milkywaygl.render.GL;
import cm.milkywaygl.TaskCaller;
import cm.milkywaygl.render.wrapper.Area;
import cm.milkywaylib.buffers.ProgressLine;
import cm.milkywaylib.base.Scene;
import cm.milkywaylib.base.SceneManager;

public class SceneLoad extends Scene
{

    ProgressLine progress = new ProgressLine();

    public void init()
    {
        Main.performed = new GL3Performed(GL.gl);
        Main.performed.init();
        progress.box4().loc(400, 550);
        progress.box4().setSize(800, 1.5);
        Area em = Area.dim01(Assets.progress, 0, 0.5, 1, 0.5);
        progress.setTexture(em, ProgressLine.EMPTY);
        Area f = Area.dim01(Assets.progress, 0, 0, 1, 0.5);
        progress.setTexture(f, ProgressLine.FULL);
    }

    public void tickThen()
    {
        Main.performed.tick();
        if(TaskCaller.isTickCalm()) {
            Assets.loading.update();
        }

        if(Assets.loading.isDone()) {
            SceneManager.scene(new SceneMenu());
        }
    }

    public void render()
    {
        GL.gl.clear();
        double pro = Assets.loading.progress();
        GL.gl2.dim(Assets.stg6bg, 0, 0, 800, 600);
        Main.performed.render();
        GL.gl2.dim(Assets.loadingFont, 5, 570, 128, 32);
        progress.value(pro);
        progress.render();
        GL.gl.freeAll();
    }

}
