package cm.milkywaytype.stg;

import cm.milkywaygl.maths.Maths;
import cm.milkywaygl.maths.check.Box4;
import cm.milkywaygl.input.InputMap;
import cm.milkywaygl.input.Key;
import cm.milkywaylib.util.AnimatedRenderer;
import cm.milkywaylib.linkdown.BufBound;

public class BufPlayer extends BufBound
{

    double defaultSpeed;
    double shiftSpeed;
    //INITIAL, AFTER TAKING IN
    BufBound boundPoint;
    double sizeMax;
    AnimatedRenderer stayTimeline;
    AnimatedRenderer moveTimeline;
    Box4 moveDimension;

    boolean up;
    boolean down;
    boolean left;
    boolean right;
    boolean shift;

    int dieTime;
    int noBoundTime;

    Key kr = Key.key("right");
    Key kl = Key.key("left");
    Key ku = Key.key("up");
    Key kd = Key.key("down");
    Key ks = Key.key("shift");

    public BufPlayer(double speed, double shiftSped, BufBound point, AnimatedRenderer stay, AnimatedRenderer move, Box4 moveDim)
    {
        stayTimeline = stay;
        moveTimeline = move;
        defaultSpeed = speed;
        shiftSpeed = shiftSped;
        boundPoint = point;
        sizeMax = point.box4().width();
        moveDimension = moveDim;
    }

    public void keyBind(Key r, Key l, Key u, Key d, Key s)
    {
        kr = r;
        kl = l;
        ku = u;
        kd = d;
        ks = s;
    }

    public void tickThen()
    {
        super.tickThen();

        dieTime--;
        noBoundTime--;

        up = InputMap.isOn(ku);
        down = InputMap.isOn(kd);
        left = InputMap.isOn(kl);
        right = InputMap.isOn(kr);
        shift = InputMap.isOn(ks);

        double deg = -90;

        if(up && right) {
            deg = (315);
        }
        else if(up && left) {
            deg = (225);
        }
        else if(down && left) {
            deg = (135);
        }
        else if(down && right) {
            deg = (45);
        }
        else if(up) {
            deg = (270);
        }
        else if(left) {
            deg = (180);
        }
        else if(down) {
            deg = (90);
        }
        else if(right) {
            deg = (0);
        }

        vec2.setSpeed(shift ? shiftSpeed : defaultSpeed);
        vec2.setDegree(deg);

        box4.setX(Maths.min(box4.x(), moveDimension.xc2()));
        box4.setY(Maths.min(box4.y(), moveDimension.yc2()));
        box4.setX(Maths.max(box4.x(), moveDimension.xc()));
        box4.setY(Maths.max(box4.y(), moveDimension.yc()));

        if((!up && !down && !left && !right)) {
            vec2.setSpeed(0);
        }

        Box4 b = boundPoint.box4();
        double s;

        if(shift) {
            s = Maths.min(b.width() * 1.1 + 1, sizeMax);
        }
        else {
            s = Maths.max(b.width() / 1.2, 0);
        }
        b.setSize(s, s);

        stayTimeline.tick();
        moveTimeline.tick();

        boundPoint.box4().loc(box4.x(), box4.y());
    }

    public void renderThen(double x, double y, double w, double h)
    {
        if(dieTime <= 0 && (noBoundTime <= 0 || (noBoundTime % 5 != 0))) {
            if(left) {
                moveTimeline.render(box4, false);
            }
            else if(right) {
                moveTimeline.render(box4, true);
            }
            else {
                stayTimeline.render(box4, false);
            }
            boundPoint.render();
        }
    }

    public Box4 bound()
    {
        return boundPoint.bound();
    }

    public void beShot()
    {
        dieTime = 10;
        noBoundTime = 300;
    }

    public boolean hasBound()
    {
        return noBoundTime <= 0;
    }

}
