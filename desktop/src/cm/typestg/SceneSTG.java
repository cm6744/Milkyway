package cm.typestg;

import cm.milkywayx.widgetx.base.Scene;
import cm.milkywayx.particlex.Particle;
import cm.milkyway.lang.cache.Pool;
import cm.milkyway.lang.container.List;

public class SceneSTG extends Scene
{

    public List<Bullet> bullets = new List<>();
    public List<Bullet> playerBullets = new List<>();
    public List<Enemy> enemies = new List<>();
    public List<Dropping> droppings = new List<>();
    public List<Particle> particles = new List<>();

    public Pool<Bullet> bulletPool = new Pool<>(Bullet::new);
    public Pool<Particle> particlePool = new Pool<>(Particle::new);
    public Pool<Dropping> droppingPool = new Pool<>(Dropping::new);

    public void init()
    {
        super.init();
        bulletPool.fill(5000);
        particlePool.fill(5000);
        droppingPool.fill(5000);
    }

}
