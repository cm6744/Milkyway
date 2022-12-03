package cm.glbackends.gdx;

import cm.milkywaygl.io.Access;

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
