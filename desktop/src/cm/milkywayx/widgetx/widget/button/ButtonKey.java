package cm.milkywayx.widgetx.widget.button;

import cm.milkyway.opengl.input.InputMap;
import cm.milkyway.opengl.input.Key;

public class ButtonKey extends Button
{

    boolean force;

    public ButtonKey(Key k)
    {
        key = k;
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
