package cm.milkywaylib.buffers.choice;

import cm.milkywaygl.input.InputMap;

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
