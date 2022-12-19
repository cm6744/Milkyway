package cm.typestg;

import cm.milkyway.Milkyway;
import cm.milkyway.opengl.input.InputMap;
import cm.milkyway.opengl.input.Key;
import cm.milkyway.opengl.render.g2d.Area;
import cm.milkywayx.widgetx.base.Effect;
import cm.milkywayx.widgetx.base.RenderBuffer;
import cm.milkywayx.widgetx.widget.Bounder;
import cm.milkyway.lang.maths.Mth;
import cm.milkyway.physics.shapes.Rect;

public class Player extends Bounder<Rect>
{

    public static final String STAY = "stay";
    public static final String MOVE = "move";

    double defaultSpeed;
    double shiftSpeed;
    double bombSpeed;
    //INITIAL, AFTER TAKING IN
    Bounder<Rect> boundPoint;
    RenderBuffer magic;
    double sizeMax;
    Rect moveDimension;

    boolean up;
    boolean down;
    boolean left;
    boolean right;
    boolean shift;

    int dieTime;
    int noBoundTime;
    int bombTime;

    Key kr;
    Key kl;
    Key ku;
    Key kd;
    Key ks;

    public Player(double speed, double shiftSped, double bombSped, Bounder<Rect> point, RenderBuffer mag, Rect moveDim)
    {
        defaultSpeed = speed;
        shiftSpeed = shiftSped;
        bombSpeed = bombSped;
        boundPoint = point;
        magic = mag;
        sizeMax = point.box().w();
        moveDimension = moveDim;
        keyBind(
                Milkyway.keys.key("right"),
                Milkyway.keys.key("left"),
                Milkyway.keys.key("up"),
                Milkyway.keys.key("down"),
                Milkyway.keys.key("shift")
        );
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
        bombTime--;

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

        if(bombTime > 0) {
            vecInfo.setSpeed(bombSpeed);
        }
        else {
            vecInfo.setSpeed(shift ? shiftSpeed : defaultSpeed);
        }
        vecInfo.setDegree(deg);

        renderBox.setX(Mth.min(renderBox.x(), moveDimension.xc2()));
        renderBox.setY(Mth.min(renderBox.y(), moveDimension.yc2()));
        renderBox.setX(Mth.max(renderBox.x(), moveDimension.xc()));
        renderBox.setY(Mth.max(renderBox.y(), moveDimension.yc()));

        if((!up && !down && !left && !right)) {
            vecInfo.setSpeed(0);
        }

        Rect b = boundPoint.box();
        double s;

        if(shift) {
            s = Mth.min(b.w() * 1.1 + 1, sizeMax);
        }
        else {
            s = Mth.max(b.w() / 1.2, 0);
        }
        b.resize(s, s);

        boundPoint.box().loc(renderBox.x(), renderBox.y());
        boundPoint.effect().mvRotation(1);
        magic.box().loc(renderBox.x(), renderBox.y());
        magic.effect().mvRotation(2.5);

        Effect ef = magic.effect();

        if(shift) {
            ef.mvOpacity(0.05);
            if(ef.opacity() > 0.5) {
                ef.setOpacity(0.5);
            }
        }
        else {
            ef.mvOpacity(-0.05);
            if(ef.opacity() < 0) {
                ef.setOpacity(0);
            }
        }
    }

    public void renderThen(double x, double y, double w, double h)
    {
        if(canRender()) {
            magic.render();
            Area moveTimeline = texture(MOVE);
            Area stayTimeline = texture(STAY);
            if(left) {
                Milkyway.gl2d.dim(moveTimeline, renderBox);
            }
            else if(right) {
                Milkyway.glBase.state().mirrored(true);
                Milkyway.gl2d.dim(moveTimeline, renderBox);
            }
            else {
                Milkyway.gl2d.dim(stayTimeline, renderBox);
            }
        }
    }

    public void renderPoint()
    {
        if(canRender()) {
            boundPoint.render();
        }
    }

    private boolean canRender()
    {
        return dieTime <= 0 && (noBoundTime <= 0 || (noBoundTime % 5 != 0));
    }

    protected void initBound()
    {
        bound = new Rect();
        allowBoundRotate = false;
    }

    public Rect bound()
    {
        return boundPoint.bound();
    }

    public void beShot()
    {
        dieTime = 20;
        noBoundTime = 300;
    }

    public void bomb(int time)
    {
        bombTime = time;
    }

    public boolean hasBound()
    {
        return noBoundTime <= 0;
    }

}
