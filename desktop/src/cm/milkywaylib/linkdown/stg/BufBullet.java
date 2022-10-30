package cm.milkywaylib.linkdown.stg;

import cm.milkywaygl.maths.Maths;
import cm.milkywaygl.maths.check.Box4;
import cm.milkywaygl.render.GL;
import cm.milkywaylib.linkdown.BufBound;

public class BufBullet extends BufBound
{

    public static int UV_SIZE_TILE = 16;
    public static int UV_SIZE_TILE_BIG = 48;

    Box4 uv;

    public BufBullet()
    {
        uv = Box4.offset(UV_SIZE_TILE, UV_SIZE_TILE);
    }

    public void tick()
    {
        super.tick();
        effect.setRotation(vec2.degree());
    }

    public void implRender()
    {
        GL.gl8.draw(texture, box4, uv);
    }

    public void dye(int col)
    {
        uv.setX(col * UV_SIZE_TILE);
    }

    public void retype(int typ)
    {
        uv.setY(typ * UV_SIZE_TILE);
    }

    public int color()
    {
        return Maths.toInt(uv.xc() / UV_SIZE_TILE);
    }

    public int type()
    {
        return Maths.toInt(uv.yc() / UV_SIZE_TILE);
    }

}
