package cm.milkyway.opengl.render;

public interface GLBase
{

    double width();

    double height();

    double calcMX(double x);

    double calcMY(double y);

    void updateFrameData();

    void clear();

    void viewPort();

    void translation(double x, double y);

    void viewPortBack();

    //when your render method end, invoke this.
    void freeAll();

    Batch curBlocking();

    //ensure the given batch is begun.
    //if given batch is null, it will not begin a new batch.
    void ensure(Batch batch);

    void begin(Batch batch);

    void end(Batch batch);

    void save();

    void read();

    State2D state();

}
