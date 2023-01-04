package cm.milkywayx.lightx;

import cm.milkyway.lang.container.list.List;
import cm.milkyway.lang.maths.Mth;
import cm.milkyway.opengl.render.g2d.Tex;
import cm.milkyway.opengl.render.graphics.Graphics2D;

public class LightMap
{

    List<Light> lights = new List<>();
    Tex tex;
    double blockSize;
    double renderSize;

    public LightMap(Tex shade, double block, double render)
    {
        tex = shade;
        blockSize = block;
        renderSize = render;
    }

    public void add(Light spot)
    {
        lights.add(spot);
    }

    public void remove(Light spot)
    {
        lights.remove(spot);
    }

    public void clear()
    {
        lights.clear();
    }
    
    public void render(Graphics2D g)
    {
        double w = g.getContext().width();
        double h = g.getContext().height();
        
        for(double i = -blockSize; i < w + blockSize; i += blockSize) {
            for(double j = -blockSize; j < h + blockSize; j += blockSize) {
                double cx = i + blockSize / 2;
                double cy = j + blockSize / 2;
                double intensity = 0;
                for(int k = lights.last(); k >= 0; k--) {
                    Light light = lights.get(k);
                    double lm = light.luminous(cx, cy);
                    intensity += Mth.max(Mth.min(lm, 1), 0);
                    if(light.isRemoved()) {
                        lights.remove(k);
                    }
                }
                intensity = Mth.min(1, intensity);
                intensity = Mth.max(0, intensity);
                g.setOpacity(1 - intensity);
                g.draw(tex, i - renderSize / 2, j - renderSize / 2, renderSize, renderSize);
            }
        }

        g.setOpacity(1);
    }

}
