package cm.type2d.serialize;

import cm.milkyway.lang.Platform;
import cm.milkyway.lang.container.list.List;
import cm.milkyway.lang.container.map.Node;
import cm.milkyway.lang.io.AccessLocal;
import cm.milkywayx.storfesx.CombinedFes;
import cm.milkywayx.storfesx.multi.MultiCombinedFes;
import cm.type2d.registry.Registry;
import cm.type2d.room.Room;
import cm.type2d.world.FixedWorld;
import cm.type2d.world.entity.Entity;
import cm.type2d.world.entity.Player;

public class FixedWorldSave
{

    public void save(FixedWorld world)
    {
        CombinedFes buffer = new CombinedFes();

        //save interactable objects
        MultiCombinedFes entities = new MultiCombinedFes();
        List<Node<String, Room>> rooms = Registry.ROOM.map().toList();
        for(int r = 0; r < rooms.size(); r++) {
            Room room = rooms.get(r).value();
            for(int i = 0; i < room.entities.size(); i++) {
                CombinedFes entity = new CombinedFes();
                Entity inter = room.entities.get(i);
                if(!inter.needToSave()) {
                    continue;
                }
                FesSerializer<Entity> ser = (FesSerializer<Entity>) inter.getSerializer();
                if(ser == null) {
                    Platform.log("An entity : " + inter.getClass() + ", its serializer is null!");
                    continue;
                }
                ser.merge(entity, inter);
                entities.add(entity);
            }
        }
        buffer.put("entities", entities);
//
        //buffer.write(new AccessLocal("save.data"));
    }

    public void read(FixedWorld world)
    {
        CombinedFes buffer = null;//CombinedFes.read(new AccessLocal("save.data"));

        //read entities
        MultiCombinedFes entities = (MultiCombinedFes) buffer.get("entities");
        for(int i = 0; i < entities.size(); i++) {
            CombinedFes entity = entities.get(i);
            FesSerializer<? extends Entity> ser = Registry.ENTITY_SERIALIZER.get(entity.getString("type"));
            String pos = entity.getString("room");
            Entity inter = ser.take(entity, world, Registry.ROOM.get(pos));
            inter.spawn();

            if(inter instanceof Player) {
                world.player = (Player) inter;
                world.curRoom = inter.room;
            }
        }

    }

}
