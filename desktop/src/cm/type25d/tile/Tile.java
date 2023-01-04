package cm.type25d.tile;

import cm.backends.tipy.DefaultTipyReader;
import cm.milkyway.lang.container.map.Map;
import cm.milkyway.lang.io.AccessLocal;
import cm.milkyway.opengl.render.g2d.Area;
import cm.milkyway.opengl.render.g2d.AreaStatic;
import cm.milkyway.tipy.Tipy;
import cm.type25d.Load;

public class Tile
{

    String type;
    TileProperties prop;

    public Tile(String t, TileProperties p)
    {
        type = t;
        prop = p;
        prop.readStateTipy(t);
    }

    public String getType()
    {
        return type;
    }

    public TileProperties getProperties()
    {
        return prop;
    }

    public static class TileProperties
    {

        public Area[] tex = new Area[64];
        public int maxExistState;
        public double friction;
        public double speedSlide;

        public TileProperties readStateTipy(String name)
        {
            Tipy tipy = new DefaultTipyReader().read(new AccessLocal("data/tiles/" + name + ".tipy"));
            Tipy states = tipy.get("states");
            states.toMap().iterate((o, i) -> {
                Tipy obj = o.value();
                int[] uv = obj.getArrayInt("uv");
                state(i, AreaStatic.dim(Load.loader.getTex(obj.getString("texture")), uv[0], uv[1], uv[2], uv[3]));
            }, false);
            return this;
        }

        public TileProperties state(int s, Area a)
        {
            tex[s] = a;
            maxExistState = s;
            return this;
        }

    }

}
