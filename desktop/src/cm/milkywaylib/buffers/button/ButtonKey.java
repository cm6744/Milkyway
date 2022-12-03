package cm.milkywaylib.buffers.button;

import cm.milkywaygl.Milkyway;
import cm.milkywaygl.input.InputMap;
import cm.milkywaygl.input.Keys;

public class ButtonKey extends Button
{

    boolean force;

    public ButtonKey()
    {
        key = Milkyway.keys.key("z");
    }

    public void forceHang(boolean fh)
    {
        force = fh;
    }

    public boolean hangOn()
    {
        return force;
    }

    public boolean clickOn()
    {
        return hangOn() && InputMap.isClick(key);
    }

}
