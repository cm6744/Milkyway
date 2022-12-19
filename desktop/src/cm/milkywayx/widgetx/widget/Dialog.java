package cm.milkywayx.widgetx.widget;

import cm.milkyway.Milkyway;
import cm.backends.gdx.json.JsonOGdx;
import cm.milkyway.opengl.input.InputMap;
import cm.milkyway.opengl.input.Key;
import cm.milkyway.opengl.render.g2d.Font2;
import cm.milkyway.opengl.render.g2d.Text;
import cm.milkywayx.widgetx.base.RenderBuffer;
import cm.milkyway.lang.container.List;

public class Dialog extends RenderBuffer
{

    public static final String V_TEXT = "text";

    List<Text> text = new List<>();
    Key key = Milkyway.keys.key("z");

    boolean disposed = false;

    public void setExitKey(Key code)
    {
        key = code;
    }

    public void tickThen()
    {
        super.tickThen();

        if(InputMap.isClick(key)) {
            disposed = true;
        }
    }

    public void renderThen(double x, double y, double w, double h)
    {
        if(disposed) {
            return;
        }

        if(texture() != null) {
            Milkyway.gl2d.dim(texture(), renderBox);
        }

        if(text != null) {
            text.iterate((o, i) -> {
                o.render(
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

    public void from(Font2 font2, JsonOGdx txt)
    {
        txt.toMap().iterate((o, i) -> append(Text.create(font2, o.entry(V_TEXT).asString())), false);
    }

}
