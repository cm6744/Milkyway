package cm.type2d.item;

import cm.milkyway.lang.container.Map;
import cm.milkyway.opengl.render.g2d.BufferTex;
import cm.milkywayx.inventoryx.Item;
import cm.type2d.res.Load;

public class RegisterItem
{

    static Map<String, Item> itemMap = new Map<>();

    static {
        register("bronze", 36);
        register("electrum", 48);
    }

    public static void register(String name, Item item)
    {
        itemMap.put(name, item);
    }

    public static void register(String name, int stack)
    {
        itemMap.put(name, new RenderableItem(stack, name, Load.loader.getTex(name)));
    }

    public static Item get(String name)
    {
        return itemMap.get(name);
    }

}
