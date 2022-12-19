package cm.type2d.hud;

import cm.milkyway.Milkyway;
import cm.milkyway.opengl.input.InputMap;
import cm.milkyway.opengl.input.Key;
import cm.milkywayx.inventoryx.Inventory;
import cm.milkywayx.inventoryx.Stack;
import cm.type2d.item.RenderableItem;

public class InvRenderer
{

    Inventory inv;
    ItemRenderer itemRenderer = new ItemRenderer();

    public InvRenderer(Inventory i)
    {
        inv = i;
    }

    public void tick()
    {
    }

    public void render(double x, double y, double slotW, double slotH, double space)
    {
        double xn = x;
        for(int i = 0; i < inv.size(); i++) {
            Stack s = inv.get(i);
            if(!s.isEmpty()) {
                itemRenderer.render(s, xn, y, slotW, slotH);
            }
            xn += slotW;
            xn += space;
        }
    }

}
