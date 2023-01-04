package cm.type2d.item;

import cm.milkywayx.inventoryx.Item;
import cm.milkywayx.inventoryx.Stack;
import cm.milkywayx.storfesx.CombinedFes;
import cm.type2d.registry.Register;
import cm.type2d.registry.Registry;

public class StackSerializer
{

    public static CombinedFes toFes(Stack stack)
    {
        CombinedFes fes = new CombinedFes();
        if(stack.isEmpty()) {
            fes.putBoolean("is_empty", true);
            return fes;
        }
        fes.putBoolean("is_empty", false);
        fes.putInt("stack_count", stack.count());
        fes.putString("stack_type", stack.item().name());
        return fes;
    }

    public static Stack fromFes(CombinedFes fes)
    {
        if(fes.getBoolean("is_empty")) {
            return Stack.createEmpty();
        }
        int c;
        Item i;
        c = fes.getInt("stack_count");
        i = Registry.ITEM.get(fes.getString("stack_type"));
        return i.aStack(c);
    }

}
