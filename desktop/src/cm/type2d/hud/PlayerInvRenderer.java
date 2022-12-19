package cm.type2d.hud;

import cm.milkyway.Milkyway;
import cm.milkyway.opengl.input.InputMap;
import cm.milkyway.opengl.input.Key;
import cm.milkyway.physics.shapes.Rect;
import cm.milkywayx.inventoryx.Inventory;
import cm.milkywayx.inventoryx.Stack;
import cm.type2d.item.PlayerInv;

public class PlayerInvRenderer extends InvRenderer
{

    Stack hold = Stack.EMPTY;
    Rect[] slotBounds;
    Rect chosen;

    public PlayerInvRenderer(Inventory i)
    {
        super(i);
        slotBounds = new Rect[i.size()];
    }

    public void tick()
    {
        double mx = InputMap.x();
        double my = InputMap.y();
        Key l = Milkyway.keys.mouse("left");
        Key r = Milkyway.keys.mouse("right");
        int slot = -1;
        Huds h = Huds.GLOBAL;

        for(int i = 0; i < slotBounds.length; i++) {
            if(slotBounds[i] == null) {
                slotBounds[i] = Rect.offset(h.pInvLeft + h.pInvSpaceOrg + (h.pInvSize + h.pInvSpace) * i,
                                            h.pInvTop + h.pInvSpaceOrg, h.pInvSize, h.pInvSize);
            }
            if(slotBounds[i].contains(mx, my)) {
                slot = i;
                break;
            }
        }

        if(slot == -1) {
            chosen = null;
            return;
        }
        chosen = slotBounds[slot];

        if(InputMap.isClick(l)) {
            hold = ((PlayerInv) inv).onClick(slot, hold, l);
        }
        else if(InputMap.isClick(r)) {
            hold = ((PlayerInv) inv).onClick(slot, hold, r);
        }
    }

    public void render(double x, double y, double slotW, double slotH, double space)
    {
        super.render(x, y, slotW, slotH, space);

        if(chosen != null) {
            Milkyway.glBase.state().color(1, 1, 1, 0.5);
            Milkyway.glShape.dim(chosen);
        }

        if(!hold.isEmpty()) {
            double sz = Huds.GLOBAL.pInvSize;
            itemRenderer.render(hold, InputMap.x() - sz / 2, InputMap.y() - sz / 2, sz, sz);
        }
    }

}
