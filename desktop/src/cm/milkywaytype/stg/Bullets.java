package cm.milkywaytype.stg;

import cm.milkywaygl.maths.VMaths;

public class Bullets
{

    SceneSTG scene;

    public Bullets(SceneSTG sce)
    {
        scene = sce;
    }

    private BufBullet copyBul(BufBullet bl)
    {
        BufBullet res = scene.bulletPool.get();
        res.pushTexture(bl.texture());
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

    public void add(BufBullet copy, Action<BufBullet> action, String group,
                    double x, double y, double speed, double degree)
    {
        BufBullet bul = copyBul(copy);
        bul.act(action);
        bul.box4().loc(x, y);
        bul.vec2().vec(speed, degree);
        scene.addBullet(group, bul);
    }

    public void ring(BufBullet copy, Action<BufBullet> action, String group,
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
