package cm.milkyway.opengl.render;

public interface GLText
{

    void cut(String text, double x, double y, double start, double end, boolean center);

    void text(String text, double x, double y, boolean center);

}
