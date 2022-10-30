package cm.milkywaylib.linklib;

public class SceneManager
{

    static Scene nowScene;

    public static void scene(Scene s)
    {
        nowScene = s;
        s.init();
    }

    public static void tick()
    {
        if(nowScene != null) {
            nowScene.tick();
        }
    }

    public static void render()
    {
        if(nowScene != null) {
            nowScene.render();
        }
    }

}
