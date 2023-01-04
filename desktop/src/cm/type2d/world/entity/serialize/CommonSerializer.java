package cm.type2d.world.entity.serialize;

import cm.milkywayx.storfesx.CombinedFes;
import cm.type2d.room.Room;
import cm.type2d.serialize.FesSerializer;
import cm.type2d.world.FixedWorld;
import cm.type2d.world.entity.Entity;

public abstract class CommonSerializer<E extends Entity> implements FesSerializer<E>
{

    public void merge(CombinedFes fes, E obj)
    {
        fes.putString("room", obj.room.name);
        fes.putString("type", obj.name());
    }

}
