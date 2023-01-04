package cm.milkyway.opengl.render.g2d;

public class Color
{

    public static Color C1111 = new Color(1, 1, 1, 1);
    public static Color C0001 = new Color(0, 0, 0, 1);
    public static Color C0000 = new Color(0, 0, 0, 0);

    double red;
    double green;
    double blue;
    double alpha;

    //rgba:0~1.0
    public Color(double r, double g, double b, double a)
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

    public Color(double r, double g, double b)
    {
        this(r, g, b, 1.0);
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

    public void setOpacity(double a)
    {
        alpha = a;
    }

    public void set(double r, double g, double b, double a)
    {
        red = r;
        green = g;
        blue = b;
        alpha = a;
    }

    public void trs(double r, double g, double b, double a)
    {
        red += r;
        green += g;
        blue += b;
        alpha += a;
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
