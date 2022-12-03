package cm.glbackends;

import cm.milkywaygl.BackendMark;
import cm.milkywaygl.Milkyway;
import cm.glbackends.gdx.*;

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
    }

}
