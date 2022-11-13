package cm.milkywaylib.util;

import cm.milkywaygl.interfac.GLRenderable;
import cm.milkywaygl.util.container.List;

public class BufferGroup<E extends GLRenderable> implements GLRenderable
{

    List<E> buffers = new List<>();

    public List<E> buf()
    {
        return buffers;
    }

    public void render()
    {
        buffers.iterate((obj, index) -> {
            obj.render();
        }, true);
    }

    public void tick()
    {
        buffers.iterate((obj, index) -> {
            obj.tick();
        }, true);
    }

}
