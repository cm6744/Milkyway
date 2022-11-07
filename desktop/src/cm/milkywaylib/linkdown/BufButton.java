package cm.milkywaylib.linkdown;

import cm.milkywaygl.render.GL;
import cm.milkywaygl.render.nnat.InputMap;
import cm.milkywaygl.render.wrapper.Keys;
import cm.milkywaylib.linklib.RenderBuffer;

public class BufButton extends RenderBuffer
{

    int countDown;
    String text;

    public void renderThen()
    {
        double x = box4.xc();
        double y = box4.yc();
        double w = box4.width();
        double h = box4.height();

        countDown--;

        if(isInDownState()) {
            GL.gl2.dim01(texture, x, y, w, h, 0, 2 / 3.0, 1, 1 / 3.0);
        }
        else if(hangOn()) {
            GL.gl2.dim01(texture, x, y, w, h, 0, 1 / 3.0, 1, 1 / 3.0);
        }
        else {
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
        return hangOn() && InputMap.mouseClick(Keys.M_LEFT);
    }

    public void append(String txt)
    {
        text = txt;
    }

}
