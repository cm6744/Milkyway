package cm.milkywaytype.stg;

import cm.milkywaygl.util.Pool;
import cm.milkywaylib.base.Scene;
import cm.milkywaylib.buffers.Particle;
import cm.milkywaylib.buffers.ParticlePow;
import cm.milkywaylib.util.BufferGroup;
import cm.milkywaylib.util.BufferQueue;

public class SceneSTG extends Scene
{

    public BufferQueue<Bullet> bullets = new BufferQueue<>();
    public BufferQueue<Enemy> enemies = new BufferQueue<>();
    public BufferGroup<Particle> particles = new BufferGroup<>();
    public Pool<Bullet> bulletPool = new Pool<>(Bullet::new);
    public Pool<ParticlePow> particlePool = new Pool<>(ParticlePow::new);

    public void init()
    {
        super.init();
        bulletPool.fill(5000);
        particlePool.fill(5000);
    }

}
