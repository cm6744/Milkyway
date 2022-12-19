package cm.milkyway.opengl.render.g2d;

public interface Graphics
{

    BufferTex newTex();

    Color4 newColor(double r, double g, double b, double a);

    Color4 newColor(double r, double g, double b);

    FontType newType(String ttf, String desc);

}
