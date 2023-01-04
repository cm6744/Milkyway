package cm.milkywayx.widgetx.scene;

import cm.milkyway.opengl.render.graphics.Graphics2D;

public class SceneManager
{

    static Scene nowScene;

    public static void withoutTurning(Scene s)
    {
        nowScene = s;
        nowScene.init();
    }

    public static void scene(Scene s)
    {
        if(nowScene != null) {
            nowScene.shadow.turnIn(s);
        }
        else {
            withoutTurning(s);
        }
    }

    public static void tick()
    {
        if(nowScene != null) {
            nowScene.tick();
            nowScene.shadow().tickTurning();
        }
    }

    public static void render(Graphics2D g)
    {
        if(nowScene != null) {
            nowScene.render(g);
            nowScene.shadow().renderTurning(g);
        }
    }

    public static Scene current()
    {
        return nowScene;
    }

}
