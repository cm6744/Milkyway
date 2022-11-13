package cm.milkywaygl.render.wrapper;

import cm.milkywaygl.util.IndexCache;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

//Not safe
public class Font2
{

    public static IndexCache<Font2> CACHES = new IndexCache<>();

    public double size;
    public int id;
    public BitmapFont _nativeFont;

    Font2(BitmapFont nat, double siz)
    {
        _nativeFont = nat;
        size = siz;
        id = CACHES.gen(this).value();
    }

}
