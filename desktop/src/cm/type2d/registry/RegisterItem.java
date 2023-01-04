package cm.type2d.registry;

import cm.milkywayx.inventoryx.Item;
import cm.type2d.item.ExtendedItem;
import cm.type2d.Load;

public class RegisterItem extends Register<Item>
{

    public void call()
    {
        register("bronze", 36);
        register("electrum", 48);
    }

    public void register(String name, int stack)
    {
        register(name, new ExtendedItem(stack, name, Load.loader.getTex(name)));
    }

}
