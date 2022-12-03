package cm.glbackends.gdx;

import cm.milkywaygl.Milkyway;
import cm.milkywaygl.Platform;
import cm.milkywaygl.render.Batch;
import cm.milkywaygl.render.GLBase;
import cm.milkywaygl.render.State2D;
import cm.milkywaygl.render.Window;
import com.badlogic.gdx.Gdx;

import static com.badlogic.gdx.graphics.GL20.*;

public class GLGdxBase implements GLBase
{

    public double zoom, zoomedW, zoomedH;
    public double cornerX, cornerY;
    public double width, height;

    Batch blocking;

    State2D current = new State2D();
    State2D cache = new State2D();

    public double tru, trv;

    public void updateFrameData()
    {
        Window win = Milkyway.window;
        zoom = win.winHeight() / win.height();

        width = win.width();
        height = win.height();
        zoomedW = width * zoom;
        zoomedH = height * zoom;
        cornerX = (win.winWidth() - (zoomedW)) / 2;
        cornerY = 0;
    }

    public double width()
    {
        return width;
    }

    public double height()
    {
        return height;
    }

    public double calcMX(double x)
    {
        return (x - cornerX) / zoom;
    }

    public double calcMY(double y)
    {
        return (y - cornerY) / zoom;
    }

    public void clear()
    {
        Gdx.gl.glClearColor(0, 0, 0, 1f);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT | GL_STENCIL_BUFFER_BIT);
    }

    public void viewPort()
    {
        Gdx.gl.glViewport((int) (cornerX + tru), (int) (cornerY + trv), (int) zoomedW, (int) zoomedH);
    }

    public void translation(double x, double y)
    {
        tru = x;
        trv = y;
    }

    public void viewPortBack()
    {
        Window win = Milkyway.window;
        Gdx.gl.glViewport(0, 0, (int) win.winWidth(), (int) win.winHeight());
    }

    //BLOCKING

    //when your render method end, invoke this.
    public void freeAll()
    {
        ensure(null);
    }

    public Batch curBlocking()
    {
        return blocking;
    }

    //ensure the given batch is begun.
    //if given batch is null, it will not begin a new batch.
    public void ensure(Batch batch)
    {
        if(blocking != batch) {
            if(blocking != null) {
                blocking.end();
            }
            if(batch != null) {
                batch.begin();
            }
        }
    }

    public void begin(Batch batch)
    {
        if(blocking != null) {
            Platform.error("Cannot use multi renderer at same time.");
        }
        if(blocking == batch) {
            Platform.error("Cannot begin batches multi times.");
        }
        blocking = batch;
    }

    public void end(Batch batch)
    {
        if(blocking == null) {
            Platform.error("Cannot end batches multi times.");
        }
        if(blocking != batch) {
            Platform.error("Cannot use multi renderer at same time.");
        }
        blocking = null;
    }

    //STATEMENT

    public void save()
    {
        cache.copy(current);
    }

    public void read()
    {
        current.copy(cache);
    }

    public State2D curState()
    {
        return current;
    }

}
