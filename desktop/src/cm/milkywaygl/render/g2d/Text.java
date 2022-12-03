package cm.milkywaygl.render.g2d;

import cm.milkywaygl.Milkyway;

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
        Milkyway.glBase.curState().font(font);
        Milkyway.glText.text(component, x, y, center);
        Milkyway.glBase.curState().clear();
    }

    public double size()
    {
        return font.size();
    }

}
