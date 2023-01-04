package cm.type2d.world.entity;

import cm.milkyway.lang.container.list.List;
import cm.milkyway.lang.maths.shapes.Rect;
import cm.milkyway.lang.maths.shapes.Vec2;

public class Collision
{

    Entity e;
    Rect sim = Rect.normal();
    public boolean l, r, u, d;

    public Collision(Entity ent)
    {
        e = ent;
    }

    public void step(Vec2 speed)
    {
        Rect eb = e.bound();
        List<Entity> rs = e.room.entities;

        Entity e1;
        Rect eb1;
        int kickTimeX = 0, kickTimeY = 0;
        for(int i = 0; i < rs.size(); i++) {
            e1 = rs.get(i);
            eb1 = e1.bound();
            if(e1 == e || !e1.isHardCollision(e)) continue;

            double xSpeed = speed.x(), ySpeed = speed.y();
            double acx = xSpeed, acy = ySpeed;

            if(xSpeed > 0) {
                l = false;
            }
            else if(xSpeed < 0) {
                r = false;
            }
            if(ySpeed > 0) {
                u = false;
            }
            else if(ySpeed < 0) {
                d = false;
            }

            sim.copy(eb);
            sim.mvLoc(xSpeed, ySpeed);
            int times = 0;
            while(sim.interacts(eb1)) {
                times++;
                if(times > 100) {
                    break;
                }
                sim.copy(eb);
                sim.mvLoc(acx, acy);
                if(xSpeed < 0 && sim.xc() < eb1.xc2()) {
                    acx /= 2;
                    l = true;
                }
                if(xSpeed > 0 && sim.xc2() > eb1.xc()) {
                    acx /= 2;
                    r = true;
                }
                if(ySpeed < 0 && sim.yc() < eb1.yc2()) {
                    acy /= 2;
                    u = true;
                }
                if(ySpeed > 0 && sim.yc2() > eb1.yc()) {
                    acy /= 2;
                    d = true;
                }
            }

            eb.mvLoc(acx, acy);
        }
    }

}
