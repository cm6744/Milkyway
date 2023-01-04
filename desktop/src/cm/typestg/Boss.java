package cm.typestg;

import cm.milkyway.opengl.render.g2d.Area;
import cm.milkyway.lang.maths.Mth;
import cm.milkyway.lang.maths.shapes.Rect;
import cm.milkyway.opengl.render.graphics.Graphics2D;

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

    public void renderThen(Graphics2D g, double x, double y, double w, double h)
    {
        renderBoxFloat.copy(renderBox);
        renderBoxFloat.mvLoc(0, Mth.sin(time() * 0.1));

        double d = vecInfo.degree();
        Area moveTimeline = texture(MOVE);
        Area stayTimeline = texture(STAY);

        if(Mth.similarCompare(d, 180, 45)) {
            g.draw(moveTimeline, renderBoxFloat);
        }
        else if(Mth.similarCompare(d, 0, 45)) {
            g.setFlipX(true);
            g.draw(moveTimeline, renderBoxFloat);
            g.setFlipX(false);
        }
        else {
            g.draw(stayTimeline, renderBoxFloat);
        }
    }

    public boolean isDead()
    {
        return logic.dead && super.isDead();
    }

}
