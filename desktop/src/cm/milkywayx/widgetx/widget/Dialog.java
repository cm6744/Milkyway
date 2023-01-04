package cm.milkywayx.widgetx.widget;

import cm.milkyway.opengl.input.InputMap;
import cm.milkyway.opengl.input.Key;
import cm.milkyway.opengl.render.g2d.Color;
import cm.milkyway.opengl.render.g2d.Font;
import cm.milkyway.opengl.render.g2d.Text;
import cm.milkyway.opengl.render.graphics.Graphics2D;
import cm.milkyway.tipy.Tipy;
import cm.milkywayx.widgetx.base.RenderBuffer;
import cm.milkyway.lang.container.list.List;

public class Dialog extends RenderBuffer
{

    public static final String V_TEXT = "text";

    List<Text> text = new List<>();
    Key key;

    boolean disposed = false;

    public Dialog(Key exit)
    {
        key = exit;
    }

    public void tickThen()
    {
        super.tickThen();

        if(InputMap.isClick(key)) {
            disposed = true;
        }
    }

    public void renderThen(Graphics2D g, double x, double y, double w, double h)
    {
        if(disposed) {
            return;
        }

        if(texture() != null) {
            g.draw(texture(), renderBox);
        }

        if(text != null) {
            text.iterate((o, i) -> {
                g.draw(o,
                        renderBox.xc() + renderBox.w() * 0.2,
                        renderBox.yc() + renderBox.h() * 0.2 + i * text.get(i).size(),
                        false
                );
            }, false);
        }
    }

    public void append(Text txt)
    {
        text.add(txt);
    }

    public void from(Font font2, Color color, Tipy txt)
    {
        txt.toMap().iterate((o, i) -> append(Text.create(font2, color, o.value().getString(V_TEXT))), false);
    }

}
