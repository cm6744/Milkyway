package cm.milkywayx.widgetx.scene;

import cm.milkyway.opengl.render.Window;
import cm.milkyway.opengl.render.g2d.Area;
import cm.milkyway.opengl.render.g2d.AreaColored;
import cm.milkyway.opengl.render.g2d.Color;
import cm.milkyway.opengl.render.graphics.Graphics2D;

public class Shadow
{

    public static Area DEFAULT_AREA = AreaColored.create(Color.C0001);

    double turnProgress;
    double turnChange;
    boolean turnIn;
    boolean turnOut;
    double turnSpeed;
    Scene next;
    Area area;

    public Shadow(double speed)
    {
        turnSpeed = speed;

        setArea(DEFAULT_AREA.copy());
    }

    public Shadow()
    {
        this(0.1);
    }

    public void setArea(Area a)
    {
        area = a;
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
        turnProgress += turnChange;
        if(turnProgress > 1) {
            turnProgress = 1;
        }
        if(turnProgress < 0) {
            turnProgress = 0;
        }
    }

    public void progress(double turn)
    {
        turnProgress = turn;
    }

    public void renderTurning(Graphics2D g)
    {
        Window win = g.getContext().getWindow();

        g.setOpacity(turnProgress);
        g.draw(area, 0, 0, win.width(), win.height());
        g.setOpacity(1);
    }

}
