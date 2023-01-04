package cm.type25d;

import cm.backends.lwjgl.LwjglKey;
import cm.backends.tipy.DefaultTipyReader;
import cm.milkyway.lang.container.list.List;
import cm.milkyway.lang.container.map.Map;
import cm.milkyway.lang.container.map.Node;
import cm.milkyway.lang.io.AccessLocal;
import cm.milkyway.lang.maths.Mth;
import cm.milkyway.opengl.input.InputMap;
import cm.milkyway.opengl.render.g2d.Area;
import cm.milkyway.opengl.render.g2d.Color;
import cm.milkyway.opengl.render.graphics.Graphics2D;
import cm.milkywayx.widgetx.scene.Scene;
import cm.type25d.registry.Registry;
import cm.type25d.tile.Player;
import cm.type25d.tile.Tile;
import cm.type25d.tile.TileIns;
import cm.type25d.tile.TileMap;
import cm.type25d.world.World;

public class ScenePainter extends Scene
{

    List<Area> allAreas = new List<>();
    Map<Area, Tile> areaTileMap = new Map<>();
    Map<Area, Integer> areaStateMap = new Map<>();
    int cursor;
    int curWait;

    World world;
    TileMap s;
    int chose = 0;
    int depth;
    boolean flipx, flipy;
    boolean init;
    Player player = new Player();
    TileIns lastPlace;

    public ScenePainter()
    {
        Registry.callRegistry();
        //add all to registered list, all states
        List<Node<String, Tile>> tileList = Registry.TILES.map().toList();
        tileList.iterate((o, i) -> {
            int ct = 0;
            for(Area a : o.value().getProperties().tex) {
                if(a != null) {
                    allAreas.add(a);
                    areaTileMap.put(a, o.value());
                    areaStateMap.put(a, ct);
                }
                ct++;
            }
        }, false);

        canvas(48, 32, 5);
    }

    public void canvas(int w, int h, int d)
    {
        s = new TileMap(world = new World(w, h, d));
        player.map = s;
    }

    public void tick()
    {
        curWait--;
        if(curWait < 0) {
            if(InputMap.scroll() > 0) {
                curWait = 1;
                cursor++;
                cursor = (int) Mth.min(cursor, allAreas.last());
            }
            if(InputMap.scroll() < 0) {
                curWait = 1;
                cursor--;
                cursor = (int) Mth.max(cursor, 0);
            }
        }

        Map<String, Tile> tiles = Registry.TILES.map();
        List<Node<String, Tile>> tileList = tiles.toList();

        if(InputMap.isOn(LwjglKey.mouse("left")))
        {
                Area a = allAreas.get(cursor);
                TileIns i = new TileIns(areaTileMap.get(a));
                i.setState(areaStateMap.get(a));
                if(flipx) {
                    i.setFlip(TileIns.FLIP_X);
                }
                if(flipy) {
                    i.setFlip(TileIns.FLIP_Y);
                }
                if(flipx && flipy) {
                    i.setFlip(TileIns.FLIP_XY);
                }
                s.setTileUnderMouse(depth, i);
                lastPlace = i;
        }
        if(InputMap.isOn(LwjglKey.mouse("right"))) {
            TileIns i = s.getTileUnderMouse(depth);
            //i.randFlip();
            //i.rand4way();
            i.randState();
        }
        if(InputMap.isClick(LwjglKey.key("up"))) depth++;
        if(InputMap.isClick(LwjglKey.key("down"))) depth--;
        if(depth < 0) depth = 0;
        if(depth >= world.depth) depth = world.depth - 1;

        player.tick();

        if(InputMap.isClick(LwjglKey.key("2"))) {
            s.save().write(new AccessLocal("save.tipy"));
        }
        if(InputMap.isClick(LwjglKey.key("1"))) {
            s.load(new DefaultTipyReader().read(new AccessLocal("save.tipy")));
        }
    }

    private void draw(Graphics2D g, int ofs, double x, double y)
    {
        if(allAreas.checkIndex(cursor + ofs)) {
            g.draw(allAreas.get(cursor + ofs), x, y, 32, 32);
        }
    }

    public void render(Graphics2D g)
    {
        s.render(g);
        g.drawRect(new Color(1, 0, 0), 300 - 2, 20 - 2, 32 + 4, 32 + 4);
        for(int i = -20; i < 20; i++)
        {
            draw(g, i, 300 + 32 * i, 20);
        }
        player.render(g);
    }

}
