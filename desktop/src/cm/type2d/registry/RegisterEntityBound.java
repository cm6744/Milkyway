package cm.type2d.registry;

import cm.milkyway.lang.maths.shapes.Rect;

public class RegisterEntityBound extends Register<Rect[]>
{

    public void call()
    {
        register("entity_player", Rect.sized(30, 160), Rect.sized(80, 180));
        register("entity_item", Rect.sized(32, 24), Rect.sized(32, 32));
        register("entity_room_door", Rect.sized(100, 200), Rect.sized(100, 200));
    }

    public void register(String name, Rect b, Rect o)
    {
        super.register(name, new Rect[] {b, o});
    }

}
