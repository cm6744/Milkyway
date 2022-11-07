package cm.milkywaylib.stg;

import cm.milkywaygl.maths.Maths;
import cm.milkywaygl.maths.check.Box4;
import cm.milkywaygl.render.GL;
import cm.milkywaygl.util.IntBuffer;
import cm.milkywaylib.linkdown.BufBound;

public class BufBullet extends BufBound
{

    public static int UV_SIZE_TILE = 16;
    public static int UV_SIZE_TILE_BIG = 48;
    public static IntBuffer FOG, TEXTURE = IntBuffer.newBuf();

    Box4 uv;
    Box4 fogUV;
    Box4 fogBox;
    Action<BufBullet> action;

    public BufBullet()
    {
        uv = Box4.offset(UV_SIZE_TILE, UV_SIZE_TILE);
        fogUV = Box4.offset(UV_SIZE_TILE, UV_SIZE_TILE);
        fogBox = Box4.normal();
    }

    public void tickThen()
    {
        super.tickThen();
        effect.setRotation(vec2.degree());

        if(action != null) {
            action.buf = this;
            action.tick();
        }
    }

    public void renderThen()
    {
        GL.gl.curState().opacity(time() / 20.0);
        GL.gl2.dim(TEXTURE, box4, uv);
        if(time() <= 20) {
            GL.gl.curState().opacity((20 - time()) / 20.0);
            fogBox.copy(box4);
            double s = (20 + box4.width()) - time();
            fogBox.setSize(s, s);
            GL.gl2.dim(FOG, fogBox, fogUV);
        }
    }

    public void dye(int col)
    {
        uv.setX(col * UV_SIZE_TILE);
        fogUV.setX(col * UV_SIZE_TILE);
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

    public void act(Action<BufBullet> act)
    {
        action = act;
    }

    public Action<BufBullet> action()
    {
        return action;
    }

}
