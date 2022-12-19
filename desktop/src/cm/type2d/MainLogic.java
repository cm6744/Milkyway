package cm.type2d;

import cm.milkyway.Milkyway;
import cm.milkyway.lang.maths.Mth;
import cm.milkyway.opengl.input.InputMap;
import cm.milkyway.physics.shapes.Rect;
import cm.milkyway.opengl.render.g2d.Area;
import cm.milkyway.opengl.render.g2d.BufferTex;
import cm.milkyway.opengl.render.g2d.Color4;
import cm.milkywayx.particlex.ParticleMap;
import cm.milkywayx.widgetx.base.Scene;
import cm.milkywayx.lightx.EnvironmentalLight;
import cm.milkywayx.lightx.LightMap;
import cm.milkywayx.lightx.SpotLight;
import cm.type2d.hud.Huds;
import cm.type2d.item.PlayerInv;
import cm.type2d.item.RegisterItem;
import cm.type2d.logic.Player;
import cm.type2d.logic.Seasons;
import cm.type2d.logic.Times;
import cm.type2d.res.Load;

public class MainLogic extends Scene
{

    Area tex;
    RollMap rollMap;
    Color4 color4;
    LightMap map;
    ParticleMap pmap;
    SpotLight spotLight1;
    Player player;
    double light = 8;
    BufferTex bg;
    Huds huds;
    EnvironmentalLight env;

    public MainLogic()
    {
        huds = new Huds(BufferTex.directLoad("textures/gui/hud.png"));
        bg = Milkyway.graphics.newTex();
        Milkyway.gl2d.loadTexture(bg, "textures/bgs/bg1.png");
        Rect r = new Rect();
        r.resize(640, 480);
        r.loc(320, 240);
        rollMap = new RollMap(bg, r);
        BufferTex textu = Milkyway.graphics.newTex();
        Milkyway.gl2d.loadTexture(textu, "textures/misc/shade.png");
        map = new LightMap(textu, light, light * 4);
        spotLight1 = new SpotLight();
        spotLight1.setIntensity(150);
        spotLight1.pos().loc(320, 240);
        map.add(spotLight1);
        env = new EnvironmentalLight();
        env.setIntensity(0.0);
        map.add(env);
        pmap = new ParticleMap(r);
        player = new Player(world.physic,
                            Load.loader.getTex("chr1_walk"),
                            Load.loader.getTex("chr1_stay"),
                            new BufferTex[] {
                                    Load.loader.getTex("chr1_r1")
        });
    }

    public void add(Area t) {
        tex = t;
    }

    public void bg(Color4 c) {
        color4 = c;
    }

    public void tickThen()
    {
        if(InputMap.isOn(Milkyway.keys.key("ctrl"))) {
            for(int i = 0; i < 39; i++) {
                runLogic();
            }
        }
        runLogic();
    }

    void runLogic()
    {
        Times.upTime();

        if(time() == 0) {
            PlayerInv.GLOBAL.add(RegisterItem.get("bronze").aStack(23));
            PlayerInv.GLOBAL.add(RegisterItem.get("electrum").aStack(48));
            PlayerInv.GLOBAL.add(RegisterItem.get("electrum").aStack(23));
        }
        pmap.tick();
        double d = Times.time;
        if(d >= 1 && d <= 54) {
            env.smoothTo(1);
        }
        else if((d > 54 && d <= 60) || (d >= 0 && d < 1) || (d >= 117)) {
            env.smoothTo(0.8);
        }
        else if(d > 60) {
            env.smoothTo(0);
        }
        env.updateSmooth();
        spotLight1.setIntensity(120);
        spotLight1.pos().loc(320, 300);
        huds.tick();
    }

    WorldRenderer world = new WorldRenderer();

    public void render()
    {
        Milkyway.glBase.state().dye(Seasons.getDye());
        Milkyway.gl2d.dim(bg, 0, 0, 800, 600);
        pmap.render();
        player.tick();
        player.render();
        world.render();
        map.render();
        huds.render();
    }

}
