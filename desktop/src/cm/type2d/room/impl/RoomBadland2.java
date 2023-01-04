package cm.type2d.room.impl;

import cm.type2d.Load;
import cm.type2d.registry.Registry;
import cm.type2d.room.Room;
import cm.type2d.room.RoomDoor;
import cm.type2d.world.FixedWorld;

public class RoomBadland2 extends Room
{

    public RoomBadland2()
    {
        super("badland2");
        set(null,
            Load.loader.getTex("room1_2"),
            Load.loader.getTex("room1_3"));
    }

    public void onWorldStart(FixedWorld world)
    {
        dim(-500, 440, 2800, 300);
        Room next = Registry.ROOM.get("badland1");
        new RoomDoor(world, this, 1000, 400, false, next, 20, 440).spawn();
        new RoomDoor(world, this, -200, 400, false, next, 780, 440).spawn();
        new RoomDoor(world, this, 550, 350, true, next, 550, 410).spawn();
    }

}
