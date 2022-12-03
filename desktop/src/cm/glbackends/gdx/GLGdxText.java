package cm.glbackends.gdx;

import cm.milkywaygl.Milkyway;
import cm.glbackends.gdx.g2d.Font2Gdx;
import cm.milkywaygl.render.GLText;
import cm.milkywaygl.render.g2d.Font2;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Align;

public class GLGdxText implements GLText
{

    GLGdxBase gl;
    GLGdx2D gl2;

    public GLGdxText(GLGdxBase g, GLGdx2D g2)
    {
        gl = g;
        gl2 = g2;
    }

    public void cut(String text, double x, double y, double start, double end, boolean center)
    {
        Milkyway.glBase.ensure(gl2);
        Milkyway.glBase.viewPort();

        double xin = x, yin = y;
        //Allow smoothing to font native
        Font2 f2 = gl.curState().fontNow;
        BitmapFont ft = ((Font2Gdx) f2)._nativeFont;
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
        ft.draw(gl2.drawer, text, (float) (xin), (float) (gl.height() - yin), (int) start, (int) end, (float) gl.zoomedW, Align.left, true);
    }

    public void text(String text, double x, double y, boolean center)
    {
        cut(text, x, y, 0, text.length(), center);
    }

}
