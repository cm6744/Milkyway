package cm.milkywaygl.render;

import cm.milkywaygl.interfac.GLBatch;
import cm.milkywaygl.maths.check.Box4;
import cm.milkywaygl.render.nativegl.Context;
import cm.milkywaygl.resource.Path;
import cm.milkywaygl.util.IntBuffer;
import cm.milkywaygl.util.IntHolder;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GL2 extends GLBatch
{

    GL gl;
    IntHolder<Texture> images = new IntHolder<>();
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
        return images.get(id.value());
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

    public void vertex(Texture txt, double x, double y, double x2, double y2, double u, double v, double u2, double v2)
    {
        GL.gl.ensure(this);
        float r = drawer.getColor().r;
        float g = drawer.getColor().g;
        float b = drawer.getColor().b;
        float a = drawer.getColor().a;
        drawer.setColor(r, g, b, a * (float) gl.mutable.alpha);
        double ny = gl.calcY(y2);
        double nx = gl.calcX(x);
        double w = gl.calcP(x2 - x);
        double h = gl.calcP(y2 - y);
        drawer.draw(txt, (float) (nx + (gl.mutable.mirror ? w : 0)), (float) (ny), (float) (w / 2), (float) (h / 2), (float) (gl.mutable.mirror ? -w : w), (float) (h), (float) 1, (float) 1,
                    //LIB GDX IMPL: COUNTER CLOCK WISE.
                    //IN PURE JAVA IT SHOULD BE POSITIVE.
                    (float) (-gl.mutable.rotation), (int) u, (int) v, (int) (u2 - u), (int) (v2 - v), false, false
        );
        drawer.setColor(r, g, b, a);
    }

    public void vertex(IntBuffer reg, double x, double y, double x2, double y2, double u, double v, double u2, double v2)
    {
        if(reg.value() == IntHolder.NULL) {
            return;
        }
        vertex(_natTex(reg), x, y, x2, y2, u, v, u2, v2);
    }

    //Not Native Methods

    public void dim(IntBuffer id, double x, double y, double w, double h, double u, double v, double uw, double vh)
    {
        vertex(id, x, y, x + w, y + h, u, v, u + uw, v + vh);
    }

    public void dim01(IntBuffer id, double x, double y, double w, double h, double u, double v, double uw, double vh)
    {
        double wi = texw(id);
        double hi = texh(id);
        dim(id, x, y, w, h, u * wi, v * hi, wi * uw, hi * vh);
    }

    public void dim(IntBuffer id, double x, double y, double w, double h)
    {
        dim(id, x, y, w, h, 0, 0, texw(id), texh(id));
    }

    public void dim(IntBuffer id, Box4 b)
    {
        dim(id, b.xc(), b.yc(), b.width(), b.height());
    }

    public void dim(IntBuffer id, Box4 b, Box4 uv)
    {
        dim(id, b.xc(), b.yc(), b.width(), b.height(), uv.xc(), uv.yc(), uv.width(), uv.height());
    }

}
