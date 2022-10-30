package cm.milkywaylib.linkdown.stg;

import cm.milkywaygl.maths.Maths;
import cm.milkywaygl.maths.check.Box4;
import cm.milkywaygl.render.nnat.InputMap;
import cm.milkywaygl.render.wrapper.Keys;
import cm.milkywaylib.linkdown.AnimatedRenderer;
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

    public BufPlayer(double speed, double shift, BufBound point, AnimatedRenderer stay, AnimatedRenderer move, Box4 moveDim)
    {
        stayTimeline = stay;
        moveTimeline = move;
        defaultSpeed = speed;
        shiftSpeed = shift;
        boundPoint = point;
        sizeMax = point.box4().width();
        moveDimension = moveDim;
    }

    public void tick()
    {
        super.tick();

        up = InputMap.keyOn(Keys.UP);
        down = InputMap.keyOn(Keys.DOWN);
        left = InputMap.keyOn(Keys.LEFT);
        right = InputMap.keyOn(Keys.RIGHT);
        shift = InputMap.keyOn(Keys.SHIFT);

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
    }

    public void implRender()
    {
        if(left) {
            moveTimeline.render(box4, false);
        }
        else if(right) {
            moveTimeline.render(box4, true);
        }
        else {
            stayTimeline.render(box4, false);
        }
    }

    public Box4 bound()
    {
        return boundPoint.bound();
    }

}
