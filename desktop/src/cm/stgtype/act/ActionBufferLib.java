package cm.stgtype.act;

import cm.milkywaycls.cls.lib.Lib;
import cm.milkywaycls.cls.method.NumMethod;
import cm.milkywaycls.cls.method.VoidMethod;
import cm.milkywaycls.cls.value.CLSMethodArg;
import cm.milkywaylib.base.RenderBuffer;
import cm.milkywaytool.container.Map;
import cm.milkywaytool.maths.VecMth;
import cm.stgtype.Bullet;
import cm.stgtype.BulletMap;
import cm.stgtype.Share;

public class ActionBufferLib extends Lib
{

    static Map<String, ActionBulletCms> bas = new Map<>();
    static Map<String, ActionEnemyCms> eas = new Map<>();

    Action<? extends RenderBuffer> action;

    public void load()
    {
        put("time", new STBTm());
        put("p_direc", new STBAtP());
        put("stb_add", new STB1());
        put("stb_ring", new STB2());
        put("stb_laser", new STBLaser());
        put("stb_laser_ring", new STBLaser2());
        put("mv_vec", new T1());
    }

    public ActionBufferLib(Action<? extends RenderBuffer> act)
    {
        action = act;
    }

    private class T1 extends VoidMethod
    {
        public void invoke(CLSMethodArg ps)
        {
            action.buf.vec().mvVec(ps.vDouble(0), ps.vDouble(1));
        }
    }

    private static Bullet match(int type, int color)
    {
        return BulletMap.type_color_mat[type][color];
    }

    private class STBTm extends NumMethod
    {
        public void invoke(CLSMethodArg ps)
        {
            retVal().set(action.buf.time());
        }
    }

    private class STBAtP extends NumMethod
    {
        public void invoke(CLSMethodArg ps)
        {
            retVal().set(VecMth.degreeBetweenAB(Share.player.box(), action.buf.box()));
        }
    }

    private class STB1 extends VoidMethod
    {
        public void invoke(CLSMethodArg ps)
        {
            Share.shooter.add(match(ps.vInt(1), ps.vInt(2)),
                              bas.get(ps.vString(0)),
                              null,
                              action.buf.box().x() + ps.vDouble(3),
                              action.buf.box().y() + ps.vDouble(4),
                              ps.vDouble(5), ps.vDouble(6)
            );
        }
    }

    private class STBLaser extends VoidMethod
    {
        public void invoke(CLSMethodArg ps)
        {
            Share.shooter.laser(match(ps.vInt(1), ps.vInt(2)),
                                bas.get(ps.vString(0)),
                                null,
                                action.buf.box().x() + ps.vDouble(3),
                                action.buf.box().y() + ps.vDouble(4),
                                ps.vDouble(5), ps.vInt(6), ps.vInt(7)
            );
        }
    }

    private class STBLaser2 extends VoidMethod
    {
        public void invoke(CLSMethodArg ps)
        {
            Share.shooter.laserRing(match(ps.vInt(1), ps.vInt(2)),
                                    bas.get(ps.vString(0)),
                                    null,
                                    action.buf.box().x() + ps.vDouble(3),
                                    action.buf.box().y() + ps.vDouble(4),
                                    ps.vInt(8), ps.vInt(9),
                                    ps.vDouble(5), ps.vInt(6), ps.vInt(7)
            );
        }
    }

    private class STB2 extends VoidMethod
    {
        public void invoke(CLSMethodArg ps)
        {
            Share.shooter.ring(match(ps.vInt(1), ps.vInt(2)),
                               bas.get(ps.vString(0)),
                               null,
                               action.buf.box().x() + ps.vDouble(3),
                               action.buf.box().y() + ps.vDouble(4),
                               ps.vInt(7), ps.vDouble(8),
                               ps.vDouble(5), ps.vDouble(6)
            );
        }
    }

}
