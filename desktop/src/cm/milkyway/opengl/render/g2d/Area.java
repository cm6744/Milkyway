package cm.milkyway.opengl.render.g2d;

public interface Area
{

    BufferTex texture();

    double fw();

    double fh();

    double w();

    double h();

    //You can extend AreaStatic, override this method, to make your own render effect
    //Such as AreaAnimated
    void render(double x1, double y1, double w, double h);

    Area copy();

}
