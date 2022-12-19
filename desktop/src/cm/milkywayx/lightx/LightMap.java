package cm.milkywayx.lightx;

import cm.milkyway.Milkyway;
import cm.milkyway.lang.container.List;
import cm.milkyway.lang.maths.Mth;
import cm.milkyway.opengl.render.g2d.BufferTex;
import cm.milkyway.physics.d4simple.World;

public class LightMap
{

    List<Light> lights = new List<>();
    BufferTex tex;
    double blockSize;
    double renderSize;
    World world;

    public LightMap(BufferTex shade, double block, double render)
    {
        tex = shade;
        blockSize = block;
        renderSize = render;
    }

    public void add(Light spot)
    {
        lights.add(spot);
    }

    public void render()
    {
        double w = Milkyway.glBase.width();
        double h = Milkyway.glBase.height();

        for(double i = -blockSize; i < w + blockSize; i += blockSize) {
            for(double j = -blockSize; j < h + blockSize; j += blockSize) {
                double cx = i + blockSize / 2;
                double cy = j + blockSize / 2;
                double intensity = 1;
                for(int k = lights.last(); k >= 0; k--) {
                    Light light = lights.get(k);
                    intensity *= 1 - light.luminous(cx, cy);
                    if(light.isRemoved()) {
                        lights.remove(k);
                    }
                }
                intensity = Mth.min(1, intensity);
                intensity = Mth.max(0, intensity);
                Milkyway.glBase.state().opacity(intensity);
                Milkyway.gl2d.dim(tex, i - renderSize / 2, j - renderSize / 2, renderSize, renderSize);
            }
        }

        Milkyway.glBase.state().clear();
    }

}
