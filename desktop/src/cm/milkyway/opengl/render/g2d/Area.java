package cm.milkyway.opengl.render.g2d;

import cm.milkyway.opengl.render.graphics.Graphics2D;

public interface Area
{

    Tex texture();

    //You can extend AreaStatic, override this method, to make your own render effect
    //Such as AreaAnimated
    void render(Graphics2D g, double x, double y, double w, double h);

    Area copy();

}
