package cm.backends.lwjgl;

import cm.milkyway.lang.maths.shapes.Flat;
import cm.milkyway.opengl.render.Window;
import cm.milkyway.opengl.render.graphics.GraphicsContext;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class LwjglGraphicsContext implements GraphicsContext
{

    Flat SCR_BUFFER = Flat.normal();

    double zoom;
    double cornerX, cornerY;

    public double calcMX(double x)
    {
        return (x - cornerX) / zoom;
    }

    public double calcMY(double y)
    {
        return (y - cornerY) / zoom;
    }

    public void flushScreenBuffer()
    {
        Window win = getWindow();
        zoom = win.winHeight() / win.height();

        double width = win.width();
        double height = win.height();
        double zoomedW = width * zoom;
        double zoomedH = height * zoom;
        cornerX = (win.winWidth() - (zoomedW)) / 2;
        cornerY = 0;

        SCR_BUFFER.resize(zoomedW, zoomedH);
        SCR_BUFFER.loc(cornerX, cornerY);
    }

    public Window getWindow()
    {
        return LwjglWindow.instance;
    }

    public double width()
    {
        return getWindow().width();
    }

    public double height()
    {
        return getWindow().height();
    }

    public void clear()
    {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
    }

    public void paint()
    {
        LwjglBlockedQueue.ensure(null);
    }

    public void viewport(double x, double y, double w, double h)
    {
        Gdx.gl20.glViewport((int) x, (int) y, (int) w, (int) h);
    }

    public void viewportByDefault()
    {
        viewport(SCR_BUFFER.x(), SCR_BUFFER.y(), SCR_BUFFER.width(), SCR_BUFFER.height());
    }

    public Flat stretchDimension()
    {
        return SCR_BUFFER;
    }

}
