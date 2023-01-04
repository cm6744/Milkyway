package cm.type2d.render;

import cm.milkyway.opengl.render.graphics.Graphics2D;
import cm.milkywayx.inventoryx.Inventory;
import cm.milkywayx.inventoryx.Stack;

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

    public void render(Graphics2D g, double x, double y, double slotW, double slotH, double space)
    {
        double xn = x;
        for(int i = 0; i < inv.size(); i++) {
            Stack s = inv.get(i);
            if(!s.isEmpty()) {
                itemRenderer.render(g, s, xn, y, slotW, slotH, true);
            }
            xn += slotW;
            xn += space;
        }
    }

}
