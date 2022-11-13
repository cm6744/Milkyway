package cm.milkywaygl.render.wrapper;

import cm.milkywaygl.Platform;
import cm.milkywaygl.maths.check.Box4;
import cm.milkywaygl.render.GL;
import cm.milkywaygl.util.IntBuffer;

//A row line render image cutter
public class AnimatedArea extends Area
{

    //do not change its values
    //mustn't immediately link object when copying!!!
    Area[] texture;
    int timer;
    int timeRoom;
    int maxIndex;
    int index;
    //these too

    public AnimatedArea(IntBuffer tex, int rows, int lines, int lineIndex)
    {
        maxIndex = rows;
        texture = new Area[maxIndex];
        for(int i = 0; i < maxIndex; i++) {
            Area area = Area.dim01(tex, 1.0 / rows * i, 1.0 / lines * lineIndex, 1.0 / rows, 1.0 / lines);
            texture[i] = area;
        }
    }

    public AnimatedArea()
    {
    }

    public AnimatedArea perTime(int time)
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

    public void renderVertex(double x1, double y1, double x2, double y2)
    {
        texture[index].renderVertex(x1, y1, x2, y2);
    }

    public Area copy()
    {
        AnimatedArea ani = new AnimatedArea();
        ani.timeRoom = timeRoom;
        ani.index = index;
        ani.maxIndex = maxIndex;
        ani.texture = texture;
        ani.timer = timer;
        return ani;
    }

}
