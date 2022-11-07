package cm.milkywaygl.render;

import cm.milkywaygl.maths.check.Box4;
import cm.milkywaygl.render.inat.Context;
import cm.milkywaygl.render.wrapper.Font2;
import cm.milkywaygl.resource.Path;
import cm.milkywaygl.util.IntBuffer;
import cm.milkywaygl.util.IntHolder;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Align;

public class GL2F
{

    GL gl;
    GL2 gl2;

    public GL2F(GL g, GL2 g2)
    {
        gl = g;
        gl2 = g2;
    }

    public void text(String text, double x, double y, double start, double end, boolean center)
    {
        GL.gl.ensure(gl2);
        double xin = x, yin = y;
        //Allow smoothing to font native
        Font2 f2 = gl.mutable.fontNow;
        BitmapFont ft = f2._nativeFont;
        BitmapFont.BitmapFontData bb = ft.getData();
        if(center) {
            double yPro = 0;
            for(int i = 0; i < text.length(); i++) {
                BitmapFont.Glyph gl = bb.getGlyph(text.charAt(i));
                if(gl == null) {
                    continue;
                }
                xin -= (gl.width) / 2.0;
                yPro = gl.height / 2.0;
            }
            yin -= (yPro / text.length());
        }
        ft.draw(gl2.drawer, text, (float) gl.calcX(xin), (float) gl.calcY(yin), (int) start, (int) end, (float) 0x5000f, Align.left, true);
    }

    public void textLines(String text, double x, double y, double max)
    {
        GL.gl.ensure(gl2);
        Font2 f2 = gl.mutable.fontNow;
        BitmapFont ft = f2._nativeFont;
        ft.draw(gl2.drawer, text, (float) gl.calcX(x), (float) gl.calcY(y),
                0, text.length(), (float) max, Align.left, true
        );
    }

    //Not Native Methods

    public void text(String text, double x, double y, boolean center)
    {
        text(text, x, y, 0, text.length(), center);
    }

}
