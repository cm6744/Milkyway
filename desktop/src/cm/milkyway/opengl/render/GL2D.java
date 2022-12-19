package cm.milkyway.opengl.render;

import cm.milkyway.opengl.render.g2d.Area;
import cm.milkyway.opengl.render.g2d.BufferTex;
import cm.milkyway.physics.shapes.Rect;

public interface GL2D extends Batch
{

    void setSmooth(boolean smt);

    void loadTexture(BufferTex merge, String path);

    double texw(BufferTex id);

    double texh(BufferTex id);

    void vertex(BufferTex buf, double x, double y, double x2, double y2, double u, double v, double u2, double v2);

    void dim(BufferTex buf, double x, double y, double w, double h, double u, double v, double uw, double vh);

    void dim(BufferTex buf, Rect rect, double u, double v, double uw, double vh);

    void dim(BufferTex buf, double x, double y, double w, double h);

    void dim(BufferTex buf, Rect rect);

    void dim(Area area, double x, double y, double w, double h);

    void dim(Area area, Rect rect);

}
