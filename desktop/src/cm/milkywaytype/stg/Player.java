package cm.milkywaytype.stg;

import cm.milkywaygl.maths.Maths;
import cm.milkywaygl.maths.check.Box4;
import cm.milkywaygl.input.InputMap;
import cm.milkywaygl.input.Key;
import cm.milkywaygl.render.GL;
import cm.milkywaygl.render.wrapper.AnimatedArea;
import cm.milkywaygl.render.wrapper.Area;
import cm.milkywaylib.buffers.Bounder;

public class Player extends Bounder
{

    public static final String STAY = "stay";
    public static final String MOVE = "move";

    double defaultSpeed;
    double shiftSpeed;
    //INITIAL, AFTER TAKING IN
    Bounder boundPoint;
    double sizeMax;
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

    public Player(double speed, double shiftSped, Bounder point, Box4 moveDim)
    {
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

        boundPoint.box4().loc(box4.x(), box4.y());
        boundPoint.effect().mvRotation(1);
    }

    public void renderThen(double x, double y, double w, double h)
    {
        if(dieTime <= 0 && (noBoundTime <= 0 || (noBoundTime % 5 != 0))) {
            Area moveTimeline = texture(MOVE);
            Area stayTimeline = texture(STAY);
            if(left) {
                GL.gl2.dim(moveTimeline, box4);
            }
            else if(right) {
                GL.gl.curState().mirrored(true);
                GL.gl2.dim(moveTimeline, box4);
            }
            else {
                GL.gl2.dim(stayTimeline, box4);
            }
            GL.gl.read();
            boundPoint.render();
            GL.gl.save();
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
