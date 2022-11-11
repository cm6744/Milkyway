package cm.test;

import cm.milkywaygl.maths.Maths;
import cm.milkywaytype.stg.Action;
import cm.milkywaytype.stg.BufBullet;

public class Act1 extends Action<BufBullet>
{

    private class Act2 extends Action<BufBullet>
    {
    }

    Act2 sub1 = new Act2()
    {
        public void tickThen()
        {
            if(buf.time() >= 50) {
                buf.vec2().mvDegree(buf.box4().y() * 0.006);
            }
            if(Maths.random() < 0.01) {
                bullets.add(buf, null, "default", buf.box4().x(), buf.box4().y(), buf.vec2().speed(), buf.vec2().degree());
            }
        }

        ;
    };

    public void tickThen()
    {
        if(buf.time() == 10) {
            buf.vec2().mvDegree(Maths.random(-5, 5));
            bullets.add(buf, sub1, "default", buf.box4().x(), buf.box4().y(), buf.vec2().speed(), buf.vec2().degree());
            //bullets.add(buf, sub1, "default", buf.box4().x(), buf.box4().y(), buf.vec2().speed(), buf.vec2().degree());
            buf.remove();
        }
    }

}
