package cm.test;

import cm.milkywaytype.stg.Action;
import cm.milkywaytype.stg.Bullet;

public class Act1 extends Action<Bullet>
{

    private class Act2 extends Action<Bullet>
    {
    }

    Act2 sub1 = new Act2()
    {
        public void tickThen()
        {
            if(buf.time() >= 50) {
                buf.vec2().mvDegree(0.01);
            }
        }
    };

    public void tickThen()
    {

    }

}
