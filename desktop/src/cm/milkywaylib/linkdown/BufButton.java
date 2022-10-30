package cm.milkywaylib.linkdown;

import cm.milkywaygl.render.GL;
import cm.milkywaygl.render.nnat.InputMap;
import cm.milkywaygl.render.wrapper.Color4;
import cm.milkywaygl.render.wrapper.Font2;
import cm.milkywaygl.render.wrapper.Keys;
import cm.milkywaylib.linklib.RenderBuffer;

public class BufButton extends RenderBuffer
{

    int countDown;
    String text;
    Font2 font;
    Font2 shadow;
    int offset;

    public void font(Font2 f, Font2 sd, int shOffs)
    {
        font = f;
        shadow = sd;
        offset = shOffs;
    }

    public void implRender()
    {
        double x = box4.xc();
        double y = box4.yc();
        double w = box4.width();
        double h = box4.height();

        countDown--;

        if(isInDownState()) {
            GL.gl8.drawUVPer(texture, x, y, w, h, 0, 2 / 3.0, 1, 1 / 3.0);
        }
        else if(hangOn()) {
            GL.gl8.drawUVPer(texture, x, y, w, h, 0, 1 / 3.0, 1, 1 / 3.0);
        }
        else {
            GL.gl8.drawUVPer(texture, x, y, w, h, 0, 0, 1, 1 / 3.0);
        }

        if(text != null) {
            if(font != null) {
                GL.gl.font(font);
            }
            if(shadow != null) {
                GL.gl8.drawTextShadowed(text, box4.x(), box4.y(), offset, shadow, true);
            }
            else {
                GL.gl8.drawText(text, box4.x(), box4.y(), true);
            }
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
