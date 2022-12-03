package cm.milkywaylib.buffers.particle;

import cm.milkywaylib.base.RenderBuffer;

public class Particle extends RenderBuffer
{

    double rotate;
    double alive;
    double easeIn;
    double easeOut;
    double zoom;

    public void tickThen()
    {
        super.tickThen();

        effect.mvRotation(rotate);
        if(time() < easeIn) {
            effect.setOpacity(time() / easeIn);
        }
        else if(time() > (alive - easeOut)) {
            effect.setOpacity((alive - time()) / easeOut);
        }
        renderBox.mvSize(zoom, zoom);

        if(time() > alive) {
            remove();
        }
    }

    public void rotation(double rot)
    {
        rotate = rot;
    }

    public void zoom(double zm)
    {
        zoom = zm;
    }

    public void aliveTime(double al)
    {
        alive = al;
    }

    public void easeIn(double in)
    {
        easeIn = in;
    }

    public void easeOut(double out)
    {
        easeOut = out;
    }

}
