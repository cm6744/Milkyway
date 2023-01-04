package cm.type2d.room.impl;

import cm.milkywayx.lightx.Light;
import cm.milkywayx.lightx.SpotLight;
import cm.type2d.Load;
import cm.type2d.registry.Registry;
import cm.type2d.room.Room;
import cm.type2d.room.RoomDoor;
import cm.type2d.world.FixedWorld;

public class RoomBadland1 extends Room
{

    Light l1 = new SpotLight(), l2 = new SpotLight();

    public RoomBadland1()
    {
        super("badland1");
        set(null,
            Load.loader.getTex("room1_2"),
            Load.loader.getTex("room1_3"));
    }

    public void onWorldStart(FixedWorld world)
    {
        l1.setIntensity(270);
        l2.setIntensity(200);
        l1.pos().loc(384, 99);
        l2.pos().loc(506, 63);
        dim(800, 240, 600, 100);
        dim(400, 540, 800, 200);
        Room next = Registry.ROOM.get("badland2");
        new RoomDoor(world, this, 1000, 400, false, next, 20, 440).spawn();
        new RoomDoor(world, this, -200, 400, false, next, 780, 440).spawn();
        new RoomDoor(world, this, 550, 350, true, next, 550, 410).spawn();
    }

    public void tickThen()
    {
    }

    public void onLoadIntoWorld(FixedWorld world)
    {
        world.lightMap.add(l1);
        world.lightMap.add(l2);
    }

    public void onRemoveOutOfWorld(FixedWorld world)
    {
        world.lightMap.remove(l1);
        world.lightMap.remove(l2);
    }

}
