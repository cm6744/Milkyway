package cm.milkywaygl.render;

import cm.milkywaygl.inter.GLBatch;
import cm.milkywaygl.maths.check.Box4;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class GL4 extends GLBatch
{

    GL gl;
    ShapeRenderer renderer;

    public GL4(GL g)
    {
        gl = g;
    }

    public void init()
    {
        renderer = new ShapeRenderer();
        renderer.setAutoShapeType(true);
    }

    public void dispose()
    {
        renderer.dispose();
    }

    protected void nbegin()
    {
        renderer.begin(ShapeRenderer.ShapeType.Filled);
    }

    protected void nend()
    {
        //blend, it.
        Gdx.graphics.getGL20().glEnable(GL20.GL_BLEND);
        renderer.end();
        Gdx.graphics.getGL20().glDisable(GL20.GL_BLEND);
    }

    public void vertex(double x, double y, double x2, double y2)
    {
        GL.gl.ensure(this);
        float r = gl.mutable.colorNow._nativeColor.r;
        float g = gl.mutable.colorNow._nativeColor.g;
        float b = gl.mutable.colorNow._nativeColor.b;
        float a = gl.mutable.colorNow._nativeColor.a;
        renderer.setColor(r, g, b, a * (float) gl.mutable.alpha);
        renderer.rect((float) gl.calcX(x), (float) gl.calcY(y2), (float) gl.calcP(x2 - x), (float) gl.calcP(y2 - y));
    }

    public void dim(double x, double y, double w, double h)
    {
        vertex(x, y, w + x, y + h);
    }

    public void dim(Box4 box4)
    {
        dim(box4.xc(), box4.yc(), box4.width(), box4.height());
    }

}
