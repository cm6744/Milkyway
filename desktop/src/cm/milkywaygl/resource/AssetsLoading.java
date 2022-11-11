package cm.milkywaygl.resource;

import cm.milkywaygl.interfac.GLTickable;
import cm.milkywaygl.Platform;
import cm.milkywaygl.render.GL;
import cm.milkywaygl.util.IntBuffer;
import cm.milkywaygl.util.container.Queue;

public class AssetsLoading
{

    double progress;
    double total;
    double run;
    boolean done;
    Queue<GLTickable> tasks = new Queue<>();

    public void loadSpecial(GLTickable run)
    {
        tasks.offer(run);
        total++;
    }

    public void loadSpecialImmediately(GLTickable run)
    {
        run.tick();
    }

    public void loadTex(IntBuffer buf, String path)
    {
        //Lambda usage.
        loadSpecial(() -> loadTexImmediately(buf, path));
    }

    public void loadTexImmediately(IntBuffer buf, String path)
    {
        buf.setValue(GL.gl2.loadTexture(path).value());
    }

    public void update()
    {
        GLTickable r = tasks.take();
        if(r == null) {
            done = true;
            progress = 1;
            return;
        }
        r.tick();
        Platform.log("Asset loaded. Remain: " + (total - run));
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
