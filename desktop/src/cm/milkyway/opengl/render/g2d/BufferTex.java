package cm.milkyway.opengl.render.g2d;

import cm.milkyway.Milkyway;

public interface BufferTex
{

    static BufferTex directLoad(String pth)
    {
        BufferTex buf = Milkyway.graphics.newTex();
        Milkyway.gl2d.loadTexture(buf, pth);
        return buf;
    }

    double w();

    double h();

}
