package cm.backends.lwjgl;

import cm.milkyway.lang.io.Access;
import cm.milkyway.opengl.render.g2d.Tex;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class LwjglTexture implements Tex
{

    public Texture tex;

    public LwjglTexture set(Texture t)
    {
        tex = t;

        LwjglEnvironment.autoDispose(this);

        return this;
    }

    public Tex load(Access access)
    {
        set(new Texture(Gdx.files.absolute(access.path())));
        return this;
    }

    public double w()
    {
        return tex.getWidth();
    }

    public double h()
    {
        return tex.getHeight();
    }

    public void dispose()
    {
        tex.dispose();
    }

    public void set(int min, int mag)
    {
        tex.setFilter(fromInt(min), fromInt(mag));
    }

    private Texture.TextureFilter fromInt(int i)
    {
        switch(i) {
            case LEANER:
                return Texture.TextureFilter.Linear;
            case NEAREST:
                return Texture.TextureFilter.Nearest;
        }
        return null;
    }

}
