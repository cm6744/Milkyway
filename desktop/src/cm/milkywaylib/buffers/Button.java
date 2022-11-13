package cm.milkywaylib.buffers;

import cm.milkywaygl.render.GL;
import cm.milkywaygl.input.InputMap;
import cm.milkywaygl.input.Key;
import cm.milkywaylib.base.RenderBuffer;

//Needs texture format:
//---------
public class Button extends RenderBuffer
{

    public static final String DOWN = "down";
    public static final String UP = "up";
    public static final String HANG = "hang";

    int countDown;
    String text;
    Key key = Key.mouseLeft();

    public void setTypeKey(Key code)
    {
        key = code;
    }

    public void renderThen(double x, double y, double w, double h)
    {
        countDown--;

        if(isInDownState()) {
            GL.gl2.dim(texture(DOWN), box4);
        }
        else if(hangOn()) {
            GL.gl2.dim(texture(HANG), box4);
        }
        else {
            GL.gl2.dim(texture(UP), box4);
        }

        if(text != null) {
            GL.gl2f.text(text, box4.x(), box4.y(), true);
        }
    }

    public void callDown(int downTime)
    {
        countDown = downTime;
    }

    public boolean isInDownState()
    {
        return countDown > 0;
    }

    public boolean hangOn()
    {
        double mx = InputMap.x();
        double my = InputMap.y();
        return box4.boundPoint(mx, my);
    }

    public boolean clickOn()
    {
        return hangOn() && InputMap.isClick(key);
    }

    public void append(String txt)
    {
        text = txt;
    }

}
