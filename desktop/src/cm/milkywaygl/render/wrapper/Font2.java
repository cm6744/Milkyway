package cm.milkywaygl.render.wrapper;

import com.badlogic.gdx.graphics.g2d.BitmapFont;

//Not safe
public class Font2
{

    public double size;
    public BitmapFont _nativeFont;

    Font2(BitmapFont nat, double siz)
    {
        _nativeFont = nat;
        size = siz;
    }

}
