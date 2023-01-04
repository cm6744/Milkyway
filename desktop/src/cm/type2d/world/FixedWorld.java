package cm.type2d.world;

import cm.milkyway.lang.container.list.List;
import cm.milkyway.lang.maths.VecMth;
import cm.milkyway.lang.maths.shapes.Vec2;
import cm.milkyway.opengl.render.g2d.Color;
import cm.milkyway.opengl.render.graphics.Graphics2D;
import cm.milkywayx.physicsx.arcle.Dimension;
import cm.milkywayx.lightx.EnvironmentalLight;
import cm.milkywayx.lightx.LightMap;
import cm.type2d.render.CtUtil;
import cm.type2d.world.entity.Entity;
import cm.type2d.world.entity.Player;
import cm.type2d.room.Room;
import cm.type2d.world.time.Days;

import java.io.Serializable;

public class FixedWorld implements Serializable
{

    public Room curRoom;
    private Room readiedNextRoom;
    private Dimension dim;
    private double effectOpacity;
    private boolean effectIncreasing;
    private boolean effectRunning;
    public Player player;
    public List<SerializableTickable> oftenTicks = new List<>();
    public LightMap lightMap;
    public EnvironmentalLight globalEnv;
    public WorldSky sky = new WorldSky();
    //public Box2DWd physic = new Box2DWd(Vec2.of(9.8, 90), 0.1);

    public void setRoom(Room s)
    {
        if(curRoom != null) {
            curRoom.onRemoveOutOfWorld(this);
        }
        curRoom = s;
        curRoom.onLoadIntoWorld(this);
        readiedNextRoom = null;
    }

    public void enterWithEffect(Room s)
    {
        readiedNextRoom = s;
        effectIncreasing = true;
        effectRunning = true;
    }

    public void tick()
    {
        if(curRoom != null) {
            curRoom.tick();
        }
        CtUtil.fastTick(oftenTicks);

        Days.intensityOfDay(globalEnv);
        globalEnv.updateSmooth();
        sky.tick();

        if(effectRunning) {
            effectOpacity += effectIncreasing ? 0.04 : -0.04;
            if(effectOpacity >= 1) {
                effectIncreasing = false;
                setRoom(readiedNextRoom);
            }
            if(effectOpacity <= 0) {
                effectOpacity = 0;
                effectRunning = false;
            }
        }
    }

    public void render(Graphics2D g)
    {
        sky.render(g);

        if(curRoom != null) {
            curRoom.render(g);
        }

        lightMap.render(g);

        g.drawRect(new Color(0, 0, 0, effectOpacity), 0, 0, g.getContext().width(), g.getContext().height());
    }

    public List<Entity> roomEntities()
    {
        return curRoom.entities;
    }

    public Entity getEntity(double x, double y)
    {
        Entity buf;
        int level = -99999;
        Entity ret = null;

        List<Entity> curInter = roomEntities();

        for(int i = 0; i < curInter.size(); i++) {
            buf = curInter.get(i);
            if(buf == null || !buf.canBeInteracted(player) || buf instanceof Player) {
                continue;
            }
            if(buf.getLevel() > level && buf.bound().contains(x, y)) {
                ret = buf;
                level = ret.getLevel();
            }
        }

        return ret;
    }

    public Entity getNearestEntity()
    {
        Entity buf;
        Entity nearest = null;
        double nDist = 99999;

        List<Entity> curInter = roomEntities();

        for(int i = 0; i < curInter.size(); i++) {
            buf = curInter.get(i);
            if(buf == null || !buf.canBeInteracted(player) || buf instanceof Player) {
                continue;
            }
            boolean inInterArea = buf.bound().interacts(player.getInterArea());
            double dst = VecMth.distanceBetweenAB(buf.bound(), player.bound());
            boolean interWithPlayer = buf.bound().interacts(player.bound());
            //if it can't be found, only when it is in player's bound can it be interacted
            if(buf.canBeFind(player)) {
                if(inInterArea && dst < nDist) {
                    nearest = buf;
                    nDist = dst;
                }
            }
            else if(interWithPlayer) {
                nearest = buf;
                nDist = dst;
            }
        }

        return nearest;
    }

}
