package cm.type2d.world.entity;

import cm.backends.lwjgl.LwjglKey;
import cm.milkyway.lang.maths.Mth;
import cm.milkyway.lang.maths.shapes.Vec2;
import cm.milkyway.opengl.render.g2d.Color;
import cm.milkyway.opengl.render.graphics.Graphics2D;
import cm.milkywayx.physicsx.arcle.Force;
import cm.milkyway.opengl.input.InputMap;
import cm.milkyway.opengl.render.g2d.AreaAnimated;
import cm.milkyway.opengl.render.g2d.Tex;
import cm.milkyway.lang.maths.shapes.Rect;
import cm.milkywayx.storfesx.CombinedFes;
import cm.type2d.Load;
import cm.type2d.item.PickableInv;
import cm.type2d.item.StackSerializer;
import cm.type2d.room.Room;
import cm.type2d.serialize.FesSerializer;
import cm.type2d.world.FixedWorld;
import cm.type2d.world.entity.serialize.CommonSerializer;

public class Player extends LivingEntity
{

    boolean right;
    boolean left;
    boolean walking;
    AreaAnimated walk;
    AreaAnimated stay;
    AreaAnimated[] randoms;
    double uptime;
    int specialAct = -1;
    int actTime;
    PlayerInterHandle handle = new PlayerInterHandle();
    public PickableInv inv = new PickableInv(12, this);
    public boolean taskRight;
    public boolean taskLeft;

    public Player(FixedWorld w, Room r, double x, double y, Tex walki, Tex stayi, AreaAnimated[] rands)
    {
        super(w, r, x, y);
        walk = new AreaAnimated(walki, 4, 1, 0).perTime(5);
        stay = new AreaAnimated(stayi, 4, 1, 0).perTime(20);
        randoms = rands;
    }

    public PlayerInterHandle handle()
    {
        return handle;
    }

    public String name()
    {
        return "entity_player";
    }

    Rect interArea = Rect.sized(320, 320);

    public Rect getInterArea()
    {
        return interArea;
    }

    Vec2 speedProcess = Vec2.normal();

    public void tickThen()
    {
        walking = false;
        boolean lo = InputMap.isOn(LwjglKey.key("a"));
        boolean ro = InputMap.isOn(LwjglKey.key("d"));
        if(lo || taskLeft) {
            right = false;
            left = true;
            collision.step(Vec2.of(5, 180));
            walking = true;
        }
        if(ro || taskRight) {
            right = true;
            left = false;
            collision.step(Vec2.of(5, 0));
            walking = true;
        }
        if(lo && ro) {
            left = true;
            right = false;
            walking = false;
        }
        if(ro || lo) {
            taskLeft = taskRight = false;
            handle.goal = null;
        }

        if(InputMap.isOn(LwjglKey.key("space")) && collision.d) {
            jump();
        }
        jmpTime--;
        if(jmpTime > 0) {
            collision.step(speedProcess);
        }

        actTime--;
        if(Mth.random() < 0.0024) {
            play(0);
        }
        if(actTime <= 0) {
            specialAct = -1;
        }

        handle.onTick(this);
        interArea.loc(bound().x(), bound().y());
        inv.tick();
        super.tickThen();
    }

    int jmpTime;

    private void jump()
    {
        jmpTime = 6;
        speedProcess.set(0, -10);
    }

    public void play(int i)
    {
        if(actTime <= 0) {
            specialAct = i;
            randoms[specialAct].reset();
            actTime = randoms[specialAct].getTimeACycle();//get a delay
        }
    }

    public void render(Graphics2D g, double x, double y, double w, double h)
    {
        g.setFlipX(right);
        if(walking) {
            g.draw(walk, x, y, w, h);
        }
        else {
            if(specialAct != -1 && actTime > 0) {
                g.draw(randoms[specialAct], x, y, w, h);
            }
            else {
                g.draw(stay, x, y, w, h);
            }
        }
        g.setFlipX(false);
        g.drawRect(new Color(1, 1, 1, 0.1), x, y, w, h);
    }

    public FesSerializer<Player> getSerializer()
    {
        return new PlayerSerializer();
    }

    public static class PlayerSerializer extends CommonSerializer<Player>
    {

        public void merge(CombinedFes fes, Player obj)
        {
            super.merge(fes, obj);
            for(int i = 0; i < obj.inv.size(); i++) {
                fes.put("inv_stack" + i, StackSerializer.toFes(obj.inv.get(i)));
            }
            fes.putDouble("x", obj.bound().x());
            fes.putDouble("y", obj.bound().y());
        }

        public Player take(CombinedFes fes, FixedWorld world, Room room)
        {
            Player player = new Player(world, room,
                              fes.getDouble("x"),
                              fes.getDouble("y"),
                              Load.loader.getTex("chr1_walk"),
                              Load.loader.getTex("chr1_stay"),
                              new AreaAnimated[] {
                                      new AreaAnimated(Load.loader.getTex("chr1_r1"), 4, 1, 0).perTime(5),
                                      new AreaAnimated(Load.loader.getTex("chr1_r2"), 4, 1, 0).perTime(8)
                              });
            for(int i = 0; i < player.inv.size(); i++)
            {
                player.inv.set(i, StackSerializer.fromFes(fes.getChild("inv_stack" + i)));
            }
            return player;
        }
    }

}
