package cm.typestg;

import cm.milkyway.opengl.render.g2d.Color;
import cm.milkyway.lang.maths.VecMth;
import cm.milkyway.opengl.render.graphics.Graphics2D;

public class Laser extends Bullet
{

    double delay;

    public void tickThen()
    {
        if(time() == delay) {
            Share.shooter.adding(this);
        }

        super.tickThen();
    }

    public void renderThen(Graphics2D g, double x, double y, double w, double h)
    {
        x = box().x();
        y = box().y();
        //NOT USE -DEGREE!!!!!, BUT USE -SPEED!!!!
        //TO OPPOSE DEGREE, 180-IT
        double x1 = VecMth.vectorX(x, -w / 2, vecInfo.degree());
        double y1 = VecMth.vectorY(y, -w / 2, vecInfo.degree());
        double x2 = VecMth.vectorX(x, w / 2, vecInfo.degree());
        double y2 = VecMth.vectorY(y, w / 2, vecInfo.degree());

        if(time() > delay) {
            super.renderThen(g, x, y, w, h);
        }
        else {
            g.drawLine(Color.C1111, x1, y1, x2, y2);
        }
        g.draw(Share.shooter.pars[color], x1 - 16, y1 - 16, 32, 32);
    }

    public void setDelay(double i)
    {
        delay = i;
    }

    public double delay()
    {
        return delay;
    }

    public boolean canBound()
    {
        return time() > delay;
    }

}
