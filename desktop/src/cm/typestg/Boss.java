package cm.typestg;

import cm.milkyway.Milkyway;
import cm.milkyway.opengl.render.g2d.Area;
import cm.milkyway.lang.maths.Mth;
import cm.milkyway.physics.shapes.Rect;

public class Boss extends Enemy
{

    BossLogic logic;

    public void setLogic(BossLogic l)
    {
        logic = l;
    }

    public void tickThen()
    {
        logic.boss = this;
        logic.tick();

        canRunAct = !isDead() && (logic.actRom <= 0);

        super.tickThen();
    }

    Rect renderBoxFloat = new Rect();

    public void renderThen(double x, double y, double w, double h)
    {
        renderBoxFloat.copy(renderBox);
        renderBoxFloat.mvLoc(0, Mth.sin(time() * 0.1));

        double d = vecInfo.degree();
        Area moveTimeline = texture(MOVE);
        Area stayTimeline = texture(STAY);

        if(Mth.similarCompare(d, 180, 45)) {
            Milkyway.gl2d.dim(moveTimeline, renderBoxFloat);
        }
        else if(Mth.similarCompare(d, 0, 45)) {
            Milkyway.glBase.state().mirrored(true);
            Milkyway.gl2d.dim(moveTimeline, renderBoxFloat);
        }
        else {
            Milkyway.gl2d.dim(stayTimeline, renderBoxFloat);
        }
    }

    public boolean isDead()
    {
        return logic.dead && super.isDead();
    }

}