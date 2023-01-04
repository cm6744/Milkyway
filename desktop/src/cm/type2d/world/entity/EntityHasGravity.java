package cm.type2d.world.entity;

import cm.milkyway.lang.maths.shapes.Vec2;
import cm.milkywayx.physicsx.arcle.Force;
import cm.milkywayx.physicsx.arcle.Quality;
import cm.type2d.room.Room;
import cm.type2d.world.FixedWorld;

public abstract class EntityHasGravity extends Entity
{

    public EntityHasGravity(FixedWorld w, Room r, double sx, double sy)
    {
        super(w, r, sx, sy);
    }

    public static final Vec2 gravity = Vec2.of(0.5, 90);
    private final Vec2 grvProcess = Vec2.normal();

    public void tickThen()
    {
        collision.step(grvProcess);

        grvProcess.add(gravity);
        if(collision.d) {
            grvProcess.set(0, 0);
        }
    }

}
