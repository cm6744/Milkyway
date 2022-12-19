package cm.typestg;

import cm.milkywayx.widgetx.base.Timeline;
import cm.milkyway.lang.container.List;
import cm.milkyway.lang.maths.Mth;
import cm.milkyway.lang.maths.VecMth;
import cm.typestg.act.Action;

public class BossLogic extends Timeline
{

    public Boss boss;
    public List<Action<Enemy>> actions = new List<>();
    public boolean moveByPlayer = true;
    public double mvDimY1, mvDimY2;
    public double mvDimX1, mvDimX2;
    boolean dead;
    int moveRom;
    int actRom;
    double goalX, goalY;
    public boolean logicUntilArrive;
    boolean arrive;
    boolean curing;
    boolean stay;

    public void add(Action<Enemy> a)
    {
        actions.add(a);
    }

    public void tickThen()
    {
        actRom--;
        if(actRom > 0) {
            if(actRom == 70) {
                (Share.scene).quake = 55;
            }
            return;
        }

        if(curing) {
            boss.cure(10);
            boss.setAttackEffect(0);
            if(boss.health() >= boss.maxHealth()) {
                boss.setAttackEffect(1);
                curing = false;
            }
        }

        boss.setAttackEffect(Mth.min(1, 0.0004 * time()));

        double dg = VecMth.degreeBetweenAB(goalX, goalY, boss.box().x(), boss.box().y());
        double dist = VecMth.distanceBetweenAB(goalX, goalY, boss.box().x(), boss.box().y());

        if(dist > 10) {
            boss.vec().vec(dist * 0.05, dg);
            arrive = false;
        }
        else {
            boss.vec().vec(0, 90);
            arrive = true;
        }

        if(boss.action() == null && (!logicUntilArrive || arrive)) {
            boss.act(actions.remove(0));
        }

        if(boss.health() < 0 && !dead && !curing) {
            Action<Enemy> ea = actions.remove(0);
            if(ea == null) {
                dead = true;
                boss.act(null);
            }
            else {
                boss.setHealth(1);
                boss.setAttackEffect(0);
                curing = true;
                boss.act(ea);
                boss.setTime(0);
            }

            actRom = 180;
            Share.shooter.clear(10000, 0, 0, Share.scene.dp1);
            Share.enemyCreator.clear(10000, 0, 0, Share.scene.dp1);
        }

        moveRom--;
        if(!stay) {
            Player player = Share.player;
            if(
                    moveByPlayer && time() % 20 == 0
                            && Mth.random() < 0.5
                            && !Mth.similarCompare(boss.box().x(), player.box().x(), 20)
                            && moveRom < 0
            ) {
                goalX = player.box().x();
                double dst = Mth.abs(mvDimY1 - mvDimY2);
                goalY = (mvDimY1 + mvDimY2) * 0.5 + Mth.random(-dst, dst);
                moveRom = 100;
            }

            if(moveRom < 0 && Mth.random() < 0.01) {
                goalX = Mth.random(mvDimX1, mvDimX2);
                goalY = Mth.random(mvDimY1, mvDimY2);
                moveRom = 100;
            }
        }

    }

}
