package cm.typestg.test;

import cm.backends.lwjgl.LwjglTexture;
import cm.milkyway.lang.io.AccessLocal;
import cm.milkyway.opengl.render.g2d.Tex;
import cm.milkyway.opengl.render.graphics.Graphics2D;
import cm.milkywayx.widgetx.scene.Scene;
import cm.milkywayx.widgetx.scene.SceneManager;
import cm.typestg.Bullet;
import cm.typestg.BulletMap;
import cm.typestg.Enemy;
import cm.typestg.EnemyMap;

public class SceneLoad extends Scene
{

    Tex tex = new LwjglTexture();

    public void init()
    {
        Assets.loadAll();
        tex.load(new AccessLocal("textures/logo.png"));
    }

    public void tickThen()
    {
        if(time() >= 20) {
            Assets.loader.update();
        }
        if(Assets.loader.isDone()) {

            BulletMap.SIZE16 = Assets.loader.getTex("b16");
            BulletMap.SIZE32 = Assets.loader.getTex("b32");
            BulletMap.SIZE64 = Assets.loader.getTex("b64");
            Bullet.FOG = Assets.loader.getTex("bulletFogs");
            BulletMap.registerAll();
            EnemyMap.registerAll();

            Enemy.SHOOT_UP = Assets.loader.getTex("magicCir");
            Enemy.HURT = Assets.loader.getTex("fire");
            SceneManager.scene(new SceneMenu());
        }
    }

    public void render(Graphics2D g)
    {
        g.getContext().clear();
        g.draw(tex, 0, 0, 640, 480);
        g.getContext().paint();
    }

}
