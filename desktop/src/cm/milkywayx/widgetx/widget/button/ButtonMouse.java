package cm.milkywayx.widgetx.widget.button;

import cm.milkyway.opengl.input.InputMap;
import cm.milkyway.opengl.input.Key;

public class ButtonMouse extends Button
{

    public ButtonMouse(Key k)
    {
        key = k;
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
