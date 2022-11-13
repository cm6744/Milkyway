package cm.milkywaylib.buffers;

import cm.milkywaylib.base.RenderBuffer;

public class Particle extends RenderBuffer
{

    double rotate;
    double sizeCrease;
    double opaCrease;
    double sizeLimit;

    public void tickThen()
    {
        super.tickThen();

        effect.mvRotation(rotate);
        effect.mvOpacity(opaCrease);
        box4.mvSize(sizeCrease, sizeCrease);
    }

    public void opacity(double crease)
    {
        opaCrease = crease;
    }

    public void rotate(double rot)
    {
        rotate = rot;
    }

    public void size(double crease)
    {
        sizeCrease = crease;
    }

    public void sizeLimit(double lmt)
    {
        sizeLimit = lmt;
    }

}
