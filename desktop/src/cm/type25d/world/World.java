package cm.type25d.world;

import cm.milkyway.lang.maths.Mth;
import cm.type25d.tile.TileIns;

public class World
{

    TileIns[][][] tiles;
    boolean[][] solid;
    public int width, height, depth;

    public World(int w, int h, int d)
    {
        tiles = new TileIns[d][w][h];
        solid = new boolean[w][h];
        width = w;
        height = h;
        depth = d;
    }

    private int clampedI(double x)
    {
        int i = (int) Math.floor((x) / 32);
        i = (int) Mth.min(i, width - 1);
        i = (int) Mth.max(i, 0);
        return i;
    }

    private int clampedJ(double y)
    {
        int j = (int) Math.floor((y) / 32);
        j = (int) Mth.min(j, height - 1);
        j = (int) Mth.max(j, 0);
        return j;
    }

    public TileIns getTile(int i, int j, int d)
    {
        TileIns t = tiles[d][i][j];
        if(t == null) {
            return TileIns.NULL;
        }
        return t;
    }

    public void setTile(int i, int j, int d, TileIns ins)
    {
        tiles[d][i][j] = ins;
    }

    public TileIns getTileXY(double x, double y, int depth)
    {
        return getTile(clampedI(x), clampedJ(y), depth);
    }

    public void setTileXY(double x, double y, int depth, TileIns ins)
    {
        setTile(clampedI(x), clampedJ(y), depth, ins);
    }

    public void setSolid(int i, int j, boolean s)
    {
        solid[i][j] = s;
    }

    public void setSolidXY(double x, double y, boolean s)
    {
        setSolid(clampedI(x), clampedJ(y), s);
    }

}
