package cm.milkywaytype.stg;

import cm.milkywaygl.maths.Maths;
import cm.milkywaygl.render.wrapper.Area;
import cm.milkywaygl.util.IntBuffer;
import cm.milkywaylib.buffers.Bounder;

//a difference: this class needn't set textures by your own,
//but only set TEXTURE statically.
//to raise *EFFICIENCY*, There is a method you shouldn't use:
//#Area.uv().~
//Sometimes this can cause problems, because Java only links ptr.
//If you are determined, be sure about every buffer has its own Area.
public class Bullet extends Bounder
{

    public static int COLOR_MAX = 13;
    public static int UV_SIZE_TILE = 16;
    public static IntBuffer FOG, TEXTURE = IntBuffer.create();

    Action<Bullet> action;

    public Bullet()
    {
        setTexture(Area.create(TEXTURE));
        texture().uv().setSize(UV_SIZE_TILE, UV_SIZE_TILE);
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

    public void dye(int col)
    {
        texture().uv().setX(col * UV_SIZE_TILE);
    }

    public void retype(int typ)
    {
        texture().uv().setY(typ * UV_SIZE_TILE);
    }

    public int color()
    {
        return Maths.toInt(texture().uv().xc() / UV_SIZE_TILE);
    }

    public int type()
    {
        return Maths.toInt(texture().uv().yc() / UV_SIZE_TILE);
    }

    public void act(Action<Bullet> act)
    {
        action = act;
    }

    public Action<Bullet> action()
    {
        return action;
    }

}
