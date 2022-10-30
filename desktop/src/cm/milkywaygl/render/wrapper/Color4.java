package cm.milkywaygl.render.wrapper;

import com.badlogic.gdx.graphics.Color;

public class Color4
{

    public static Color4 LIGHT = Color4.create(1, 1, 1, 0.5);
    public static Color4 SHADOW = Color4.create(0, 0, 0, 0.5);
    public static Color4 WHITE = Color4.create(1, 1, 1);
    public static Color4 BLACK = Color4.create(0, 0, 0);
    public static Color4 GOLD = Color4.create(1, 0.9, 0.6);
    public static Color4 PURPLE = Color4.create(0.9, 0.7, 0.9);

    ///////////***** impl ****///////////
    public Color _nativeColor;
    double red;
    double green;
    double blue;
    double alpha;

    //rgba:0~1.0
    private Color4(double r, double g, double b, double a)
    {
        r = checkIn(r);
        g = checkIn(g);
        b = checkIn(b);
        a = checkIn(a);

        red = r;
        green = g;
        blue = b;
        alpha = a;

        _nativeColor = new Color((float) r, (float) g, (float) b, (float) a);
    }

    public static Color4 create(double r, double g, double b, double a)
    {
        return new Color4(r, g, b, a);
    }

    public static Color4 create(double r, double g, double b)
    {
        return create(r, g, b, 1.0);
    }

    public static double checkIn(double i)
    {
        if(i < 0) {
            return 0;
        }
        if(i > 1) {
            return 1;
        }

        return i;
    }

    public Color4 transparency(double a)
    {
        return Color4.create(red, green, blue, a);
    }

    public double getRed()
    {
        return red;
    }

    public double getBlue()
    {
        return blue;
    }

    public double getGreen()
    {
        return green;
    }

    public double getAlpha()
    {
        return alpha;
    }

}
