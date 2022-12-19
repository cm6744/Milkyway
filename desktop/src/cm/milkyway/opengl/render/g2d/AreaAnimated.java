package cm.milkyway.opengl.render.g2d;

//A row line render image cutter
public class AreaAnimated implements Area
{

    //do not change its values
    //mustn't immediately link object when copying!!!
    AreaStatic[] texture;
    int timer;
    int timeRoom;
    int maxIndex;
    int index;
    //these too

    public AreaAnimated(BufferTex tex, int rows, int lines, int lineIndex)
    {
        maxIndex = rows;
        texture = new AreaStatic[maxIndex];
        double ew = tex.w() / rows;
        double eh = tex.h() / lines;
        for(int i = 0; i < maxIndex; i++) {
            AreaStatic area = AreaStatic.dim(tex, i * ew, eh * lineIndex, ew, eh);
            texture[i] = area;
        }
    }

    private AreaAnimated()
    {
    }

    public AreaAnimated perTime(int time)
    {
        timeRoom = time;
        return this;
    }

    public void render(double x1, double y1, double w, double h)
    {
        //to next frame.
        timer++;
        if(timer % timeRoom == 0) {
            index++;
            if(index >= maxIndex) {
                index = 0;
            }
        }

        texture[index].render(x1, y1, w, h);
    }

    public int getTimeACycle()
    {
        return timeRoom * maxIndex;
    }

    public BufferTex texture()
    {
        return texture[0].texture();
    }

    public double fw()
    {
        return texture[0].fw();
    }

    public double fh()
    {
        return texture[0].fh();
    }

    public double w()
    {
        return texture[0].w();
    }

    public double h()
    {
        return texture[0].h();
    }

    public AreaAnimated copy()
    {
        AreaAnimated ani = new AreaAnimated();
        ani.timeRoom = timeRoom;
        ani.index = index;
        ani.maxIndex = maxIndex;
        ani.texture = texture;
        ani.timer = timer;
        return ani;
    }

}
