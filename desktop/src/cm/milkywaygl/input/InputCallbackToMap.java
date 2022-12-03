package cm.milkywaygl.input;

public class InputCallbackToMap extends InputCallback
{

    public void keyTyped(int code)
    {
        if(code >= InputMap.keyState.length) {
            return;
        }
        InputMap.keyState[code] = true;
    }

    public void keyReleased(int code)
    {
        if(code >= InputMap.keyState.length) {
            return;
        }
        InputMap.keyState[code] = false;
    }

    public void inputText(String text)
    {
        InputMap.inputString = text;
    }

    public void mouseClicked(int code)
    {
        if(code >= InputMap.mouseState.length) {
            return;
        }
        InputMap.mouseState[code] = true;
    }

    public void mouseReleased(int code)
    {
        if(code >= InputMap.mouseState.length) {
            return;
        }
        InputMap.mouseState[code] = false;
    }

    public void mouseMove(double x, double y)
    {
        InputMap.mx = x;
        InputMap.my = y;
    }

    public void mouseScroll(double delta)
    {
        InputMap.scroll = delta;
    }

}
