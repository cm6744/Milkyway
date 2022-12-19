package cm.milkywayx.widgetx.widget.button;

import cm.milkyway.Milkyway;
import cm.milkyway.opengl.input.InputMap;

public class ButtonMouse extends Button
{

    public ButtonMouse()
    {
        key = Milkyway.keys.mouse("left");
    }

    public boolean hangOn()
    {
        double mx = InputMap.x();
        double my = InputMap.y();
        return renderBox.contains(mx, my);
    }

    public boolean clickOn()
    {
        return hangOn() && InputMap.isClick(key);
    }

}
