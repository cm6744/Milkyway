package cm.type2d.render;

import cm.backends.lwjgl.LwjglKey;
import cm.milkyway.opengl.input.InputMap;
import cm.milkyway.opengl.input.Key;
import cm.milkyway.lang.maths.shapes.Rect;
import cm.milkyway.opengl.render.g2d.Color;
import cm.milkyway.opengl.render.graphics.Graphics2D;
import cm.milkywayx.inventoryx.Stack;
import cm.type2d.item.PickableInv;
import cm.type2d.render.hud.Huds;

public class PickableInvRenderer extends InvRenderer
{

    Rect[] slotBounds;
    Rect chosen;
    PickableInv inv;

    public PickableInvRenderer(PickableInv i)
    {
        super(i);
        inv = i;
        slotBounds = new Rect[i.size()];
    }

    public void tick()
    {
        double mx = InputMap.x();
        double my = InputMap.y();
        Key l = LwjglKey.mouse("left");
        Key r = LwjglKey.mouse("right");
        int slot = -1;
        Huds h = Huds.GLOBAL;

        for(int i = 0; i < slotBounds.length; i++) {
            if(slotBounds[i] == null) {
                slotBounds[i] = Rect.offset(h.pInvLeft + h.pInvSpaceOrg + (h.pInvSize + h.pInvSpace) * i,
                                            h.pInvTop + h.pInvSpaceOrg, h.pInvSize, h.pInvSize);
            }
            slotBounds[i].mvSize(8, 8);
            //avoid: clicking the chinks between slots and drop item by mistakes
            if(slotBounds[i].contains(mx, my)) {
                slot = i;
            }
            slotBounds[i].mvSize(-8, -8);
        }

        if(slot == -1) {
            chosen = null;
            if(!inv.hang.isEmpty()) {
                inv.hang = Stack.createEmpty();
            }
        }
        else {
            chosen = slotBounds[slot];
            inv.hang = inv.get(slot);
        }

        if(InputMap.isClick(l)) {
            if(chosen != null) {
                (inv).onClick(slot, l);
            }
            else {
                (inv).onClickOutside(l, mx, my);
            }
        }
        else if(InputMap.isClick(r)) {
            if(chosen != null) {
                (inv).onClick(slot, r);
            }
            else {
                (inv).onClickOutside(r, mx, my);
            }
        }
    }

    public void render(Graphics2D g, double x, double y, double slotW, double slotH, double space)
    {
        super.render(g, x, y, slotW, slotH, space);

        if(chosen != null) {
            g.drawRect(new Color(1, 1, 1, 0.5), chosen);
        }

        if(!inv.hold.isEmpty()) {
            double sz = Huds.GLOBAL.pInvSize;
            itemRenderer.render(g, inv.hold, InputMap.x() - sz / 2, InputMap.y() - sz / 2, sz, sz, true);
        }
    }

}
