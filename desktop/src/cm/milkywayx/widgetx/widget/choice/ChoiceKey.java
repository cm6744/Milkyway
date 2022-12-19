package cm.milkywayx.widgetx.widget.choice;

import cm.milkyway.opengl.input.InputMap;

public class ChoiceKey extends Choice
{

    protected boolean leftScroll()
    {
        return InputMap.isOn(curPost);
    }

    protected boolean rightScroll()
    {
        return InputMap.isOn(curNeg);
    }

}
