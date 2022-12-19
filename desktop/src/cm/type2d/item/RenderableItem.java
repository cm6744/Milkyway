package cm.type2d.item;

import cm.milkyway.opengl.render.g2d.BufferTex;
import cm.milkywayx.inventoryx.Item;

public class RenderableItem extends Item
{

    BufferTex tex;

    public RenderableItem(int max, String nam, BufferTex t)
    {
        super(max, nam);
        tex = t;
    }

    public BufferTex texture()
    {
        return tex;
    }

}
