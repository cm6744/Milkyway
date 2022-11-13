package cm.test;

import cm.milkywaygl.TaskCaller;
import cm.milkywaygl.render.GL;
import cm.milkywaygl.render.wrapper.Area;
import cm.milkywaylib.buffers.Button;
import cm.milkywaylib.base.Scene;
import cm.milkywaylib.base.SceneManager;

import static cm.test.Main.performed;

public class SceneMenu extends Scene
{

    Button button1;
    Button button2;

    public void init()
    {
        GL.gl.curState().font(Assets.en);
        button1 = doProcess(400, 280, 200, "Start");
        button2 = doProcess(400, 320, 200, "exit");
    }

    private Button doProcess(double x, double y, double w, String n) {
        Button button1 = new Button();
        Area a1 = Area.dim01(Assets.button, 0, 0, 1, 1 / 3.0);
        button1.setTexture(a1, Button.UP);
        Area a2 = Area.dim01(Assets.button, 0, 1 / 3.0, 1, 1 / 3.0);
        button1.setTexture(a2, Button.HANG);
        Area a3 = Area.dim01(Assets.button, 0, 2 / 3.0, 1, 1 / 3.0);
        button1.setTexture(a3, Button.DOWN);

        button1.box4().loc(x, y);
        button1.box4().setSize(w, 20);
        button1.append(n);
        return button1;
    }

    public void tickThen()
    {
        performed.sped = 1;
        performed.tick();
        button1.tick();
        button2.tick();
        if(button1.clickOn()) {
            button1.callDown(20);
            SceneManager.scene(new SceneIngame());
        }
        if(button2.clickOn()) {
            button2.callDown(20);
            TaskCaller.dispose();
        }
    }

    public void render()
    {
        GL.gl.clear();
        GL.gl2.dim(Assets.stg6bg, 0, 0, 800, 600);
        performed.render();
        button1.render();
        button2.render();
        GL.gl.freeAll();
    }

}
