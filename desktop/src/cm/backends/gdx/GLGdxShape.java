package cm.backends.gdx;

import cm.milkyway.Milkyway;
import cm.milkyway.opengl.render.GLShape;
import cm.milkyway.opengl.render.Window;
import cm.milkyway.physics.shapes.Rect;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class GLGdxShape implements GLShape
{

    GLGdxBase gl;
    ShapeRenderer renderer;
    OrthographicCamera camera;

    public GLGdxShape(GLGdxBase g)
    {
        gl = g;
    }

    public void init()
    {
        renderer = new ShapeRenderer();
        renderer.setAutoShapeType(true);
        Window win = Milkyway.window;
        camera = new OrthographicCamera((float) win.width(), (float) win.height());
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
        renderer.getProjectionMatrix().set(camera.combined);
    }

    public void dispose()
    {
        renderer.dispose();
    }

    public void nbegin()
    {
        renderer.begin(ShapeRenderer.ShapeType.Filled);
    }

    public void nend()
    {
        //blend, it
        Gdx.graphics.getGL20().glEnable(GL20.GL_BLEND);
        renderer.end();
        Gdx.graphics.getGL20().glDisable(GL20.GL_BLEND);
    }

    public void vertex(double x, double y, double x2, double y2)
    {
        Milkyway.glBase.ensure(this);
        Milkyway.glBase.viewPort();

        float r = (float) gl.current.r;
        float g = (float) gl.current.g;
        float b = (float) gl.current.b;
        float a = (float) gl.current.a;
        renderer.setColor(r, g, b, a * (float) gl.current.alpha);
        renderer.rect((float) (x), (float) (gl.height() - y2), (float) (x2 - x), (float) (y2 - y));
    }

    public void line(double x1, double y1, double x2, double y2)
    {
        Milkyway.glBase.ensure(this);
        Milkyway.glBase.viewPort();

        float r = (float) gl.current.r;
        float g = (float) gl.current.g;
        float b = (float) gl.current.b;
        float a = (float) gl.current.a;
        renderer.setColor(r, g, b, a * (float) gl.current.alpha);
        renderer.line((float) x1, (float) (gl.height() - y1), (float) x2, (float) (gl.height() - y2));
    }

    public void dim(double x, double y, double w, double h)
    {
        vertex(x, y, w + x, y + h);
    }

    public void dim(Rect rect)
    {
        dim(rect.xc(), rect.yc(), rect.w(), rect.h());
    }

}
