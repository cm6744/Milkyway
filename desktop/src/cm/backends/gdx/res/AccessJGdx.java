package cm.backends.gdx.res;

import cm.milkyway.opengl.io.Access;

public class AccessJGdx implements Access
{

    String path;

    public AccessJGdx set(String pth)
    {
        path = pth;
        return this;
    }

    public String path()
    {
        return path;
    }

}
