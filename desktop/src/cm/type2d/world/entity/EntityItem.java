package cm.type2d.world.entity;

import cm.milkyway.opengl.render.graphics.Graphics2D;
import cm.milkywayx.inventoryx.Stack;
import cm.milkywayx.storfesx.CombinedFes;
import cm.type2d.item.StackSerializer;
import cm.type2d.render.ItemRenderer;
import cm.type2d.room.Room;
import cm.type2d.serialize.FesSerializer;
import cm.type2d.world.FixedWorld;
import cm.type2d.world.entity.serialize.CommonSerializer;

public class EntityItem extends EntityHasGravity
{

    ItemRenderer itemRenderer = ItemRenderer.DEFAULT;
    public Stack stack;

    public EntityItem(FixedWorld w, Room r, Stack s, double x, double y)
    {
        super(w, r, x, y);
        stack = s;
    }

    public String name()
    {
        return "entity_item";
    }

    public ActionResult interact(Player player)
    {
        player.play(1);
        Stack rt = player.inv.add(stack);
        if(rt.isEmpty()) {
            kill();
        }
        else {
            stack = rt.copy();
        }
        return ActionResult.SUCCESS;
    }

    public void render(Graphics2D g, double x, double y, double w, double h)
    {
        itemRenderer.render(g, stack, x, y, w, h, false);
    }

    public FesSerializer<EntityItem> getSerializer()
    {
        return new ItemSerializer();
    }

    public static class ItemSerializer extends CommonSerializer<EntityItem>
    {

        public void merge(CombinedFes fes, EntityItem obj)
        {
            super.merge(fes, obj);
            fes.put("stack", StackSerializer.toFes(obj.stack));
            fes.putDouble("x", obj.bound().x());
            fes.putDouble("y", obj.bound().y());
        }

        public EntityItem take(CombinedFes fes, FixedWorld world, Room room)
        {
            return new EntityItem(world, room,
                                  StackSerializer.fromFes(fes.getChild("stack")),
                                  fes.getDouble("x"),
                                  fes.getDouble("y")
            );
        }
    }

}
