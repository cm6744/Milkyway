package cm.milkywaylib.util;

import cm.milkywaygl.interfac.GLRenderable;
import cm.milkywaygl.util.container.Map;

public class BufferQueue<E extends GLRenderable> implements GLRenderable
{

    Map<String, BufferGroup<E>> groups = new Map<>();

    public void addGroup(String name)
    {
        groups.put(name, new BufferGroup<>());
    }

    public BufferGroup<E> getGroup(String name)
    {
        return groups.get(name);
    }

    public void render()
    {
        groups.iterate((obj, index) -> {
            obj.render();
        }, true);
    }

    public void tick()
    {
        groups.iterate((obj, index) -> {
            obj.tick();
        }, true);
    }

}
