package cm.milkyway.opengl.render.g2d;

import cm.milkyway.opengl.render.graphics.Graphics2D;

public class Text
{

    Font font;
    Color color;
    String component;

    public static Text create(Font font, Color col, String comp)
    {
        return new Text(font, col, comp);
    }

    private Text(Font f, Color col, String c)
    {
        font = f;
        component = c;
        color = col;
    }

    public void render(Graphics2D g, double x, double y, boolean center)
    {
        g.drawText(font, color, component, x, y, center);
    }

    public double size()
    {
        return font.size();
    }

}
