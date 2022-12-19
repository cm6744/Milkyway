package cm.milkyway.opengl.render.g2d;

import cm.milkyway.Milkyway;

public interface Color4
{

    Color4 C1111 = Milkyway.graphics.newColor(1, 1, 1, 1);
    Color4 C0001 = Milkyway.graphics.newColor(0, 0, 0, 1);
    Color4 C0000 = Milkyway.graphics.newColor(0, 0, 0, 0);

    Color4 opacity(double a);

    double red();

    double blue();

    double green();

    double alpha();

}
