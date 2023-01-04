package cm.milkyway.opengl.render.g2d;

import cm.milkyway.lang.Disposable;
import cm.milkyway.lang.io.Access;

public interface Tex extends Disposable
{

    int LEANER = 0;
    int NEAREST = 1;

    Tex load(Access access);

    double w();

    double h();

    void set(int min, int mag);

}
