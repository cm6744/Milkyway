package cm.milkywayx.widgetx.widget.choice;

import cm.milkyway.Milkyway;
import cm.milkyway.opengl.input.Key;
import cm.milkyway.opengl.render.g2d.Text;
import cm.milkywayx.widgetx.base.RenderBuffer;
import cm.milkyway.lang.container.List;
import cm.milkyway.lang.container.Map;

public abstract class Choice extends RenderBuffer
{

    List<RenderBuffer> areas = new List<>();
    Map<RenderBuffer, Text> texts = new Map<>();
    int cur;
    double space;
    double logic;
    boolean isUpDown;
    boolean xyPositiveMove;
    boolean canMove;
    Key curPost = Milkyway.keys.key("left");
    Key curNeg = Milkyway.keys.key("right");

    public void toUpDownDirection()
    {
        curPost = Milkyway.keys.key("up");
        curNeg = Milkyway.keys.key("down");
        isUpDown = true;
    }

    public void toLeftRightDirection()
    {
        curPost = Milkyway.keys.key("left");
        curNeg = Milkyway.keys.key("right");
        isUpDown = false;
    }

    public void disableMove()
    {
        canMove = false;
    }

    public void setKey(Key l, Key r)
    {
        curPost = l;
        curNeg = r;
    }

    public void tickThen()
    {
        areas.iterate((o, i) -> {
            if(isMoving() && canMove) {
                if(isUpDown) {
                    o.box().mvLoc(0, xyPositiveMove ? vecInfo.speed() : -vecInfo.speed());
                }
                else {
                    o.box().mvLoc(xyPositiveMove ? vecInfo.speed() : -vecInfo.speed(), 0);
                }
            }
            o.tick();
        }, false);
        if(!isMoving()) {
            if(canMove) {
                correctPlace();
            }

            if(rightScroll()) {
                if(cur >= areas.last()) {
                    return;
                }
                logic = space;
                xyPositiveMove = false;
                cur++;
            }
            if(leftScroll()) {
                if(cur <= 0) {
                    return;
                }
                logic = space;
                xyPositiveMove = true;
                cur--;
            }
        }
        logic -= vecInfo.speed();
    }

    protected abstract boolean leftScroll();

    protected abstract boolean rightScroll();

    public void renderThen(double x, double y, double w, double h)
    {
        areas.iterate((o, i) -> {
            o.render();
            Text text = texts.get(o);
            if(text != null) {
                text.render(o.box().x(), o.box().yc2(), true);
            }
        }, false);
    }

    public void append(RenderBuffer buf, Text name)
    {
        if(isUpDown) {
            buf.box().loc(renderBox.x(), areas.size() * space + renderBox.y());
        }
        else {
            buf.box().loc(areas.size() * space + renderBox.x(), renderBox.y());
        }
        areas.add(buf);
        texts.put(buf, name);
    }

    public void setElementsSpace(double spc)
    {
        space = spc;
    }

    private void correctPlace()
    {
        areas.iterate((o, i) -> {
            if(isUpDown) {
                o.box().loc(renderBox.x(), (i - cur) * space + renderBox.y());
            }
            else {
                o.box().loc((i - cur) * space + renderBox.x(), renderBox.y());
            }
        }, false);
    }

    public int getCurrent()
    {
        return cur;
    }

    public boolean isMoving()
    {
        return logic > 0;
    }

}
