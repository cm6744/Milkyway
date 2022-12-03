package cm.milkywaygl.render;

import cm.milkywaytool.physics.Rect;

public interface GLShape extends Batch
{

    void vertex(double x, double y, double x2, double y2);

    void dim(double x, double y, double w, double h);

    void dim(Rect rect);

    void line(double x1, double y1, double x2, double y2);

}
