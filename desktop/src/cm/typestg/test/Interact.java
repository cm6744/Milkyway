package cm.typestg.test;

import cm.milkyway.Milkyway;
import cm.milkyway.opengl.audio.ClipPlayer;
import cm.milkyway.opengl.audio.Sound;
import cm.milkywayx.widgetx.base.RenderBuffer;
import cm.milkyway.lang.container.List;
import cm.milkyway.physics.shapes.Rect;
import cm.typestg.*;

import static cm.typestg.Scr.scr;
import static cm.typestg.Share.*;

//high-efficient interaction logic
public class Interact
{

    static Sound die = Milkyway.audio.newSound("sounds/effect/player_die.wav");

    public static void run(Dropping clear)
    {
        Enemy ene;
        int time = 0;
        for(int e = scene.enemies.last(); e >= 0; e--) {
            time++;
            ene = scene.enemies.get(e);
            ene.tick();
            if(fullyOut(ene, scr, 100) || ene.isForRemove()) {
                if(!(ene instanceof Boss)) {
                    scene.enemies.remove(ene);
                }
            }
            if(player.hasBound() && ene.bound().interacts(player.bound())) {
                player.beShot();
                shooter.clear(10000, 0, 0, clear);
                ClipPlayer.play(die);
            }
            blr(clear, ene, e == 0);
        }
        if(time == 0) {
            blr(clear, null, true);
        }
    }

    private static void blr(Dropping clear, Enemy ene, boolean onceCheck)
    {
        if(onceCheck) {
            Bullet bl;
            for(int i = scene.bullets.last(); i >= 0; i--) {
                bl = scene.bullets.get(i);
                if(bl != null) {
                    bl.tick();
                    removeBL(bl, i, scene.bullets);
                    if(player.hasBound()) {
                        if(bl.mark == Bullet.ENEMY) {
                            if(bl.canBound() && bl.bound().interacts(player.bound())) {
                                player.beShot();
                                //shooter.clear(10000, 0, 0, clear);
                                ClipPlayer.play(die);
                            }
                        }
                    }
                }
            }
            for(int i = scene.playerBullets.last(); i >= 0; i--) {
                bl = scene.playerBullets.get(i);
                if(bl != null) {
                    bl.tick();
                    removeBL(bl, i, scene.playerBullets);
                }
            }
            scene.droppings.iterate((o, i) -> {
                o.tick();
                if(o.bound().interacts(player.bound())) {
                    scene.score += (800 - player.box().y()) / 2 + 400;
                    scene.droppings.remove(i);
                    scene.droppingPool.reuse(o);
                }
                if(o.isForRemove() || Interact.fullyOut(o, scr, 0)) {
                    scene.droppings.remove(i);
                    scene.droppingPool.reuse(o);
                }
            }, true);
        }

        Bullet bl;
        for(int i = scene.playerBullets.last(); i >= 0; i--) {
            bl = scene.playerBullets.get(i);
            if(bl != null) {
                if(ene != null && bl.bound().interacts(ene.bound())) {
                    scene.playerBullets.remove(bl);
                    enemyCreator.hurting(bl);
                    scene.bulletPool.reuse(bl);
                    scene.score += 150;
                    hurt(ene, scene, 5);
                }
            }
        }
    }

    private static void removeBL(Bullet bl, int index, List<Bullet> lst)
    {
        if(fullyOut(bl, scr, 100) && !(bl instanceof Laser)) {
            lst.remove(bl);
            shooter.removing(bl);
            scene.bulletPool.reuse(bl);
        }
        else if(bl.isForRemove()) {
            lst.remove(bl);
            shooter.removing(bl);
            if(!(bl instanceof Laser)) {
                scene.bulletPool.reuse(bl);
            }
        }
    }

    public static void hurt(Enemy ene, SceneIngame scene, double hy)
    {
        ene.beShot(hy);

        if(ene.isDead()) {
            scene.score += 1500;
            enemyCreator.removing(ene);
            scene.enemies.remove(ene);
            enemyCreator.drop(scene.dp1, ene.box().x(), ene.box().y(), 3, 36, 24);
        }
    }

    public static boolean fullyOut(RenderBuffer buf, Rect scr, int ofs)
    {
        Rect r2 = buf.box();
        return r2.xc2() + ofs < scr.xc()
                || r2.yc2() + ofs < scr.yc()
                || r2.yc() - ofs > scr.yc2()
                || r2.xc() - ofs > scr.xc2();
    }

}
