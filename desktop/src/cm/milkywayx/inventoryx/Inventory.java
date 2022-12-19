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
            stacks[i] = Stack.EMPTY.copy();
        }
    }

    public void set(int i, Stack s)
    {
        stacks[i] = s;
    }

    public Stack add(Stack s)
    {
        for(int i = 0; i < stacks.length; i++) {
            s = get(i).merge(s);
            if(s.isEmpty()) {
                break;
            }
        }
        return s;
    }

    public Stack get(int i)
    {
        Stack s = stacks[i];
        if(s == null) {
            return Stack.EMPTY;
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
            Platform.log((s.count() + " " + s.item().mame()));
        }
    }

}
