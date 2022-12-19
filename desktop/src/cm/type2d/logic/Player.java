package cm.type2d.logic;

import cm.milkyway.Milkyway;
import cm.milkyway.lang.maths.Mth;
import cm.milkyway.physics.d4simple.Body;
import cm.milkyway.physics.d4simple.ForceInfo;
import cm.milkyway.physics.d4simple.Gravity;
import cm.milkyway.physics.d4simple.World;
import cm.milkyway.physics.shapes.Rect;
import cm.milkyway.opengl.input.InputMap;
import cm.milkyway.opengl.render.g2d.AreaAnimated;
import cm.milkyway.opengl.render.g2d.BufferTex;
import cm.milkywayx.widgetx.base.RenderBuffer;
import cm.milkywayx.widgetx.widget.Bounder;

public class Player extends Bounder<Rect>
{

    Body inter;
    double ySpeed;
    boolean right;
    boolean left;
    boolean walking;
    AreaAnimated walk;
    AreaAnimated stay;
    AreaAnimated[] randoms;
    Gravity gravity = new Gravity(0.1);
    double uptime;
    World world;
    int specialAct = -1;
    int actTime;

    public Player(World w, BufferTex walki, BufferTex stayi, BufferTex[] rands)
    {
        world = w;
        walk = new AreaAnimated(walki, 4, 1, 0).perTime(5);
        stay = new AreaAnimated(stayi, 4, 1, 0).perTime(20);
        inter = new Body(bound);
        randoms = new AreaAnimated[rands.length];
        for(int i = 0; i < rands.length; i++) {
            randoms[i] = new AreaAnimated(rands[i], 4, 1, 0).perTime(5);
        }
    }

    protected void initBound()
    {
        allowBoundRotate = false;
        bound = new Rect();
        bound.loc(400, 300);
        bound.resize(64, 164);
        box().loc(400, 300);
        box().resize(80, 180);
    }

    ForceInfo cache = new ForceInfo();

    public void tickThen()
    {
        walking = false;
        boolean lo = InputMap.isOn(Milkyway.keys.key("a"));
        boolean ro = InputMap.isOn(Milkyway.keys.key("d"));
        if(lo) {
            right = false;
            left = true;
            inter.run(world, -4, 0, cache);
            walking = true;
        }
        if(ro) {
            right = true;
            left = false;
            inter.run(world, 4, 0, cache);
            walking = true;
        }
        if(lo && ro) {
            left = true;
            right = false;
            walking = false;
        }

        if(InputMap.isOn(Milkyway.keys.key("space")) && cache.has(ForceInfo.States.ON_LAND)) {
            ySpeed = -15;
        }
        if(cache.has(ForceInfo.States.REACH_TOP)) {
            ySpeed += 0.5;
        }

        cache = new ForceInfo();
        inter.run(world, 0, ySpeed, cache);

        if(ySpeed <= 10) {
            ySpeed += 0.75;
        }

        box().loc(bound.x(), bound.y());

        actTime--;
        if(Mth.random() < 0.0024) {
            specialAct = Mth.randomInt(0, randoms.length);
            actTime = randoms[specialAct].getTimeACycle();
        }
        if(actTime <= 0) {
            specialAct = -1;
        }
    }

    public void render()
    {
        Milkyway.glBase.state().mirrored(right);
        if(walking) {
            Milkyway.gl2d.dim(walk, box());
        }
        else {
            if(specialAct != -1 && actTime > 0) {
                Milkyway.gl2d.dim(randoms[specialAct], box());
            }
            else {
                Milkyway.gl2d.dim(stay, box());
            }
        }
        Milkyway.glBase.state().clear();
    }

}
