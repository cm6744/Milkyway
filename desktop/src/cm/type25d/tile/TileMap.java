package cm.type25d.tile;

import cm.backends.lwjgl.LwjglEnvironment;
import cm.backends.tipy.DefaultTipyWriter;
import cm.milkyway.lang.maths.Mth;
import cm.milkyway.opengl.input.InputMap;
import cm.milkyway.opengl.render.graphics.Graphics2D;
import cm.milkyway.tipy.Tipy;
import cm.type25d.registry.Registry;
import cm.type25d.world.World;

public class TileMap
{

    World world;
    double rox, roy;
    boolean hasLimit = true;

    public TileMap(World w)
    {
        world = w;
    }

    public void setHasLimit(boolean limit)
    {
        hasLimit = limit;
    }

    public void roll(double x, double y)
    {
        rox = x;
        roy = y;
        if(hasLimit) {
            rox = Mth.max(rox, LwjglEnvironment.context.width() - world.width * 32);
            rox = Mth.min(rox, 0);
            roy = Mth.max(roy, LwjglEnvironment.context.height() - world.height * 32);
            roy = Mth.min(roy, 0);
        }
    }

    public void rollMv(double mx, double my)
    {
        roll(rox + mx, roy + my);
    }

    public TileIns getTileUnderMouse(int depth)
    {
        return world.getTileXY(InputMap.x() - rox, InputMap.y() - roy, depth);
    }

    public void setTileUnderMouse(int depth, TileIns ins)
    {
        world.setTileXY(InputMap.x() - rox, InputMap.y() - roy, depth, ins);
    }

    public void setTileUnderMouse(boolean s)
    {
        world.setSolidXY(InputMap.x() - rox, InputMap.y() - roy, s);
    }

    public void render(Graphics2D g)
    {
        for(int i = 0; i < world.depth; i++) {
            for(int j = 0; j < world.width; j++) {
                for(int k = 0; k < world.height; k++) {
                    world.getTile(j, k, i).render(g, j, k, rox, roy);
                }
            }
        }
    }

    public void load(Tipy tipy)
    {
        Tipy tiles = tipy.get("tiles");
        for(int i = 0; i < world.depth; i++) {
            for(int j = 0; j < world.width; j++) {
                for(int k = 0; k < world.height; k++) {
                    world.setTile(j, k, i, TileIns.NULL);
                    if(!tiles.has(i + ":" + j + ":" + k)) {
                        continue;
                    }
                    Tipy cld = tiles.get(i + ":" + j + ":" + k);
                    String type = cld.getString("type");
                    int flip = cld.getInt("flip");
                    int s = cld.getInt("sts");
                    double rot = cld.getDouble("rotation");
                    double ox = cld.getDouble("ox");
                    double oy = cld.getDouble("oy");
                    TileIns ins = new TileIns(Registry.TILES.get(type));
                    ins.setFlip(flip);
                    ins.setRotation(rot);
                    ins.setOffset(ox, oy);
                    ins.setState(s);
                    world.setTile(j, k, i, ins);
                }
            }
        }
    }

    public DefaultTipyWriter save()
    {
        DefaultTipyWriter writer = new DefaultTipyWriter();
        Tipy tipy = writer.createSaveData();
        Tipy tiles = tipy.object("tiles");
        for(int i = 0; i < world.depth; i++) {
            for(int j = 0; j < world.width; j++) {
                for(int k = 0; k < world.height; k++) {
                    TileIns t = world.getTile(j, k, i);
                    if(t != TileIns.NULL) {
                        Tipy cld = tiles.object(i + ":" + j + ":" + k);
                        cld.putBoolean("null", false);
                        cld.putString("type", t.tile.type);
                        cld.putInt("flip", t.flip);
                        cld.putDouble("rotation", t.rotation);
                        cld.putDouble("ox", t.offsetX);
                        cld.putDouble("oy", t.offsetY);
                        cld.putInt("sts", t.state);
                    }
                }
            }
        }
        return writer;
    }

}
