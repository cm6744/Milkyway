package cm.milkywaylib.linklib;

import cm.milkywaygl.util.container.List;

public class ChildBuffer3D extends ModelBuffer
{

    protected List<ModelBuffer> child = new List<>();

    public void addChild(ModelBuffer buf)
    {
        child.add(buf);
    }

    public List<ModelBuffer> childList()
    {
        return child;
    }

    public void tick()
    {
        super.tick();

        for(int i = 0; i < child.size(); i++) {
            ModelBuffer buffer = child.get(i);
            if(buffer != null) {
                buffer.tick();
            }
        }
    }

    public void render()
    {
        super.render();

        for(int i = 0; i < child.size(); i++) {
            ModelBuffer buffer = child.get(i);
            if(buffer != null) {
                buffer.render();
            }
        }
    }

}
