package cm.type2d.hud;

import cm.milkyway.Milkyway;
import cm.milkyway.lang.text.Data;
import cm.milkyway.opengl.render.g2d.BufferTex;
import cm.milkyway.opengl.render.g2d.Font2;
import cm.milkywayx.inventoryx.Stack;
import cm.type2d.item.RenderableItem;

public class ItemRenderer
{

    public void render(Stack stack, double x, double y, double w, double h)
    {
        Font2 font = Huds.FONT_ALL;

        BufferTex tex = ((RenderableItem) stack.item()).texture();
        Milkyway.gl2d.dim(tex, x, y, w, h);
        Milkyway.glBase.state().font(font);
        Milkyway.glText.text(Data.toString(stack.count()), x + w, y + h, true);
    }

}
