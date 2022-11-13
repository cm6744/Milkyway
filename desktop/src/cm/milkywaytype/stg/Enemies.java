package cm.milkywaytype.stg;

import cm.milkywaygl.render.wrapper.AnimatedArea;

public class Enemies
{

    SceneSTG scene;

    public Enemies(SceneSTG sce)
    {
        scene = sce;
    }

    private Enemy copyEne(Enemy e)
    {
        Enemy res = new Enemy();
        res.setTexture(e.texture(Enemy.MOVE).copy(), Enemy.MOVE);
        res.setTexture(e.texture(Enemy.STAY).copy(), Enemy.STAY);
        res.vec2().copy(e.vec2());
        res.box4().copy(e.box4());
        res.bound().copy(e.bound());
        res.setTime(0);
        res.act(null);
        res.cancelRemove();
        return res;
    }

    public void add(Enemy copy, ActionWith<Enemy, Bullet> action, String group,
                    double x, double y, double speed, double degree)
    {
        Enemy bul = copyEne(copy);
        bul.act(action);
        bul.box4().loc(x, y);
        bul.vec2().vec(speed, degree);
        scene.enemies.getGroup(group).buf().add(bul);
    }

}
