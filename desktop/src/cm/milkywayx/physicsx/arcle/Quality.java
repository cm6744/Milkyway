package cm.milkywayx.physicsx.arcle;

import cm.milkyway.lang.container.list.List;
import cm.milkyway.lang.maths.shapes.Rect;
import cm.milkyway.lang.maths.shapes.Vec2;

public class Quality
{

    List<Force> forces = new List<>();
    List<Force> forcesAccept = new List<>();
    Vec2 finalForce = Vec2.normal();
    Rect pos;
    Rect simulate = Rect.normal();
    Vec2 speed = Vec2.normal();
    Vec2 accel = Vec2.normal();
    Dimension dim;
    double quality;

    public Quality(Rect r, double ql)
    {
        pos = r;
        quality = ql;
    }

    public void setDim(Dimension d)
    {
        dim = d;
    }

    public double quality()
    {
        return quality;
    }

    public Vec2 speed()
    {
        return speed;
    }

    public Vec2 accel()
    {
        return accel;
    }

    public Rect pos()
    {
        return pos;
    }

    public void apply(Force f)
    {
        forces.add(f);
        forcesAccept.add(f);
        f.apply(this);
    }

    public void applyNotAccpet(Force f)
    {
        forces.add(f);
        f.apply(this);
    }

    public double getYForces()
    {
        Force f;
        double fin = 0;
        for(int i = forcesAccept.last(); i >= 0; i--)
        {
            f = forcesAccept.get(i);
            fin += f.vec2.y();
        }
        return fin;
    }

    public double getXForces()
    {
        Force f;
        double fin = 0;
        for(int i = forcesAccept.last(); i >= 0; i--)
        {
            f = forcesAccept.get(i);
            fin += f.vec2.x();
        }
        return fin;
    }

    Vec2 LEFT = Vec2.of(1, 180);
    Vec2 RIGHT = Vec2.of(1, 0);
    Vec2 UP = Vec2.of(1, -90);
    Vec2 DOWN = Vec2.of(1, 90);

    private boolean hit(Rect r1, Rect r2, Vec2 deg)
    {
        r1.mvLoc(deg.x(), deg.y());
        return r1.interacts(r2);
    }

    public void tick()
    {
        apply(new Force(dim.gravity * quality, 90));
        apply(new Force(-speed.x() / 2, 0));//friction, everywhere

        Force f;
        for(int i = forces.last(); i >= 0; i--)
        {
            f = forces.get(i);
            if(i == forces.last()) {
                finalForce.set(f.vec2);
            }
            else {
                finalForce.add(f.vec2);
            }
        }
        forces.clear();
        forcesAccept.clear();
        accel.setRot(finalForce.ln() / quality, finalForce.degree());
        speed.add(accel);

        Quality q;
        Rect r;

        simulate.copy(pos);

        for(int i = 0; i < dim.qualities.size(); i++)
        {
            q = dim.qualities.get(i);
            r = q.pos;

            if(hit(simulate, r, LEFT) || hit(simulate, r, RIGHT)) {
                speed.mul(0, 1);
            }
            if(hit(simulate, r, UP) || hit(simulate, r, DOWN)) {
                speed.mul(1, 0);
            }
        }

        double acx = speed.x() * dim.step;
        double acy = speed.y() * dim.step;
        pos.mvLoc(acx, acy);
    }

}
