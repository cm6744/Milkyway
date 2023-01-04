package cm.milkywayx.inventoryx;

import cm.milkyway.lang.Platform;

public class Inventory
{

    Stack[] stacks;
    int size;

    public Inventory(int siz)
    {
        size = siz;
        stacks = new Stack[siz];
        for(int i = 0; i < stacks.length; i++) {
            stacks[i] = Stack.createEmpty();
        }
    }

    public void set(int i, Stack s)
    {
        stacks[i] = s;
    }

    public Stack add(Stack s)
    {
        return add(s, 0, size);
    }

    public Stack add(Stack s, int startSlot, int endSlot)
    {
        for(int i = startSlot; i < endSlot; i++) {
            if(!canPut(s, i)) {
                continue;
            }
            s = get(i).merge(s);
            if(s.isEmpty()) {
                break;
            }
        }
        return s;
    }

    public boolean canPut(Stack s, int slot)
    {
        if(s.isEmpty()) {
            return true;
        }
        return s.item().canPutIn(this, s, slot);
    }

    public Stack get(int i)
    {
        Stack s = stacks[i];
        if(s == null) {
            return Stack.createEmpty();
        }
        return s;
    }

    public int size()
    {
        return stacks.length;
    }

    public void printAll()
    {
        for(int i = 0; i < stacks.length; i++) {
            Stack s = get(i);
            Platform.log((s.count() + " " + s.item().name()));
        }
    }

}
