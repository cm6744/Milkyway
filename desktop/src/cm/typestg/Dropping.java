package cm.typestg;

import cm.milkywayx.widgetx.widget.Bounder;
import cm.milkyway.lang.maths.VecMth;
import cm.milkyway.lang.maths.shapes.Rect;

public class Dropping extends Bounder<Rect>
{

    double distance;
    Rect goal;

    public void goal(Rect gol, double dst)
    {
        goal = gol;
        distance = dst;
    }

    public void tickThen()
    {
        super.tickThen();

        if(VecMth.distanceBetweenAB(bound(), goal) <= distance) {
            vec().setDegree(VecMth.degreeBetweenAB(goal, bound()));
            vec().mvSpeed(0.1);
        }
        else {
            vec().setDegree(90);
            vec().mvSpeed(0.01);
        }
    }

    protected void initBound()
    {
        bound = new Rect();
        allowBoundRotate = false;
    }

}
