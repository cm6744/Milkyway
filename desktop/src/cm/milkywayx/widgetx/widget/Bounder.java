package cm.milkywayx.widgetx.widget;

import cm.milkywayx.widgetx.base.RenderBuffer;
import cm.milkyway.physics.shapes.Shape;

public abstract class Bounder<TYPE extends Shape> extends RenderBuffer
{

    protected TYPE bound;
    protected boolean allowBoundRotate;

    public Bounder()
    {
        initBound();
    }

    protected abstract void initBound();

    public TYPE bound()
    {
        bound.loc(renderBox.x(), renderBox.y());
        if(allowBoundRotate) {
            bound.setRotation(vecInfo.degree());
        }
        else {
            bound.setRotation(0);
        }
        return bound;
    }

}
