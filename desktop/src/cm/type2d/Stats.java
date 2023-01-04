package cm.type2d;

import cm.milkyway.opengl.render.g2d.AreaAnimated;
import cm.milkywayx.lightx.EnvironmentalLight;
import cm.milkywayx.lightx.LightMap;
import cm.type2d.room.Room;
import cm.type2d.world.entity.Player;
import cm.type2d.render.hud.Huds;
import cm.type2d.registry.Registry;
import cm.type2d.serialize.FixedWorldSave;
import cm.type2d.world.FixedWorld;

//Shouldn't be referred most time except StatsTicker
public class Stats
{

    public FixedWorld world;
    public Huds huds;

    public void createNewWorld()
    {
        doRegistry();
        world = new FixedWorld();
        Room def = Registry.ROOM.get("badland1");
        world.player = new Player(world, def,
                            400, 200,
                            Load.loader.getTex("chr1_walk"),
                            Load.loader.getTex("chr1_stay"),
                            new AreaAnimated[] {
                                    new AreaAnimated(Load.loader.getTex("chr1_r1"), 4, 1, 0).perTime(5),
                                    new AreaAnimated(Load.loader.getTex("chr1_r2"), 4, 1, 0).perTime(8)
                            }
        );
        world.player.spawn();
        world.player.inv.add(Registry.ITEM.get("bronze").aStack(24));

        createEffect();

        world.setRoom(def);

        Registry.ROOM.map().iterate((o, i) -> {
            o.value().pushWorld(world);
            o.value().onWorldStart(world);
            o.value().onWorldGenerate(world);
        }, false);
    }

    public void loadWorld()
    {
        doRegistry();

        FixedWorldSave save = new FixedWorldSave();
        world = new FixedWorld();
        save.read(world);

        createEffect();

        Registry.ROOM.map().iterate((o, i) -> {
            o.value().pushWorld(world);
            o.value().onWorldStart(world);
        }, false);

        world.setRoom(world.player.room);
    }

    private void doRegistry()
    {
        Registry.ITEM.call();
        Registry.ROOM.call();
        Registry.ENTITY_SERIALIZER.call();
        Registry.ENTITY_BOUND.call();
        Registry.ENTITY_ATTR.call();
    }

    private void createEffect()
    {
        world.lightMap = new LightMap(Load.loader.getTex("shade"), 6, 6);
        world.globalEnv = new EnvironmentalLight();
        world.globalEnv.setIntensity(1);
        world.lightMap.add(world.globalEnv);
        huds = new Huds(Load.loader.getTex("hud"), world.player);
    }

    public void saveWorld()
    {
        FixedWorldSave save = new FixedWorldSave();
        save.save(world);
    }

}
