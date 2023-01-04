package cm.milkyway.opengl.render;

import cm.milkyway.lang.io.Access;
import cm.milkyway.lang.io.AccessAbsolute;

public class Preference
{

    public String title;
    public Access cursor = new AccessAbsolute("");
    public Access icon = new AccessAbsolute("");
    public int bitDepth;
    public int refreshRate;
    public int width;
    public int height;
    public int winWidth;
    public int winHeight;
    public boolean fullScreen;
    public int fps;

}
