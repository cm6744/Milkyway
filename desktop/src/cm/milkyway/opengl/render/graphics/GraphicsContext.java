package cm.milkyway.opengl.render.graphics;

import cm.milkyway.lang.maths.shapes.Flat;
import cm.milkyway.opengl.render.Window;

public interface GraphicsContext
{

    Window getWindow();

    double width();

    double height();

    void clear();

    void paint();

    void viewport(double x, double y, double w, double h);

    Flat stretchDimension();

}
