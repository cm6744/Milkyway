package cm.milkywaygl.render;

import cm.milkywaygl.maths.Maths;
import cm.milkywaygl.render.inat.Context;
import cm.milkywaygl.render.wrapper.Color4;
import cm.milkywaygl.render.wrapper.Font2;
import cm.milkywaygl.resource.Path;
import cm.milkywaygl.util.IntBuffer;
import cm.milkywaygl.util.IntHolder;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;

public class GL2 implements GLObject
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

    public void begin()
    {
        gl.viewPort();
        gl.begin();
        drawer.begin();
    }

    public void end()
    {
        drawer.end();
        gl.end();
        gl.viewPortBack();
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

    //gl2 PRE AND END CALLERS

    public void draw(IntBuffer reg, double x, double y, double x2, double y2, double u, double v, double u2, double v2)
    {
        if(reg.value() == IntHolder.NULL) {
            return;
        }
        draw(_natTex(reg), x, y, x2, y2, u, v, u2, v2);
    }

    //END REGION

    //DRAW CALLS

    private void draw(Texture txt, double x, double y, double x2, double y2, double u, double v, double u2, double v2)
    {
        float r = drawer.getColor().r;
        float g = drawer.getColor().g;
        float b = drawer.getColor().b;
        float a = drawer.getColor().a;
        if(gl.mutable.alpha != 1) {
            if(gl.mutable.dye != null) {
                Color cl = gl.mutable.dye._nativeColor;
                drawer.setColor(cl.r, cl.g, cl.b, a * (float) gl.mutable.alpha);
            }
            else {
                drawer.setColor(r, g, b, a * (float) gl.mutable.alpha);
            }
        }
        //If !enableZoom, xy should not be in width/height, but actualWidth/actualHeight.
        //If enableZoom, xy should be in width/height, not actualWidth/actualHeight.It will be scaled soon.
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

    public void drawText(String text, double x, double y, boolean center)
    {
        double xin = x, yin = y;
        //Allow smoothing to font native
        Font2 f2 = gl.mutable.fontNow;
        BitmapFont ft = f2._nativeFont;
        BitmapFont.BitmapFontData bb = ft.getData();
        if(center) {
            double yPro = 0;
            for(int i = 0; i < text.length(); i++) {
                BitmapFont.Glyph gl = bb.getGlyph(text.charAt(i));
                if(gl == null) continue;
                xin -= (gl.width) / 2.0;
                yPro += gl.height / 2.0;
            }
            yin -= (yPro / text.length());
        }
        ft.draw(drawer, text, (float) gl.calcX(xin), (float) gl.calcY(yin));
    }

}
