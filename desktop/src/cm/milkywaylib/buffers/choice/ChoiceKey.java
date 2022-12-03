package cm.milkywaylib.buffers.choice;

import cm.milkywaygl.input.InputMap;

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
