package cm.milkywayx.widgetx.widget.button;

import cm.milkyway.opengl.input.Key;
import cm.milkyway.opengl.render.g2d.Text;
import cm.milkyway.opengl.render.graphics.Graphics2D;
import cm.milkywayx.widgetx.base.RenderBuffer;

//Needs texture format:
//---------
public abstract class Button extends RenderBuffer
{

    public static final String DOWN = "down";
    public static final String UP = "up";
    public static final String HANG = "hang";

    int countDown;
    Text text;
    Key key;

    public void setTypeKey(Key code)
    {
        key = code;
    }

    public void renderThen(Graphics2D g, double x, double y, double w, double h)
    {
        countDown--;

        if(isInDownState()) {
            g.draw(texture(DOWN), renderBox);
        }
        else if(hangOn()) {
            g.draw(texture(HANG), renderBox);
        }
        else {
            g.draw(texture(UP), renderBox);
        }

        if(text != null) {
            g.draw(text, renderBox.x(), renderBox.y(), true);
        }
    }

    public void append(Text txt)
    {
        text = txt;
    }

    public void callDown(int downTime)
    {
        countDown = downTime;
    }

    public boolean isInDownState()
    {
        return countDown > 0;
    }

    public abstract boolean hangOn();

    public abstract boolean clickOn();

}
