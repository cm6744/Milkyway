package cm.milkywaylib.base;

import cm.milkywaygl.maths.LimitValue;
import cm.milkywaygl.render.GL;
import cm.milkywaygl.render.nativegl.Context;
import cm.milkywaygl.render.wrapper.Color4;

public class Shadow
{

    double turnProgress;
    double turnChange;
    boolean turnIn;
    boolean turnOut;
    double turnSpeed;
    Scene next;

    public Shadow(double speed)
    {
        turnSpeed = speed;
    }

    public Shadow()
    {
        this(0.01);
    }

    public void turnIn(Scene scene)
    {
        turnIn = true;
        turnOut = false;
        next = scene;
    }

    public void turnOut()
    {
        turnIn = false;
        turnOut = true;
        turnProgress = 1;
    }

    public void tickTurning()
    {
        if(turnIn) {
            turnChange = turnSpeed;
            if(turnProgress >= 1) {
                turnIn = turnOut = false;
                turnChange = 0;
                turnProgress = 0;
                SceneManager.withoutTurning(next);
                next.shadow().turnOut();
            }
        }
        if(turnOut) {
            turnChange = -turnSpeed;
            if(turnProgress <= 0) {
                turnChange = 0;
                turnIn = turnOut = false;
            }
        }
        if(turnChange > 0) {
            turnProgress = LimitValue.ofDouble(turnProgress, turnChange, 1);
        }
        else {
            turnProgress = LimitValue.ofDouble(turnProgress, turnChange, 0);
        }
    }

    public void renderTurning()
    {
        GL.gl.save();

        GL.gl.curState().opacity(turnProgress);
        GL.gl.curState().color(Color4.BLACK);
        GL.gl4.dim(0, 0, Context.width(), Context.height());

        GL.gl.read();

        GL.gl.freeAll();
    }

}
