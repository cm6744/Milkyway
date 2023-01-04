package cm.type2d.registry;

import cm.type2d.world.entity.Entity;
import cm.type2d.world.entity.EntityItem;
import cm.type2d.serialize.FesSerializer;
import cm.type2d.world.entity.Player;
import cm.type2d.world.entity.serialize.CommonSerializer;

public class RegisterEntitySerializer extends Register<FesSerializer<? extends Entity>>
{

    public void call()
    {
        register("entity_player", new Player.PlayerSerializer());
        register("entity_item", new EntityItem.ItemSerializer());
    }

}
