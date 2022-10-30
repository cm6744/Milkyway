package cm.milkywaylib.linklib;

import cm.milkywaygl.maths.check.Box4;
import cm.milkywaygl.maths.check.Effect;
import cm.milkywaygl.maths.check.Vec2;
import cm.milkywaygl.render.GL;
import cm.milkywaygl.util.container.List;

public class ChildBuffer2D extends RenderBuffer
{

    protected List<RenderBuffer> child = new List<>();

    public void addChild(RenderBuffer buf)
    {
        buf.box4().mvLoc(box4.xc(), box4.yc());
        child.add(buf);
    }

    public List<RenderBuffer> childList()
    {
        return child;
    }

    public void tick()
    {
        super.tick();

        for(int i = 0; i < child.size(); i++) {
            RenderBuffer buffer = child.get(i);
            if(buffer != null) {
                buffer.tick();
            }
        }
    }

    public void render()
    {
        super.render();

        for(int i = 0; i < child.size(); i++) {
            RenderBuffer buffer = child.get(i);
            if(buffer != null) {
                buffer.render();
            }
        }
    }

}
