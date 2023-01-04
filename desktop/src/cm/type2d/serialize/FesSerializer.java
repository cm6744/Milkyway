package cm.type2d.serialize;

import cm.milkywayx.storfesx.CombinedFes;
import cm.type2d.room.Room;
import cm.type2d.world.FixedWorld;

public interface FesSerializer<E>
{

    void merge(CombinedFes fes, E obj);

    E take(CombinedFes fes, FixedWorld world, Room room);

}
