package cm.type2d.item;

import cm.milkyway.opengl.render.g2d.Tex;
import cm.milkywayx.inventoryx.Item;
import cm.milkywayx.inventoryx.Stack;
import cm.type2d.world.entity.Entity;

public class ExtendedItem extends Item
{

    public enum UsingType
    {
        HELMET,
        CLOTHES,
        TOOL,
        RIGHT_CLICK_USE
    }

    Tex tex;

    public ExtendedItem(int max, String nam, Tex t)
    {
        super(max, nam);
        tex = t;
    }

    public UsingType usingType()
    {
        return UsingType.RIGHT_CLICK_USE;
    }

    //in correct slot, like armor
    public void onActiveTick(PickableInv inv, Stack stack, Entity entity) {}

    public void inInvTick(PickableInv inv, Stack stack, Entity entity) {}

    public void onHoldStart(PickableInv inv, Stack stack, Entity entity) {}

    public void onHoldEnd(PickableInv inv, Stack stack, Entity entity) {}

    public void onUseClick(PickableInv inv, Stack stack, Entity entity) {}

    public Tex texture()
    {
        return tex;
    }

}
