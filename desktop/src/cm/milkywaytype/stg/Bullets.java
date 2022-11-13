package cm.milkywaytype.stg;

import cm.milkywaygl.maths.VMaths;
import cm.milkywaygl.render.wrapper.Area;
import cm.milkywaylib.buffers.ParticlePow;

public class Bullets
{

    SceneSTG scene;
    Area[] pars = new Area[Bullet.COLOR_MAX];

    public Bullets(SceneSTG sce)
    {
        scene = sce;
        for(int i = 0; i < Bullet.COLOR_MAX; i++) {
            pars[i] = Area.dim01(Bullet.FOG, 1.0 / Bullet.COLOR_MAX * i, 0, 1.0 / Bullet.COLOR_MAX, 1);
        }
    }

    private Bullet copyBul(Bullet bl)
    {
        Bullet res = scene.bulletPool.get();
        res.vec2().copy(bl.vec2());
        res.box4().copy(bl.box4());
        res.retype(bl.type());
        res.dye(bl.color());
        res.bound().copy(bl.bound());
        res.setTime(0);
        res.act(null);
        res.cancelRemove();
        return res;
    }

    private void adding(Bullet b)
    {
        ParticlePow particle = scene.particlePool.get();
        particle.cancelRemove();
        particle.setTime(0);
        particle.setTexture(pars[b.color()]);
        particle.box4().copy(b.box4());
        particle.box4().mulSize(1.5, 1.5);
        particle.sizeLimit(b.box4().width());
        particle.period(20);
        scene.particles.buf().add(particle);
    }

    public void add(Bullet copy, Action<Bullet> action, String group,
                    double x, double y, double speed, double degree)
    {
        Bullet bul = copyBul(copy);
        bul.act(action);
        bul.box4().loc(x, y);
        bul.vec2().vec(speed, degree);
        scene.bullets.getGroup(group).buf().add(bul);
        adding(bul);
    }

    public void ring(Bullet copy, Action<Bullet> action, String group,
                     double x, double y, int size, double offset, double speed, double degree)
    {
        double each = 360.0 / size;
        for(int i = 0; i < size; i++) {
            double deg = degree + i * each;
            double x2 = VMaths.vectorX(x, offset, deg);
            double y2 = VMaths.vectorY(y, offset, deg);
            add(copy, action, group, x2, y2, speed, deg);
        }
    }

}
