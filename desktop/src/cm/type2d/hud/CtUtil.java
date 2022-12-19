package cm.type2d.hud;

import cm.milkyway.Milkyway;
import cm.milkyway.opengl.render.g2d.BufferTex;

public class CtUtil
{

    public static void ct(BufferTex tex, double x, double y, double w, double h, double u, double v, double uw, double vh)
    {
        Milkyway.gl2d.dim(tex, x - w / 2, y - h / 2, w, h, u, v, uw, vh);
    }

}
