package cm.milkywaygl;

import cm.milkywaygl.input.InputMap;
import cm.milkywaygl.render.g2d.Font2;

public class Debug
{

    public static void renderDebug(Font2 font)
    {
        Milkyway.glBase.curState().color(Milkyway.graphics.newColor(1, 1, 1));
        Milkyway.glBase.curState().font(font);
        Milkyway.glShape.dim(InputMap.x() - 1, 0, 2, Milkyway.glBase.height());
        Milkyway.glShape.dim(0, InputMap.y() - 1, Milkyway.glBase.width(), 2);
        Milkyway.glText.text("[" + InputMap.x() + ", " + InputMap.y() + "]", InputMap.x(), InputMap.y(), false);
    }

}
