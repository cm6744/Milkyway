package cm.backends.lwjgl;

import cm.milkyway.opengl.render.g2d.Font;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class LwjglFont implements Font
{

    public double size;
    public BitmapFont font;

    LwjglFont(BitmapFont nat, double siz)
    {
        font = nat;
        size = siz;
    }

    public void dispose()
    {
        font.dispose();
    }

    public double size()
    {
        return size;
    }

}
