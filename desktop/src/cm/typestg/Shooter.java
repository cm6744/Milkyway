package cm.typestg;

import cm.milkyway.opengl.render.g2d.Area;
import cm.milkyway.opengl.render.g2d.AreaStatic;
import cm.milkyway.opengl.audio.ClipPlayer;
import cm.milkywayx.particlex.Particle;
import cm.milkyway.lang.container.List;
import cm.milkyway.lang.maths.Mth;
import cm.milkyway.lang.maths.VecMth;
import cm.typestg.act.Action;

public class Shooter
{

    SceneSTG scene;
    Area[] pars = new Area[Bullet.COLOR_MAX];

    public Shooter(SceneSTG sce)
    {
        scene = sce;
        for(int i = 0; i < Bullet.COLOR_MAX; i++) {
            pars[i] = AreaStatic.dim(Bullet.FOG, i * 24, 0, 24, 24);
        }
    }

    private Bullet copyBul(Bullet bl, int mk)
    {
        Bullet res = scene.bulletPool.get();
        res.vec().copy(bl.vec());
        res.box().copy(bl.box());
        res.setTexture(bl.texture());
        res.retype(bl.type());
        res.dye(bl.color());
        res.bound().copy(bl.bound());
        res.setTime(0);
        res.act(null);
        res.cancelRemove();
        res.mark = mk;
        res.rotating = bl.rotating;
        res.rotatable = bl.rotatable;
        res.rotateSpeed = (Mth.random() < 0.5 ? 5 : -5);
        res.setAliveTime(1000000000);
        return res;
    }

    private Laser copyLas(Laser bl, int mk)
    {
        Laser res = new Laser();
        res.vec().copy(bl.vec());
        res.box().copy(bl.box());
        res.setTexture(bl.texture());
        res.retype(bl.type());
        res.dye(bl.color());
        res.bound().copy(bl.bound());
        res.setTime(0);
        res.act(null);
        res.cancelRemove();
        res.mark = mk;
        res.rotating = bl.rotating;
        res.rotatable = bl.rotatable;
        res.rotateSpeed = (Mth.random() < 0.5 ? 5 : -5);
        res.setAliveTime(1000000000);
        res.setDelay(0);
        return res;
    }

    public void adding(Bullet b)
    {
        Particle particle = scene.particlePool.get();
        particle.cancelRemove();
        particle.setTime(0);
        particle.setTexture(pars[b.color()]);
        particle.box().copy(b.box());
        particle.box().mulSize(2, 2);
        particle.aliveTime(Bullet.FOGGY_TIME);
        particle.zoom(-1);
        particle.easeIn(0);
        particle.easeOut(Bullet.FOGGY_TIME);
        particle.vec().copy(b.vec());
        particle.vec().mulSpeed(0.5);
        particle.rotation(0);
        particle.effect().setRotation(particle.vec().degree());
        scene.particles.add(particle);
    }

    public void removing(Bullet b)
    {
        Particle particle = scene.particlePool.get();
        particle.cancelRemove();
        particle.setTime(0);
        particle.setTexture(pars[b.color()]);
        particle.box().copy(b.box());
        particle.aliveTime(Bullet.FOGGY_TIME);
        particle.zoom(1);
        particle.easeIn(0);
        particle.easeOut(Bullet.FOGGY_TIME);
        particle.vec().vec(0, b.vec().degree());
        particle.rotation(0);
        particle.effect().setRotation(particle.vec().degree());
        scene.particles.add(particle);
    }

    public void clear(double dist, double x, double y, Dropping drop)
    {
        scene.bullets.iterate((o, i) -> {
            if(VecMth.distanceBetweenAB(o.box().x(), o.box().y(), x, y) <= dist) {
                removing(o);
                scene.bullets.remove(i);
                if(drop != null) {
                    Share.enemyCreator.drop(drop, o.box().x(), o.box().y(), 3, 12, 1);
                    Share.enemyCreator.collectDrops();
                }
            }
        }, true);
        scene.playerBullets.iterate((o, i) -> {
            if(VecMth.distanceBetweenAB(o.box().x(), o.box().y(), x, y) <= dist) {
                removing(o);
                scene.playerBullets.remove(i);
            }
        }, true);
    }

    public void clear(double dist, double x, double y)
    {
        clear(dist, x, y, null);
    }

    public void add(Bullet copy, Action<Bullet> action, List<Bullet> group,
                    double x, double y, double speed, double degree, int mk, boolean effect)
    {
        Bullet bul = copyBul(copy, mk);
        bul.act(action);
        bul.box().loc(x, y);
        bul.vec().vec(speed, degree);
        if(bul.mark == Bullet.PLAYER) {
            scene.playerBullets.add(bul);
        }
        else {
            scene.bullets.add(bul);
        }
        if(effect) {
            adding(bul);
        }
        if(group != null) {
            group.add(bul);
        }
        ClipPlayer.play(BulletMap.type_sound_mat[bul.type()]);
    }

    public void add(Bullet copy, Action<Bullet> action, List<Bullet> group,
                    double x, double y, double speed, double degree)
    {
        add(copy, action, group, x, y, speed, degree, Bullet.ENEMY, true);
    }

    public void laser(Bullet copy, Action<Bullet> action, List<Bullet> group,
                      double x, double y, double degree, int mk, boolean effect, int alive, int delay)
    {
        Laser bul = copyLas((Laser) copy, mk);
        bul.alive = alive;
        bul.delay = delay;
        double x2 = VecMth.vectorX(x, bul.box().w() / 2, degree);
        double y2 = VecMth.vectorY(y, bul.box().w() / 2, degree);
        bul.box().loc(x2, y2);
        bul.vec().vec(0, degree);

        if(bul.mark == Bullet.PLAYER) {
            scene.playerBullets.add(bul);
        }
        else {
            scene.bullets.add(bul);
        }
        if(effect) {
            adding(bul);
        }
        if(group != null) {
            group.add(bul);
        }
    }

    public void laser(Bullet copy, Action<Bullet> action, List<Bullet> group,
                      double x, double y, double degree, int alive, int delay)
    {
        laser(copy, action, group, x, y, degree, Bullet.ENEMY, false, alive, delay);
    }

    public void laserRing(Bullet copy, Action<Bullet> action, List<Bullet> group,
                          double x, double y, int size, double offset, double degree, int alive, int delay)
    {
        double each = 360.0 / size;
        for(int i = 0; i < size; i++) {
            double deg = degree + i * each;
            double x2 = VecMth.vectorX(x, offset, deg);
            double y2 = VecMth.vectorY(y, offset, deg);
            laser(copy, action, group, x2, y2, deg, alive, delay);
        }
    }

    public void ring(Bullet copy, Action<Bullet> action, List<Bullet> group,
                     double x, double y, int size, double offset, double speed, double degree)
    {
        double each = 360.0 / size;
        for(int i = 0; i < size; i++) {
            double deg = degree + i * each;
            double x2 = VecMth.vectorX(x, offset, deg);
            double y2 = VecMth.vectorY(y, offset, deg);
            add(copy, action, group, x2, y2, speed, deg);
        }
    }

}
