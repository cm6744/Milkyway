package cm.milkywayx.widgetx.widget.button;

import cm.milkyway.Milkyway;
import cm.milkyway.opengl.input.Key;
import cm.milkyway.opengl.render.g2d.Text;
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

    public void renderThen(double x, double y, double w, double h)
    {
        countDown--;

        if(isInDownState()) {
            Milkyway.gl2d.dim(texture(DOWN), renderBox);
        }
        else if(hangOn()) {
            Milkyway.gl2d.dim(texture(HANG), renderBox);
        }
        else {
            Milkyway.gl2d.dim(texture(UP), renderBox);
        }

        if(text != null) {
            text.render(renderBox.x(), renderBox.y(), true);
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
