package cm.backends.gdx.input;

import cm.milkyway.opengl.input.Key;

public class KeyGdx implements Key
{

    public int code;
    public boolean mouse;

    public KeyGdx(int c, boolean mou)
    {
        code = c;
        mouse = mou;
    }

    public boolean isMouse()
    {
        return mouse;
    }

    public boolean isKey()
    {
        return !isMouse();
    }

    public int code()
    {
        return code;
    }

}
