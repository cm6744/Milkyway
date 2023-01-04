package cm.type2d.registry;

import cm.type2d.room.Room;
import cm.type2d.room.impl.RoomBadland1;
import cm.type2d.room.impl.RoomBadland2;

public class RegisterRoom extends Register<Room>
{

    public void call()
    {
        register("badland1", new RoomBadland1());
        register("badland2", new RoomBadland2());
    }

    public void register(String name, Room o)
    {
        super.register(name, o);
        o.name = name;
    }

}
