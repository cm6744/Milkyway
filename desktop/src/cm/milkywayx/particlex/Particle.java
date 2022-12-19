package cm.milkywayx.particlex;

import cm.milkywayx.lightx.Light;
import cm.milkywayx.widgetx.base.RenderBuffer;

public class Particle extends RenderBuffer
{

    double rotate;
    double alive;
    double easeIn;
    double easeOut;
    double zoom;
    Light light;
    double lightOriginInt;

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

        if(light != null) {
            light.pos().copy(renderBox);
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

    public void holdLight(Light l)
    {
        light = l;
        lightOriginInt = light.intensity();
    }

    public void disposeLight()
    {
        if(light != null) {
            light.remove();
        }
    }

}
