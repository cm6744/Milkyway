package cm.backends;

import cm.backends.gdx.input.KeysGdx;
import cm.backends.gdx.json.JsonGdx;
import cm.backends.gdx.res.AccessorJGdx;
import cm.backends.gdx.sound.AudioGdx;
import cm.milkyway.BackendMark;
import cm.milkyway.Milkyway;
import cm.backends.gdx.*;

@BackendMark
public class Backends
{

    public static void set(String type)
    {
        if(type.equals("GDX")) {
            Milkyway.window = new WinGdx();
            Milkyway.audio = new AudioGdx();
            Milkyway.graphics = new GraphicsGdx();
            Milkyway.json = new JsonGdx();
            Milkyway.accessor = new AccessorJGdx();
            Milkyway.keys = new KeysGdx();
        }
        else {
            throw new IllegalArgumentException("Not found implement: " + type);
        }
    }

}
