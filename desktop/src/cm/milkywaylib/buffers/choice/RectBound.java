package cm.milkywaylib.buffers.choice;

import cm.milkywaylib.buffers.Bounder;
import cm.milkywaytool.physics.Rect;

public class RectBound extends Bounder<Rect>
{

    public RectBound(boolean canRotate)
    {
        allowBoundRotate = canRotate;
    }

    protected void initBound()
    {
        bound = new Rect();
    }

}
