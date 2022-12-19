package cm.milkyway.opengl.render.g3d;

/** May not be implemented in some backends! */
public interface Camera
{

    void sight(double near, double far, double fogDist);

    void lookPos(double x, double y, double z);

    void lookPosTranslate(double x, double y, double z);

    void pos(double x, double y, double z);

    void posTranslate(double x, double y, double z);

}
