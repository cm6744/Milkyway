package cm.milkywaygl.render;

import cm.milkywaygl.render.wrapper.Font2;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
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

    public void cut(String text, double x, double y, double start, double end, boolean center)
    {
        GL.gl.ensure(gl2);
        double xin = x, yin = y;
        //Allow smoothing to font native
        Font2 f2 = gl.current.fontNow;
        BitmapFont ft = f2._nativeFont;
        BitmapFont.BitmapFontData bb = ft.getData();
        if(center) {
            for(int i = 0; i < text.length(); i++) {
                BitmapFont.Glyph gl = bb.getGlyph(text.charAt(i));
                if(gl == null) {
                    continue;
                }
                //get all chars' width, adding space(u)
                xin -= (gl.width + gl.u) / 2.0;
            }
            //teleport capLine
            yin -= bb.capHeight / 2;
        }
        ft.draw(gl2.drawer, text, (float) gl.calcX(xin), (float) gl.calcY(yin), (int) start, (int) end, (float) gl.zoomedW, Align.left, true);
    }

    public void lines(String text, double x, double y, double max)
    {
        GL.gl.ensure(gl2);
        Font2 f2 = gl.current.fontNow;
        BitmapFont ft = f2._nativeFont;
        ft.draw(gl2.drawer, text, (float) gl.calcX(x), (float) gl.calcY(y),
                0, text.length(), (float) max, Align.left, true
        );
    }

    //Not Native Methods

    public void text(String text, double x, double y, boolean center)
    {
        cut(text, x, y, 0, text.length(), center);
    }

}
