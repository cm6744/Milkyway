package cm.typestg;

import cm.milkyway.opengl.render.g2d.AreaStatic;
import cm.milkywayx.particlex.Particle;
import cm.milkyway.lang.container.List;
import cm.milkyway.lang.maths.Mth;
import cm.milkyway.lang.maths.VecMth;
import cm.typestg.act.Action;

public class EnemyCreator
{

    SceneSTG scene;
    AreaStatic effect;
    AreaStatic hurt;

    public EnemyCreator(SceneSTG sce)
    {
        scene = sce;
        effect = AreaStatic.create(Enemy.SHOOT_UP);
        hurt = AreaStatic.create(Enemy.HURT);
    }

    private Enemy copyEne(Enemy e)
    {
        Enemy res = new Enemy();
        res.setTexture(e.texture(Enemy.MOVE).copy(), Enemy.MOVE);
        res.setTexture(e.texture(Enemy.STAY).copy(), Enemy.STAY);
        res.vec().copy(e.vec());
        res.box().copy(e.box());
        res.bound().copy(e.bound());
        res.setMaxHealth(e.maxHealth());
        res.cure();
        res.setTime(0);
        res.act(null);
        res.cancelRemove();
        return res;
    }

    private Dropping copyDrop(Dropping e)
    {
        Dropping res = scene.droppingPool.get();
        res.setTexture(e.texture().copy());
        res.vec().copy(e.vec());
        res.box().copy(e.box());
        res.bound().copy(e.bound());
        res.goal(e.goal, e.distance);
        res.setTime(0);
        res.cancelRemove();
        return res;
    }

    public void removing(Enemy e)
    {
        Particle particle = scene.particlePool.get();
        particle.cancelRemove();
        particle.setTime(0);
        particle.setTexture(effect);
        particle.box().copy(e.box());
        particle.zoom(10);
        particle.easeIn(0);
        particle.easeOut(20);
        particle.aliveTime(20);
        particle.vec().vec(0, 0);
        particle.rotation(36);
        scene.particles.add(particle);
    }

    public void hurting(Bullet b)
    {
        Particle particle = scene.particlePool.get();
        particle.cancelRemove();
        particle.setTime(0);
        particle.setTexture(hurt);
        particle.box().copy(b.box());
        particle.box().mvLoc(Mth.random(-10, 10), Mth.random(-5, 5));
        particle.zoom(0);
        particle.easeIn(0);
        particle.easeOut(20);
        particle.aliveTime(20);
        particle.vec().copy(b.vec());
        particle.vec().setSpeed(3.2);
        particle.vec().mvDegree(Mth.random(-12, 12));
        particle.effect().setRotation(particle.vec().degree());
        particle.rotation(0);
        scene.particles.add(particle);
    }

    public void clear(double dist, double x, double y, Dropping drop)
    {
        scene.enemies.iterate((o, i) -> {
            if(o instanceof Boss) {
                return;
            }
            if(VecMth.distanceBetweenAB(o.box().x(), o.box().y(), x, y) <= dist) {
                removing(o);
                scene.enemies.remove(i);
                if(drop != null && Mth.random() < 0.3) {
                    Share.enemyCreator.drop(drop, o.box().x(), o.box().y(), 3, 12, 14);
                    Share.enemyCreator.collectDrops();
                }
            }
        }, true);
    }

    public void clear(double dist, double x, double y)
    {
        clear(dist, x, y, null);
    }

    public void add(Enemy copy, Action<Enemy> action, List<Enemy> group,
                    double x, double y, double speed, double degree)
    {
        Enemy bul = copyEne(copy);
        bul.act(action);
        bul.box().loc(x, y);
        bul.vec().vec(speed, degree);
        scene.enemies.add(bul);
        if(group != null) {
            group.add(bul);
        }
    }

    public void add(Boss copy, List<Enemy> group,
                    double x, double y, double speed, double degree, double gx, double gy)
    {
        copy.box().loc(x, y);
        copy.vec().vec(speed, degree);
        scene.enemies.add(copy);
        if(group != null) {
            group.add(copy);
        }
        copy.logic.goalX = gx;
        copy.logic.goalY = gy;
        copy.logic.logicUntilArrive = true;
        Share.shooter.clear(10000, 0, 0);
        clear(10000, 0, 0);
    }

    public void drop(Dropping copy, double x, double y, double speed, double spread, double count)
    {
        for(int i = 0; i < count; i++) {
            Dropping dp = copyDrop(copy);
            dp.box().loc(x, y);
            dp.vec().setSpeed(speed);
            dp.box().mvLoc(Mth.random(-spread, spread), Mth.random(-spread, spread));
            scene.droppings.add(dp);
        }
    }

    public void collectDrops()
    {
        scene.droppings.iterate((o, i) -> {
            o.goal(Share.player.bound(), 10000);
        }, true);
    }

}
