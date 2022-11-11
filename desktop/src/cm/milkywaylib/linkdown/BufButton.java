package cm.milkywaylib.linkdown;

import cm.milkywaygl.render.GL;
import cm.milkywaygl.input.InputMap;
import cm.milkywaygl.input.Key;
import cm.milkywaylib.linklib.RenderBuffer;

//Needs texture format:
//---------
public class BufButton extends RenderBuffer
{

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
            //line 3
            GL.gl2.dim01(texture, x, y, w, h, 0, 2 / 3.0, 1, 1 / 3.0);
        }
        else if(hangOn()) {
            //line 2
            GL.gl2.dim01(texture, x, y, w, h, 0, 1 / 3.0, 1, 1 / 3.0);
        }
        else {
            //line 1
            GL.gl2.dim01(texture, x, y, w, h, 0, 0, 1, 1 / 3.0);
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
