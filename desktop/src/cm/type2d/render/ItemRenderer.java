package cm.type2d.render;

import cm.milkyway.lang.text.Data;
import cm.milkyway.opengl.render.g2d.Tex;
import cm.milkyway.opengl.render.g2d.Color;
import cm.milkyway.opengl.render.g2d.Font;
import cm.milkyway.opengl.render.graphics.Graphics2D;
import cm.milkywayx.inventoryx.Stack;
import cm.type2d.item.ExtendedItem;
import cm.type2d.render.hud.Huds;

public class ItemRenderer
{

    public static ItemRenderer DEFAULT = new ItemRenderer();

    public void render(Graphics2D g, Stack stack, double x, double y, double w, double h, boolean num)
    {
        Font font = Huds.FONT_ALL;

        if(stack.isEmpty()) return;

        Tex tex = ((ExtendedItem) stack.item()).texture();
        g.draw(tex, x, y, w, h);
        if(num) {
            g.drawText(font, Color.C1111, Data.toString(stack.count()), x + w, y + h, true);
        }
    }

}
