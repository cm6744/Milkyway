package cm.milkywaylib.stg;

import cm.milkywaygl.inter.GLRenderable;
import cm.milkywaygl.inter.GLTimeline;
import cm.milkywaygl.util.container.List;
import cm.milkywaygl.util.container.Map;
import cm.milkywaygl.util.container.Pool;
import cm.milkywaylib.linklib.Scene;

public class SceneSTG extends Scene
{

    public Map<String, List<BufBullet>> bullets = new Map<>();
    public Pool<BufBullet> bulletPool = new Pool<>(BufBullet::new);

    public void init()
    {
        super.init();
        bulletPool.fill(5000);
    }

    public void addGroup(String name)
    {
        bullets.put(name, new List<>());
    }

    public void addBullet(String group, BufBullet bul)
    {
        bullets.get(group).add(bul);
    }

}
