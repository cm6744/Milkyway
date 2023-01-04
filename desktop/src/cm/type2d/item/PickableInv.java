package cm.type2d.item;

import cm.backends.lwjgl.LwjglKey;
import cm.milkyway.lang.maths.Mth;
import cm.milkyway.opengl.input.InputMap;
import cm.milkyway.opengl.input.Key;
import cm.milkywayx.inventoryx.Inventory;
import cm.milkywayx.inventoryx.Stack;
import cm.type2d.world.entity.Entity;
import cm.type2d.world.entity.Player;

public class PickableInv extends Inventory
{

    public Stack hold = Stack.createEmpty();
    public Stack lastHold = Stack.createEmpty();
    public Stack hang = Stack.createEmpty();
    public Entity owner;

    public PickableInv(int siz, Entity o)
    {
        super(siz);
        owner = o;
    }

    public void onClickOutside(Key mouse, double mx, double my)
    {
        if(owner instanceof Player) {
            ((Player) owner).handle().dropHelp(hold, (Player) owner, true);
        }
    }

    public void tick()
    {
        for(int i = 0; i < size(); i++) {
            Stack s = get(i);
            if(s.isEmpty()) continue;
            ((ExtendedItem) s.item()).inInvTick(this, s, owner);
        }
    }

    //from:renderer
    public void onClick(int slot, Key mouse)
    {
        lastHold = hold;
        Stack slf = get(slot);
        if(mouse == LwjglKey.key("left")) {
            //shift: add
            if(InputMap.isOn(LwjglKey.key("shift"))) {
                set(slot, add(slf));
            }
            else if(InputMap.isOn(LwjglKey.key("ctrl"))) {
                //holding control: split one or spilt half
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
                    hold = s1;
                }
                else if(canPut(hold, slot)) {
                    if(slf.isEmpty()) {
                        set(slot, hold.copy(1));
                        hold = hold.copy(hold.count() - 1);
                    }
                    else if(slf.item() == hold.item() && slf.count() < slf.item().maxStack()) {
                        slf.trs(1);
                        hold = hold.copy(hold.count() - 1);
                    }
                }

            }
            else {
                //only left click: hold or exchange
                if(hold.isEmpty()) {
                    Stack s1 = slf.copy();
                    slf.set(0);
                    hold = s1;
                }
                else if(canPut(hold, slot)) {
                    if(slf.item() == hold.item()) {
                        int rem = slf.item().maxStack() - slf.count();
                        int actual = (int) Mth.min(rem, hold.count());
                        slf.trs(actual);
                        hold = hold.copy(hold.count() - actual);
                    }
                    else {
                        set(slot, hold);
                        hold = slf;
                    }
                }
            }
        }
        else if(mouse == LwjglKey.mouse("right")) {
            //right click: use or put into armor slots
            if(!slf.isEmpty()) {
                ExtendedItem ei = ((ExtendedItem) slf.item());
                ei.onUseClick(this, slf, owner);
                if(ei.usingType() == ExtendedItem.UsingType.TOOL) {
                    transferArmor(slot, 9, slf);
                }
                if(ei.usingType() == ExtendedItem.UsingType.CLOTHES) {
                    transferArmor(slot, 10, slf);
                }
                if(ei.usingType() == ExtendedItem.UsingType.HELMET) {
                    transferArmor(slot, 11, slf);
                }
                if(slot == 9 || slot == 10 || slot == 11){
                    add(slf.copy());
                    slf.set(0);
                }
            }
        }
    }

    public boolean canPut(Stack s, int slot)
    {
        if(s.isEmpty()) {
            return true;
        }
        ExtendedItem ei = ((ExtendedItem) s.item());
        if(slot == 9) return ei.usingType() == ExtendedItem.UsingType.TOOL;
        if(slot == 10) return ei.usingType() == ExtendedItem.UsingType.CLOTHES;
        if(slot == 11) return ei.usingType() == ExtendedItem.UsingType.HELMET;

        return super.canPut(s, slot);
    }

    private boolean transferArmor(int from, int to, Stack putin)
    {
        ExtendedItem ei = ((ExtendedItem) putin.item());
        if(canPut(putin, to)) {
            Stack out = get(to);
            if(!out.isEmpty()) {
                ((ExtendedItem) out.item()).onHoldEnd(this, out, owner);
            }
            ei.onHoldStart(this, putin, owner);
            set(from, out.copy());
            out.set(0);
            set(to, putin.copy());
            putin.set(0);
            return true;
        }
        return false;
    }

}
