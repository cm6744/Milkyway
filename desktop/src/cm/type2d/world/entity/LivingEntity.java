package cm.type2d.world.entity;

import cm.type2d.registry.Registry;
import cm.type2d.room.Room;
import cm.type2d.world.FixedWorld;

public abstract class LivingEntity extends EntityHasGravity
{

    public LivingAttribute attr;

    public LivingEntity(FixedWorld w, Room r, double sx, double sy)
    {
        super(w, r, sx, sy);
        attr = Registry.ENTITY_ATTR.get(name()).copy();
    }

    public LivingAttribute getAttribute()
    {
        return attr;
    }

    public void tickThen()
    {
        attr.hunger -= attr.hungerRate;
        attr.thirst -= attr.thirstRate;
        super.tickThen();
    }

}
