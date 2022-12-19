package cm.milkyway.opengl.render.g2d;

import cm.milkyway.Milkyway;

public class Text
{

    Font2 font;
    String component;

    public static Text create(Font2 font, String comp)
    {
        return new Text(font, comp);
    }

    private Text(Font2 f, String c)
    {
        font = f;
        component = c;
    }

    public void render(double x, double y, boolean center)
    {
        Milkyway.glBase.state().font(font);
        Milkyway.glText.text(component, x, y, center);
        Milkyway.glBase.state().clear();
    }

    public double size()
    {
        return font.size();
    }

}
