package cm.milkywaygl.resource;

import cm.milkywaygl.GLRunnable;
import cm.milkywaygl.render.GL;
import cm.milkywaygl.render.wrapper.Color4;
import cm.milkywaygl.render.wrapper.Font2;
import cm.milkywaygl.render.wrapper.FontType;
import cm.milkywaygl.util.IntBuffer;
import cm.milkywaygl.util.ObjectBuffer;
import cm.milkywaygl.util.container.Queue;

public class AssetsLoading
{

    double progress;
    double total;
    double run;
    boolean done;
    Queue<GLRunnable> tasks = new Queue<>();

    public void loadSpecial(GLRunnable run)
    {
        tasks.offer(run);
        total++;
    }

    public void loadTex(IntBuffer buf, String path)
    {
        loadSpecial(() -> buf.setValue(GL.gl2.loadTexture(path).value()));
    }

    public void update()
    {
        GLRunnable r = tasks.take();
        if(r == null) {
            done = true;
            progress = 1;
            return;
        }
        r.run();
        run++;
        progress = run / total;
    }

    public double progress()
    {
        return progress;
    }

    public boolean isDone()
    {
        return done;
    }

}
