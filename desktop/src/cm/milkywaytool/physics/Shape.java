package cm.milkywaytool.physics;

public interface Shape
{

    boolean contains(double x, double y);

    double x();

    double y();

    double w();

    double h();

    void setX(double xi);

    void setY(double yi);

    void setWidth(double wi);

    void setHeight(double hi);

    void setRotation(double v);

    double degree();

    default void loc(double xi, double yi)
    {
        setX(xi);
        setY(yi);
    }

    default void mvLoc(double xi, double yi)
    {
        setX(xi + x());
        setY(yi + y());
    }

    default void resize(double wi, double hi)
    {
        setWidth(wi);
        setHeight(hi);
    }

    default void mvSize(double wi, double hi)
    {
        setWidth(w() + wi);
        setHeight(h() + hi);
    }

    default void mulSize(double wi, double hi)
    {
        setWidth(w() * wi);
        setHeight(h() * hi);
    }

    default void rotate(double v)
    {
        setRotation(degree() + v);
    }

}
