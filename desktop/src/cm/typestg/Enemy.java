package cm.typestg;

import cm.milkyway.opengl.render.g2d.Area;
import cm.milkyway.opengl.render.g2d.Tex;
import cm.milkyway.opengl.render.graphics.Graphics2D;
import cm.milkywayx.widgetx.widget.Bounder;
import cm.milkyway.lang.maths.Mth;
import cm.milkyway.lang.maths.shapes.Rect;
import cm.typestg.act.Action;

public class Enemy extends Bounder<Rect>
{

    public static Tex SHOOT_UP;
    public static Tex HURT;

    public static final String STAY = "stay";
    public static final String MOVE = "move";

    Action<Enemy> action;

    double health;
    double maxHealth;
    double attackEffect = 1.0;

    protected boolean canRunAct = true;

    protected void initBound()
    {
        bound = new Rect();
        allowBoundRotate = false;
    }

    public void tickThen()
    {
        super.tickThen();

        if(action != null && canRunAct) {
            action.buf = this;
            action.tick();
        }
    }

    public void renderThen(Graphics2D g, double x, double y, double w, double h)
    {
        double d = vecInfo.degree();
        Area moveTimeline = texture(MOVE);
        Area stayTimeline = texture(STAY);

        if(Mth.similarCompare(d, 180, 45)) {
            g.draw(moveTimeline, renderBox);
        }
        else if(Mth.similarCompare(d, 0, 45)) {
            g.setFlipX(true);
            g.draw(moveTimeline, renderBox);
            g.setFlipX(false);
        }
        else {
            g.draw(stayTimeline, renderBox);
        }
    }

    public void act(Action<Enemy> act)
    {
        action = act;
    }

    public Action<Enemy> action()
    {
        return action;
    }

    public void beShot(double atk)
    {
        health -= (atk * attackEffect);
    }

    public double health()
    {
        return health;
    }

    public void setHealth(double heal)
    {
        health = heal;
    }

    public double maxHealth()
    {
        return maxHealth;
    }

    public void setMaxHealth(double max)
    {
        maxHealth = max;
    }

    public boolean isDead()
    {
        return health <= 0;
    }

    public void cure(double v)
    {
        setHealth(Mth.min(maxHealth, health + v));
    }

    public void cure()
    {
        cure(maxHealth);
    }

    public void setAttackEffect(double effect)
    {
        attackEffect = effect;
    }

    public double attackEffect()
    {
        return attackEffect;
    }

}
