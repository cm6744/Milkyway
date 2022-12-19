package cm.backends.gdx;

import cm.milkyway.Milkyway;
import cm.backends.BasePath;
import cm.backends.gdx.g2d.BufferTexGdx;
import cm.milkyway.opengl.render.GL2D;
import cm.milkyway.opengl.render.State2D;
import cm.milkyway.opengl.render.Window;
import cm.milkyway.opengl.render.g2d.Area;
import cm.milkyway.opengl.render.g2d.BufferTex;
import cm.milkyway.physics.shapes.Rect;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GLGdx2D implements GL2D
{

    GLGdxBase gl;
    SpriteBatch drawer;
    OrthographicCamera camera;
    boolean smooth;

    public GLGdx2D(GLGdxBase g)
    {
        gl = g;
    }

    public void init()
    {
        drawer = new SpriteBatch();
        drawer.enableBlending();
        Window win = Milkyway.window;
        camera = new OrthographicCamera((float) win.width(), (float) win.height());
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
        drawer.getProjectionMatrix().set(camera.combined);
    }

    public void dispose()
    {
        drawer.dispose();
    }

    public void nbegin()
    {
        drawer.begin();
    }

    public void nend()
    {
        drawer.end();
    }

    public void setSmooth(boolean smt)
    {
        smooth = smt;
    }

    public void loadTexture(BufferTex tex, String path)
    {
        FileHandle gf = Gdx.files.absolute(BasePath.jar(path));
        ((BufferTexGdx) tex).set(loadTexture(new Texture(gf)));
    }

    private Texture loadTexture(Texture t)
    {
        //Allow smooth to texture native
        if(smooth) {
            t.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        }
        return t;
    }

    public double texw(BufferTex id)
    {
        return ((BufferTexGdx) id).nativeTex.getWidth();
    }

    public double texh(BufferTex id)
    {
        return ((BufferTexGdx) id).nativeTex.getHeight();
    }

    /////////////////****///////////////////

    public void vertex(BufferTex buf, double x, double y, double x2, double y2, double u, double v, double u2, double v2)
    {
        dim(buf, x, y, x2 - x, y2 - y, u, v, u2 - u, v2 - v);
    }

    public void dim(BufferTex buf, double x, double y, double w, double h, double u, double v, double uw, double vh)
    {
        Milkyway.glBase.ensure(this);
        Milkyway.glBase.viewPort();

        Color col = drawer.getColor();
        State2D sts = gl.current;
        float r = col.r;
        float g = col.g;
        float b = col.b;
        float a = col.a;
        drawer.setColor(r * (float) sts.apr, g * (float) sts.apg,
                        b * (float) sts.apb, a * (float) sts.alpha);
        drawer.draw(((BufferTexGdx) buf).nativeTex,
                    (float) (x + (sts.mirror ? w : 0)),
                    (float) (gl.height() - y - h),
                    (float) (w / 2), (float) (h / 2),
                    (float) (sts.mirror ? -w : w),
                    (float) (h), (float) 1, (float) 1,
                    //LIB GDX IMPL: COUNTER CLOCK WISE.
                    //IN PURE JAVA IT SHOULD BE POSITIVE.
                    (float) (-sts.rotation),
                    (int) u, (int) v,
                    (int) (uw), (int) (vh),
                    false, false
        );
        drawer.setColor(r, g, b, a);
    }

    public void dim(BufferTex buf, double x, double y, double w, double h)
    {
        dim(buf, x, y, w, h, 0, 0, texw(buf), texh(buf));
    }

    public void dim(Area area, double x, double y, double w, double h)
    {
        area.render(x, y, w, h);
    }

    public void dim(Area area, Rect rect)
    {
        dim(area, rect.xc(), rect.yc(), rect.w(), rect.h());
    }

    public void dim(BufferTex buf, Rect rect)
    {
        dim(buf, rect.xc(), rect.yc(), rect.w(), rect.h());
    }

    public void dim(BufferTex buf, Rect rect, double u, double v, double uw, double vh)
    {
        dim(buf, rect.xc(), rect.yc(), rect.w(), rect.h(), u, v, uw, vh);
    }

}