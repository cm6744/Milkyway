package cm.test;

import cm.milkywaygl.Milkyway;
import cm.milkywaygl.render.g2d.BufferTex;
import cm.milkywaylib.base.Scene;
import cm.milkywaylib.base.SceneManager;
import cm.stgtype.Bullet;
import cm.stgtype.BulletMap;
import cm.stgtype.Enemy;

public class SceneLoad extends Scene
{

    BufferTex tex = Milkyway.graphics.newTex();

    public void init()
    {
        Assets.loadAll();
        Milkyway.gl2d.loadTexture(tex, "textures/logo.png");
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

            Enemy.SHOOT_UP = Assets.loader.getTex("magicCir");
            Enemy.HURT = Assets.loader.getTex("fire");
            SceneManager.scene(new SceneMenu());
        }
    }

    public void render()
    {
        Milkyway.gl2d.dim(tex, 0, 0, 640, 480);
        Milkyway.glBase.freeAll();
    }

}
