package cm.milkywaylib.base;

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
            //promote after ticking
            nowScene.tick();
            nowScene.shadow().tickTurning();
        }
    }

    public static void render()
    {
        if(nowScene != null) {
            nowScene.render();
            nowScene.shadow().renderTurning();
        }
    }

}
