package cm.type2d.world;

import cm.backends.lwjgl.LwjglEnvironment;
import cm.backends.lwjgl.LwjglTexture;
import cm.milkyway.lang.io.AccessLocal;
import cm.milkyway.opengl.render.g2d.Tex;
import cm.milkyway.opengl.render.graphics.Graphics2D;
import cm.milkywayx.widgetx.base.VecInfo;
import cm.type2d.render.CtUtil;
import cm.type2d.world.time.Times;

public class WorldSky
{

    Tex m1, n1;
    Tex moon, sun;
    double[] moonPos = new double[2];
    double[] sunPos = new double[2];
    VecInfo sunVec = new VecInfo();
    VecInfo moonVec = new VecInfo();

    public WorldSky()
    {
        m1 = new LwjglTexture().load(new AccessLocal("textures/world/sky/sky_m1.png"));
        n1 = new LwjglTexture().load(new AccessLocal("textures/world/sky/sky_n1.png"));
        moon = new LwjglTexture().load(new AccessLocal("textures/world/sky/moon.png"));
        sun = new LwjglTexture().load(new AccessLocal("textures/world/sky/sun.png"));
    }

    private void round(double[] pos, VecInfo v, double offset)
    {
        double cx = LwjglEnvironment.context.width() / 2;
        double cy = LwjglEnvironment.context.height() * 0.75;
        v.vec(cx, Times.dayPer() * 360 + offset);
        pos[0] = v.applyX(cx);
        pos[1] = v.applyY(cy);
    }

    public void tick()
    {
        round(sunPos, sunVec, 180);
        round(moonPos, moonVec, 0);
    }

    public void render(Graphics2D g)
    {
        g.draw(n1, 0, 0, g.getContext().width(), g.getContext().height());
        CtUtil.ct(g, moon, moonPos[0], moonPos[1], 72, 72);
        CtUtil.ct(g, sun, sunPos[0], sunPos[1], 72, 72);
    }

}
