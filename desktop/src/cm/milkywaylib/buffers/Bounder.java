package cm.milkywaylib.buffers;

import cm.milkywaygl.maths.check.Box4;
import cm.milkywaylib.base.RenderBuffer;

public class Bounder extends RenderBuffer
{

    protected Box4 bound = Box4.normalInset();

    public Box4 bound()
    {
        bound.loc(box4.x(), box4.y());
        return bound;
    }

}
