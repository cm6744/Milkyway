package cm.milkyway;

import cm.milkyway.opengl.input.InputMap;
import cm.milkyway.opengl.render.g2d.Font2;

public class Debug
{

    public static void renderDebug(Font2 font)
    {
        Milkyway.glBase.state().color(Milkyway.graphics.newColor(1, 1, 1));
        Milkyway.glBase.state().font(font);
        Milkyway.glShape.dim(InputMap.x() - 1, 0, 2, Milkyway.glBase.height());
        Milkyway.glShape.dim(0, InputMap.y() - 1, Milkyway.glBase.width(), 2);
        Milkyway.glText.text("[" + InputMap.x() + ", " + InputMap.y() + "]", InputMap.x(), InputMap.y(), false);
    }

}
