package cm.milkywayx.widgetx.widget.choice;

import cm.milkywayx.widgetx.widget.Bounder;
import cm.milkyway.lang.maths.shapes.Rect;

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
