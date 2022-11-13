package cm.milkywaytype.stg;

import cm.milkywaygl.maths.Maths;
import cm.milkywaygl.render.GL;
import cm.milkywaygl.render.wrapper.Area;
import cm.milkywaylib.buffers.Bounder;

public class Enemy extends Bounder
{

    public static final String STAY = "stay";
    public static final String MOVE = "move";

    ActionWith<Enemy, Bullet> action;

    double health;
    double maxHealth;
    double attackEffect;

    public void tickThen()
    {
        super.tickThen();

        if(action != null) {
            action.buf = this;
            action.tick();
        }
    }

    public void renderThen(double x, double y, double w, double h)
    {
        double d = vec2.degree();
        Area moveTimeline = texture(MOVE);
        Area stayTimeline = texture(STAY);

        if(Maths.similarCompare(d, 180, 45)) {
            GL.gl2.dim(moveTimeline, box4);
        }
        else if(Maths.similarCompare(d, 0, 45)) {
            GL.gl.curState().mirrored(true);
            GL.gl2.dim(moveTimeline, box4);
        }
        else {
            GL.gl2.dim(stayTimeline, box4);
        }
    }

    public void act(ActionWith<Enemy, Bullet> act)
    {
        action = act;
    }

    public ActionWith<Enemy, Bullet> action()
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
        setHealth(Maths.min(maxHealth, v));
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
