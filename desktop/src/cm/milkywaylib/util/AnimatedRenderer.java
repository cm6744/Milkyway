package cm.milkywaylib.util;

import cm.milkywaygl.maths.Maths;
import cm.milkywaygl.maths.check.Box4;
import cm.milkywaygl.render.GL;
import cm.milkywaygl.util.IntBuffer;

//A row line render image cutter
public class AnimatedRenderer
{

    IntBuffer texture;
    int timer;
    int timeRoom;
    int maxIndex;
    int index;
    //CUTS
    int tileXNum;
    int tileYNum;
    int tileW;
    int tileH;
    int chosenLine;

    public AnimatedRenderer(IntBuffer tex, int rows, int lines, int lineIndex)
    {
        texture = tex;
        tileW = Maths.toInt(GL.gl2.texw(texture) / rows);
        tileH = Maths.toInt(GL.gl2.texh(texture) / lines);
        tileXNum = rows;
        tileYNum = lines;
        chosenLine = lineIndex;
        maxIndex = tileXNum;
    }

    public AnimatedRenderer perTime(int time)
    {
        timeRoom = time;
        return this;
    }

    public void tick()
    {
        timer++;
        if(timer % timeRoom == 0) {
            index++;
            if(index >= maxIndex) {
                index = 0;
            }
        }
    }

    public void render(double x, double y, double w, double h, boolean mirror)
    {
        GL.gl.cacheState();
        if(mirror) {
            GL.gl.curState().mirrored(true);
        }
        GL.gl2.dim(texture, x, y, w, h, index * tileW, chosenLine * tileH, tileW, tileH);
        GL.gl.readState();
    }

    public void render(Box4 box4, boolean mirror)
    {
        render(box4.xc(), box4.yc(), box4.width(), box4.height(), mirror);
    }

}
