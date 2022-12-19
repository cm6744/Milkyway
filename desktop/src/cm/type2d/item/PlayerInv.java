package cm.type2d.item;

import cm.milkyway.Milkyway;
import cm.milkyway.lang.maths.Mth;
import cm.milkyway.opengl.input.Key;
import cm.milkywayx.inventoryx.Inventory;
import cm.milkywayx.inventoryx.Stack;

public class PlayerInv extends Inventory
{

    public static PlayerInv GLOBAL = new PlayerInv(12);

    public PlayerInv(int siz)
    {
        super(siz);
    }

    //from:renderer
    public Stack onClick(int slot, Stack hold, Key mouse)
    {
        Stack slf = get(slot);
        if(mouse == Milkyway.keys.mouse("left")) {
            if(hold.isEmpty()) {
                Stack s1 = slf.copy();
                slf.set(0);
                return s1;
            }
            else {
                if(slf.item() == hold.item()) {
                    int rem = slf.item().maxStack() - slf.count();
                    int actual = (int) Mth.min(rem, hold.count());
                    slf.trs(actual);
                    return hold.copy(hold.count() - actual);
                }
                else {
                    set(slot, hold);
                    return slf;
                }
            }
        }
        else if(mouse == Milkyway.keys.mouse("right")) {
            if(hold.isEmpty()) {
                Stack s1;
                if(slf.count() % 2 == 0) {
                    s1 = slf.copy(slf.count() / 2);
                    slf.set(slf.count() / 2);
                }
                else {
                    s1 = slf.copy((slf.count() + 1) / 2);
                    slf.set((slf.count() - 1) / 2);
                }
                return s1;
            }
            else {
                if(slf.isEmpty()) {
                    set(slot, hold.copy(1));
                    return hold.copy(hold.count() - 1);
                }
                else if(slf.item() == hold.item() && slf.count() < slf.item().maxStack()) {
                    slf.trs(1);
                    return hold.copy(hold.count() - 1);
                }
                return hold;
            }
        }
        return Stack.EMPTY;
    }

}
