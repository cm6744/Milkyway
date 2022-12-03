package cm.milkywaylib.buffers.button;

import cm.milkywaygl.Milkyway;
import cm.milkywaygl.input.InputMap;
import cm.milkywaygl.input.Keys;
import cm.milkywaytool.physics.Physics;

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
        return Physics.boundPoint(renderBox, mx, my);
    }

    public boolean clickOn()
    {
        return hangOn() && InputMap.isClick(key);
    }

}
