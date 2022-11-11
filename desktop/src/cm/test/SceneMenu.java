package cm.test;

import cm.milkywaygl.render.GL;
import cm.milkywaylib.linkdown.BufButton;
import cm.milkywaylib.linklib.Scene;
import cm.milkywaylib.linklib.SceneManager;

import static cm.test.Main.performed;

public class SceneMenu extends Scene
{

    BufButton button1 = new BufButton();

    public void init()
    {
        GL.gl.curState().font(Assets.en);
        button1.box4().loc(400, 300);
        button1.box4().setSize(200, 20);
        button1.pushTexture(Assets.button);
        button1.append("Adventure");
    }

    public void tickThen()
    {
        performed.sped = 1;
        performed.tick();
        button1.tick();
        if(button1.clickOn()) {
            button1.callDown(20);
            SceneManager.scene(new SceneIngame());
        }
    }

    public void render()
    {
        GL.gl.clear();
        GL.gl2.dim(Assets.stg6bg, 0, 0, 800, 600);
        performed.render();
        button1.render();
        GL.gl.freeAll();
    }

}
