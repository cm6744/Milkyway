package cm.milkyway.lang.maths;

public class ScreenMap
{

    float[] val;
    int w, h;

    public ScreenMap(int wi, int hi)
    {
        w = wi;
        h = hi;
        val = new float[w * h];
    }

    public void set(int px, int py, double v)
    {
        val[w * py + px] = (float) v;
    }

    public int getW()
    {
        return w;
    }

    public int getH()
    {
        return h;
    }

    public float[] getVal()
    {
        return val;
    }

}
