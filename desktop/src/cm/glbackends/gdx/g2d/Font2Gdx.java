package cm.glbackends.gdx.g2d;

import cm.milkywaygl.render.g2d.Font2;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class Font2Gdx implements Font2
{

    public double size;
    public BitmapFont _nativeFont;

    Font2Gdx(BitmapFont nat, double siz)
    {
        _nativeFont = nat;
        size = siz;
    }

    public void dispose()
    {
        _nativeFont.dispose();
    }

    public double size()
    {
        return size;
    }

}
