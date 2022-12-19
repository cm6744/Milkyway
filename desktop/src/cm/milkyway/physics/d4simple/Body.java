package cm.milkyway.physics.d4simple;

import cm.milkyway.physics.shapes.Rect;

public class Body
{

    Rect bound;
    Rect simulate = new Rect();

    public Body(Rect bd)
    {
        bound = bd;
    }

    public Rect bound()
    {
        return bound;
    }

    public void run(World world, double xSpeed, double ySpeed, ForceInfo cache)
    {
        Rect r;

        double acx = xSpeed, acy = ySpeed;

        for(int i = 0; i < world.bounds().size(); i++) {
            r = world.bounds().get(i);

            simulate.copy(bound);
            simulate.mvLoc(xSpeed, ySpeed);
            int times = 0;

            while(simulate.interacts(r)) {
                times++;
                if(times > 100) {
                    break;
                }

                simulate.copy(bound);
                simulate.mvLoc(acx, acy);

                if(xSpeed < 0 && simulate.xc() < r.xc2()) {
                    acx /= 2;
                }
                if(xSpeed > 0 && simulate.xc2() > r.xc()) {
                    acx /= 2;
                }
                if(ySpeed < 0 && simulate.yc() < r.yc2()) {
                    acy /= 2;
                    cache.add(ForceInfo.States.REACH_TOP);
                }
                if(ySpeed > 0 && simulate.yc2() > r.yc()) {
                    acy /= 2;
                    cache.add(ForceInfo.States.ON_LAND);
                }
            }
        }

        bound.mvLoc(acx, 0);
        bound.mvLoc(0, acy);
    }

}
