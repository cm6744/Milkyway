package cm.milkywayx.widgetx.widget.choice;

import cm.milkyway.opengl.input.InputMap;

public class ChoiceMouse extends Choice
{

    protected boolean leftScroll()
    {
        return InputMap.scroll() < 0;
    }

    protected boolean rightScroll()
    {
        return InputMap.scroll() > 0;
    }

}
