package cm.typestg;

import cm.milkyway.opengl.render.g2d.AreaAnimated;
import cm.milkyway.opengl.render.g2d.Tex;
import cm.typestg.test.Assets;

public class EnemyMap
{

    public static Enemy[] enemies = new Enemy[128];

    public static void register(int type, double bw, double bh, double rw, double rh, double hlt, Tex tex)
    {
        enemies[type] = new Enemy();
        enemies[type].box().resize(rw, rh);
        enemies[type].bound().resize(bw, bh);
        enemies[type].setTexture(new AreaAnimated(tex, 4, 2, 0).perTime(5), Enemy.STAY);
        enemies[type].setTexture(new AreaAnimated(tex, 4, 2, 1).perTime(5), Enemy.MOVE);
        enemies[type].setMaxHealth(hlt);
    }

    public static void registerOwned(int type, double bw, double bh, double rw, double rh, double hlt, Tex tex)
    {
        enemies[type] = new Enemy();
        enemies[type].box().resize(rw, rh);
        enemies[type].bound().resize(bw, bh);
        enemies[type].setTexture(AreaOwnedEnemy.create(tex, Assets.loader.getTex("magicCir")), Enemy.STAY);
        enemies[type].setTexture(AreaOwnedEnemy.create(tex, Assets.loader.getTex("magicCir")), Enemy.MOVE);
        enemies[type].setMaxHealth(hlt);
    }

    public static void registerAll() {
        Tex tex1 = Assets.loader.getTex("enemy1");
        Tex tex2 = Assets.loader.getTex("enemyGlow");

        register(0, 64, 64, 42, 42, 100, tex1);
        registerOwned(50, 64, 64, 24, 24, 100, tex2);
    }

}
