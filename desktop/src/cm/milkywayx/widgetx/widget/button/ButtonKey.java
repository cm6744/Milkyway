package cm.milkywayx.widgetx.widget.button;

import cm.milkyway.Milkyway;
import cm.milkyway.opengl.input.InputMap;

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
