package cm.backends.lwjgl;

import cm.milkyway.opengl.render.Window;
import cm.milkyway.opengl.render.g2d.Tex;
import cm.milkyway.opengl.render.g2d.Color;
import cm.milkyway.opengl.render.g2d.Font;
import cm.milkyway.opengl.render.graphics.Graphics2D;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Align;

public class LwjglGraphics2D extends Graphics2D implements LwjglBlockedQueue.LwjglBatch
{

    SpriteBatch drawer;
    OrthographicCamera camera;
    LwjglShapeGraphics shapeOffer;

    public LwjglGraphics2D()
    {
        context = LwjglEnvironment.context;
        drawer = new SpriteBatch();
        drawer.enableBlending();
        Window win = context.getWindow();
        camera = new OrthographicCamera((float) win.width(), (float) win.height());
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
        camera.update();
        drawer.getProjectionMatrix().set(camera.combined);
        shapeOffer = new LwjglShapeGraphics();

        LwjglEnvironment.autoDispose(this);
    }

    public void nbegin()
    {
        ShaderProgram s = LwjglEnvironment.curSha;
        drawer.setShader(s);
        drawer.begin();
    }

    public void nend()
    {
        drawer.end();
    }

    public void dispose()
    {
        drawer.dispose();
    }

    public void draw(Tex tex, double x, double y, double w, double h, double u, double v, double uw, double vh)
    {
        LwjglBlockedQueue.ensure(this);
        ((LwjglGraphicsContext) context).viewportByDefault();

        com.badlogic.gdx.graphics.Color col = drawer.getColor();
        float r = col.r;
        float g = col.g;
        float b = col.b;
        float a = col.a;
        drawer.setColor((float) (filter[R] * r), (float) (filter[G] * g),
                              (float) (filter[B] * b), (float) (filter[A]) * a);
        drawer.draw(((LwjglTexture) tex).tex,
                    (float) (x),
                    (float) (context.height() - y - h),
                    (float) (w / 2), (float) (h / 2),
                    (float) (w),
                    (float) (h), (float) 1, (float) 1,
                    //LIB GDX IMPL: COUNTER CLOCK WISE.
                    //IN PURE JAVA IT SHOULD BE POSITIVE.
                    (float) (-rotation),
                    (int) u, (int) v,
                    (int) (uw), (int) (vh),
                    flipX, flipY
        );
        drawer.setColor(r, g, b, a);
    }

    public void draw(Tex tex, double x, double y, double w, double h)
    {
        draw(tex, x, y, w, h, 0, 0, tex.w(), tex.h());
    }

    public void draw(Tex tex, double x, double y)
    {
        draw(tex, x, y, tex.w(), tex.h());
    }

    public void drawRect(Color c, double x, double y, double w, double h)
    {
        shapeOffer.drawRect(c, x, y, w, h);
    }

    public void drawOval(Color c, double x, double y, double w, double h)
    {
        shapeOffer.drawOval(c, x, y, w, h);
    }

    public void drawLine(Color c, double x, double y, double xt, double yt)
    {
        shapeOffer.drawLine(c, x, y, xt, yt);
    }

    public void drawText(Font f, Color c, String s, double x, double y, boolean center)
    {
        LwjglBlockedQueue.ensure(this);
        ((LwjglGraphicsContext) context).viewportByDefault();

        com.badlogic.gdx.graphics.Color col = drawer.getColor();
        float r = col.r;
        float g = col.g;
        float b = col.b;
        float a = col.a;
        drawer.setColor((float) (filter[R] * r), (float) (filter[G] * g),
                        (float) (filter[B] * b), (float) (filter[A]) * a);
        double xin = x, yin = y;
        BitmapFont ft = ((LwjglFont) f).font;
        ft.setColor(drawer.getColor());
        BitmapFont.BitmapFontData bb = ft.getData();
        if(center) {
            for(int i = 0; i < s.length(); i++) {
                BitmapFont.Glyph gl = bb.getGlyph(s.charAt(i));
                if(gl == null) {
                    continue;
                }
                //get all chars' width, adding space(u)
                xin -= (gl.width + gl.u) / 2.0;
            }
            //teleport capLine
            yin -= bb.capHeight / 2;
        }
        ft.draw(drawer, s, (float) (xin), (float) (context.height() - yin), (float) context.width(), Align.left, true);
        drawer.setColor(r, g, b, a);
    }

    class LwjglShapeGraphics implements LwjglBlockedQueue.LwjglBatch
    {

        ShapeRenderer renderer;

        LwjglShapeGraphics()
        {
            context = LwjglEnvironment.context;
            renderer = new ShapeRenderer();
            renderer.setAutoShapeType(true);
            Window win = context.getWindow();
            camera = new OrthographicCamera((float) win.width(), (float) win.height());
            camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
            camera.update();
            renderer.getProjectionMatrix().set(camera.combined);

            LwjglEnvironment.autoDispose(this);
        }

        public void nbegin()
        {
            renderer.begin(ShapeRenderer.ShapeType.Filled);
        }

        public void nend()
        {
            Gdx.graphics.getGL20().glEnable(GL20.GL_BLEND);
            renderer.end();
            Gdx.graphics.getGL20().glDisable(GL20.GL_BLEND);
        }

        public void dispose()
        {
            renderer.dispose();
        }

        private void preset(Color c)
        {
            LwjglBlockedQueue.ensure(this);
            ((LwjglGraphicsContext) context).viewportByDefault();

            renderer.getColor().set((float) c.red(), (float) c.green(), (float) c.blue(), (float) c.alpha());
            renderer.getColor().mul((float) filter[R], (float) filter[G], (float) filter[B], (float) filter[A]);
        }

        public void drawRect(Color c, double x, double y, double w, double h)
        {
            preset(c);
            renderer.rect((float) (x), (float) (context.height() - (y + h)), (float) (w), (float) (h));
        }

        public void drawOval(Color c, double x, double y, double w, double h)
        {
            preset(c);
            renderer.ellipse((float) (x), (float) (context.height() - (y + h)), (float) (w), (float) (h));
        }

        public void drawLine(Color c, double x, double y, double xt, double yt)
        {
            preset(c);
            renderer.line((float) (x), (float) (context.height() - y), (float) (xt), (float) (context.height() - yt));
        }

    }

}
