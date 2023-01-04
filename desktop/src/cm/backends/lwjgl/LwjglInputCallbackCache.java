package cm.backends.lwjgl;

import cm.milkyway.opengl.input.InputCallback;

import static cm.backends.lwjgl.LwjglEnvironment.cache;

public class LwjglInputCallbackCache extends InputCallback
{

    public void keyTyped(int code)
    {
        if(code >= cache.keyState.length) {
            return;
        }
        cache.keyState[code] = true;
    }

    public void keyReleased(int code)
    {
        if(code >= cache.keyState.length) {
            return;
        }
        cache.keyState[code] = false;
    }

    public void inputText(String text)
    {
        cache.inputString = text;
    }

    public void mouseClicked(int code)
    {
        if(code >= cache.mouseState.length) {
            return;
        }
        cache.mouseState[code] = true;
    }

    public void mouseReleased(int code)
    {
        if(code >= cache.mouseState.length) {
            return;
        }
        cache.mouseState[code] = false;
    }

    public void mouseMove(double x, double y)
    {
        cache.mx = x;
        cache.my = y;
    }

    public void mouseScroll(double delta)
    {
        cache.scroll = delta;
    }
    
}
