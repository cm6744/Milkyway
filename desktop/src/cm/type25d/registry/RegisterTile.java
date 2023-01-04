package cm.type25d.registry;

import cm.backends.lwjgl.LwjglTexture;
import cm.milkyway.lang.io.AccessLocal;
import cm.milkyway.opengl.render.g2d.AreaStatic;
import cm.milkyway.opengl.render.g2d.Tex;
import cm.milkywayx.registx.Register;
import cm.type25d.Load;
import cm.type25d.tile.Tile;

public class RegisterTile extends Register<Tile>
{

    public void register(String s, Tile.TileProperties p)
    {
        register(s, new Tile(s, p));
    }

    public void doRegister()
    {
        register("lawn_1", new Tile.TileProperties());
        register("snow_1", new Tile.TileProperties());
        register("lawn_side_1", new Tile.TileProperties());
        register("lawn_rock_1", new Tile.TileProperties());
        register("rock_1", new Tile.TileProperties());
        register("brick_1", new Tile.TileProperties());
    }

}
