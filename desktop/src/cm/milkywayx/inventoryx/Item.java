package cm.milkywayx.inventoryx;

public class Item
{

    int maxStack;
    String name;

    public Item(int max, String nam)
    {
        maxStack = max;
        name = nam;
    }

    public int maxStack()
    {
        return maxStack;
    }

    public String name()
    {
        return name;
    }

    public boolean canPutIn(Inventory inv, Stack stack, int slot)
    {
        return true;
    }

    public Stack aStack(int c)
    {
        return new Stack(this, c);
    }

    public Stack aStack()
    {
        return aStack(1);
    }

}
