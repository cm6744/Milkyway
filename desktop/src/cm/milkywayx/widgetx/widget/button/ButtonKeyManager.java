package cm.milkywayx.widgetx.widget.button;

import cm.milkyway.lang.container.list.List;
import cm.milkyway.opengl.render.graphics.Graphics2D;
import cm.milkywayx.widgetx.Renderable2D;
import cm.milkywayx.widgetx.Tickable;

public class ButtonKeyManager implements Renderable2D, Tickable
{

    List<ButtonKey> buttons = new List<>();
    int cur;

    public void tick()
    {
        buttons.iterate((o, i) -> {
            o.tick();
            o.forceHang(false);
        }, false);

        current().forceHang(true);
    }

    public void render(Graphics2D g)
    {
        buttons.iterate((o, i) -> o.render(g), false);
    }

    public void add(ButtonKey but)
    {
        buttons.add(but);
    }

    public void move(int mv)
    {
        cur += mv;
    }

    public void set(int i)
    {
        cur = i;
    }

    public ButtonKey current()
    {
        return buttons.get(cur);
    }

}
