package cm.milkywayx.inventoryx;

public class Stack
{

    Item item;
    int count;

    public static Stack createEmpty()
    {
        return new Stack(null);
    }

    public Stack(Item i)
    {
        item = i;
    }

    public Stack(Item i, int c)
    {
        item = i;
        count = c;
    }

    public boolean isEmpty()
    {
        return item == null || count <= 0;
    }

    public Item item()
    {
        return isEmpty() ? null : item;
    }

    public int count()
    {
        return count;
    }

    public void set(int c)
    {
        count = c;
    }

    public void trs(int i)
    {
        count += i;
    }

    public Stack split(int c)
    {
        if(count < c) {
            Stack s = copy(count);
            set(0);
            return s;
        }
        trs(-c);
        return copy(c);
    }

    public Stack merge(Stack s)
    {
        if(isEmpty()) {
            item = s.item();
            set(s.count());
            return createEmpty();
        }
        if(s.item() != item) {
            return s;
        }
        Stack s2 = s.split(item().maxStack() - count);
        trs(s2.count());
        return s;
    }

    public Stack copy()
    {
        return new Stack(item, count);
    }

    public Stack copy(int c)
    {
        return new Stack(item, c);
    }

}
