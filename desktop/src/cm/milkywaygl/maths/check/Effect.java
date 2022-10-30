package cm.milkywaygl.maths.check;

public class Effect
{

    public static final double FULL = 1.0D;

    double opacity;
    double rotation;

    public static Effect of(double opacity)
    {
        return new Effect().setOpacity(opacity);
    }

    public static Effect normal()
    {
        return of(FULL);
    }

    public Effect setOpacity(double opa)
    {
        opacity = opa;
        return this;
    }

    public Effect mvOpacity(double mo)
    {
        opacity += mo;
        return this;
    }

    public double opacity()
    {
        if(opacity > FULL) {
            opacity = FULL;
        }
        if(opacity < 0) {
            opacity = 0;
        }
        return opacity;
    }

    public Effect setRotation(double rot)
    {
        rotation = rot;
        return this;
    }

    public Effect mvRotation(double rot)
    {
        rotation += rot;
        return this;
    }

    public double rotation()
    {
        return rotation;
    }

}
