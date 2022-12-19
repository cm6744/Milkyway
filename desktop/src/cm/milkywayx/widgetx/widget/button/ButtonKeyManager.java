package cm.milkywayx.widgetx.widget.button;

import cm.milkyway.lang.container.List;

public class ButtonKeyManager
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

    public void render()
    {
        buttons.iterate((o, i) -> o.render(), false);
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
