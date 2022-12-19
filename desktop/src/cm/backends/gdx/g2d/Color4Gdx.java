package cm.backends.gdx.g2d;

import cm.milkyway.opengl.render.g2d.Color4;
import com.badlogic.gdx.graphics.Color;

public class Color4Gdx implements Color4
{

    double red;
    double green;
    double blue;
    double alpha;

    public Color toNative()
    {
        return new Color((float) red, (float) green, (float) blue, (float) alpha);
    }

    //rgba:0~1.0
    public Color4Gdx(double r, double g, double b, double a)
    {
        r = checkIn(r);
        g = checkIn(g);
        b = checkIn(b);
        a = checkIn(a);

        red = r;
        green = g;
        blue = b;
        alpha = a;
    }

    static double checkIn(double i)
    {
        if(i < 0) {
            return 0;
        }
        if(i > 1) {
            return 1;
        }

        return i;
    }

    public Color4 opacity(double a)
    {
        return new Color4Gdx(red, green, blue, a);
    }

    public double red()
    {
        return red;
    }

    public double blue()
    {
        return blue;
    }

    public double green()
    {
        return green;
    }

    public double alpha()
    {
        return alpha;
    }

}
