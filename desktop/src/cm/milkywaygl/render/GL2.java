package cm.milkywaygl.render;

import cm.milkywaygl.interfac.GLBatch;
import cm.milkywaygl.maths.check.Box4;
import cm.milkywaygl.render.nativegl.Context;
import cm.milkywaygl.render.wrapper.Area;
import cm.milkywaygl.resource.Path;
import cm.milkywaygl.util.IntBuffer;
import cm.milkywaygl.util.IndexCache;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GL2 extends GLBatch
{

    GL gl;
    IndexCache<Texture> images = new IndexCache<>();
    SpriteBatch drawer;
    OrthographicCamera camera;

    public GL2(GL g)
    {
        gl = g;
    }

    public void init()
    {
        drawer = new SpriteBatch();
        drawer.enableBlending();
        camera = new OrthographicCamera((float) Context.width(), (float) Context.height());
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
        drawer.getProjectionMatrix().set(camera.combined);
    }

    public void dispose()
    {
        drawer.dispose();
        for(int i = 0; i < images.size(); i++) {
            images.get(i).dispose();
        }
    }

    protected void nbegin()
    {
        drawer.begin();
    }

    protected void nend()
    {
        drawer.end();
    }

    public IntBuffer loadTexture(String path)
    {
        FileHandle gf = Path._libJar(path);
        return loadTexture(new Texture(gf));
    }

    private IntBuffer loadTexture(Texture t)
    {
        //Allow smooth to texture native
        t.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        return images.gen(t);
    }

    public Texture _natTex(IntBuffer id)
    {
        return images.get(id);
    }

    public double texw(IntBuffer id)
    {
        return _natTex(id).getWidth();
    }

    public double texh(IntBuffer id)
    {
        return _natTex(id).getHeight();
    }

    /////////////////****///////////////////

    public void vertex(IntBuffer buf, double x, double y, double x2, double y2, double u, double v, double u2, double v2)
    {
        GL.gl.ensure(this);
        float r = drawer.getColor().r;
        float g = drawer.getColor().g;
        float b = drawer.getColor().b;
        float a = drawer.getColor().a;
        drawer.setColor(r, g, b, a * (float) gl.current.alpha);
        double ny = gl.calcY(y2);
        double nx = gl.calcX(x);
        double w = gl.calcP(x2 - x);
        double h = gl.calcP(y2 - y);
        drawer.draw(_natTex(buf),
                    (float) (nx + (gl.current.mirror ? w : 0)),
                    (float) (ny),
                    (float) (w / 2), (float) (h / 2),
                    (float) (gl.current.mirror ? -w : w),
                    (float) (h), (float) 1, (float) 1,
                    //LIB GDX IMPL: COUNTER CLOCK WISE.
                    //IN PURE JAVA IT SHOULD BE POSITIVE.
                    (float) (-gl.current.rotation),
                    (int) u, (int) v,
                    (int) (u2 - u), (int) (v2 - v),
                    false, false
        );
        drawer.setColor(r, g, b, a);
    }

    public void dim(IntBuffer buf, double x, double y, double w, double h, double u, double v, double uw, double vh)
    {
        vertex(buf, x, y, x + w, y + h, u, v, u + uw, v + vh);
    }

    public void dim(IntBuffer buf, double x, double y, double w, double h)
    {
        dim(buf, x, y, w, h, 0, 0, texw(buf), texh(buf));
    }

    //Area renderer

    public void vertex(Area area, double x, double y, double x2, double y2)
    {
        //invoke area.renderVertex.
        //This is designed for oop
        area.renderVertex(x, y, x2, y2);
    }

    public void dim(Area area, double x, double y, double w, double h)
    {
        vertex(area, x, y, x + w, y + h);
    }

    public void dim(Area area, Box4 box4)
    {
        dim(area, box4.xc(), box4.yc(), box4.width(), box4.height());
    }

}
