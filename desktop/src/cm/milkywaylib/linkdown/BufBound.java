package cm.milkywaylib.linkdown;

import cm.milkywaygl.maths.check.Box4;
import cm.milkywaylib.linklib.RenderBuffer;

public class BufBound extends RenderBuffer
{

    protected Box4 bound = Box4.normal();

    public Box4 bound()
    {
        bound.loc(box4.x(), box4.y());
        return bound;
    }

}
