package cm.typestg.test;

import cm.backends.lwjgl.LwjglKey;
import cm.backends.lwjgl.LwjglSoundDevice;
import cm.backends.tipy.DefaultTipyReader;
import cm.milkyway.lang.io.AccessLocal;
import cm.milkyway.opengl.input.InputMap;
import cm.milkyway.opengl.render.g2d.*;
import cm.milkyway.opengl.audio.SoundDevice;
import cm.milkyway.opengl.render.g2d.AreaAnimated;
import cm.milkyway.opengl.render.g2d.AreaColored;
import cm.milkyway.opengl.render.g2d.AreaStatic;
import cm.milkyway.opengl.render.graphics.Graphics2D;
import cm.milkyway.tipy.Tipy;
import cm.milkywayx.widgetx.base.RenderBuffer;
import cm.milkywayx.widgetx.widget.ProgressLine;
import cm.milkywayx.widgetx.widget.choice.RectBound;
import cm.milkywayx.particlex.Particle;
import cm.milkyway.lang.container.Iterator;
import cm.milkyway.lang.container.list.List;
import cm.milkyway.lang.maths.Mth;
import cm.milkyway.lang.maths.shapes.Rect;
import cm.milkyway.lang.text.Data;
import cm.typestg.*;
import cm.typestg.act.Action;
import cm.typestg.act.ActionEnemyCms;

import static cm.typestg.Scr.scr;

public class SceneIngame extends SceneSTG
{

    List<RenderBuffer> b2d = new List<>();

    Rect blOut = new Rect();
    RectBound point = new RectBound(false);
    Player player;
    Shooter shoot = new Shooter(this);
    Action<Enemy> act1 = new ActionEnemyCms("scripts/e001.cls");
    Boss b1;
    EnemyCreator addEne = new EnemyCreator(this);
    boolean pause;
    ScenePause scenePause;
    public Dropping dp1;
    int score;
    ProgressLine bossBar = new ProgressLine();
    Roller roller = new SzyiRoller();

    public void init()
    {
        super.init();
        roller.overlay(Assets.loader.getTex("stg8bgovl"));
        roller.rolling(Assets.loader.getTex("stg8bg"), 2);

        blOut.loc(320, 240);
        blOut.resize(500, 560);
        scenePause = new ScenePause(this);

        b1 = new Boss();
        b1.setTexture(new AreaAnimated(Assets.loader.getTex("boss1"), 4, 2, 0).perTime(5), Enemy.STAY);
        b1.setTexture(new AreaAnimated(Assets.loader.getTex("boss1"), 4, 2, 1).perTime(5), Enemy.MOVE);
        b1.setMaxHealth(700);
        b1.cure();
        BossLogic logic = new BossLogic();
        logic.mvDimY1 = 80;
        logic.mvDimY2 = 110;
        logic.mvDimX1 = scr.xc() + 50;
        logic.mvDimX2 = scr.xc2() - 50;
        logic.moveByPlayer = true;
        logic.add(new ActionEnemyCms("scripts/an001.cls"));
        logic.add(new ActionEnemyCms("scripts/an002.cls"));
        logic.add(new ActionEnemyCms("scripts/an003.cls"));
        b1.setLogic(logic);
        b1.box().resize(64, 84);
        b1.bound().resize(74, 96);

        bossBar.box().loc(scr.x(), scr.yc() + 10);
        bossBar.box().resize(scr.w() - 25, 5);
        bossBar.setTexture(AreaColored.create(Color.C0000), ProgressLine.EMPTY);
        bossBar.setTexture(AreaColored.create(Color.C1111), ProgressLine.FULL);

        point.box().resize(12, 12);
        point.setTexture(AreaStatic.create(Assets.loader.getTex("boundPoint")));
        point.bound().resize(1, 1);
        RenderBuffer magic = new RenderBuffer();
        magic.box().resize(96, 96);
        magic.setTexture(AreaStatic.create(Assets.loader.getTex("magicCir")));
        player = new Player(5, 2.25, 0.75, point, magic, scr);
        player.setTexture(new AreaAnimated(Assets.loader.getTex("pl1"), 4, 2, 0).perTime(5), Player.STAY);
        player.setTexture(new AreaAnimated(Assets.loader.getTex("pl1"), 4, 2, 1).perTime(5), Player.MOVE);
        player.box().resize(36, 46);
        player.box().loc(380, 420);
        player.vec().vec(0, -90);
        dp1 = new Dropping();
        dp1.box().resize(24, 24);
        dp1.goal(player.bound(), 84);
        dp1.bound().resize(20, 20);
        dp1.setTexture(AreaStatic.dim(Assets.loader.getTex("drops"), 112, 0, 16, 16));
        Share.scene = this;
        Share.shooter = shoot;
        Share.player = player;
        Share.enemyCreator = addEne;
        Tipy keys = new DefaultTipyReader().read(new AccessLocal("keys.json"));
        player.keyBind(
                LwjglKey.key(keys.getValue("right").getString()),
                LwjglKey.key(keys.getValue("left").getString()),
                LwjglKey.key(keys.getValue("up").getString()),
                LwjglKey.key(keys.getValue("down").getString()),
                LwjglKey.key(keys.getValue("mode").getString())
        );

        SoundDevice device = new LwjglSoundDevice();
        device.start();
        device.loop(Assets.loader.getSound("stg6"));
    }

    Iterator<Particle> ptick = (o, i) -> {
        o.tick();
        if(o.isForRemove()) {
            particles.remove(i);
            particlePool.reuse(o);
        }
    };

    public static Particle MORISABomb = new Particle();

    public void bomb()
    {
        MORISABomb = new Particle();
        MORISABomb.aliveTime(400);
        MORISABomb.box().resize(512, 512);
        MORISABomb.easeIn(25);
        MORISABomb.easeOut(50);
        particles.add(MORISABomb);
        MORISABomb.setTexture(AreaStatic.create(Assets.loader.getTex("pl1b")));
    }

    public void tickThen()
    {
        if(enemies.contains(b1)) {
            bossBar.value(b1.health() / b1.maxHealth());
        }

        if(InputMap.isOn(LwjglKey.key("ctrl"))) {
            //Eventbus.setDefaultFps(240, 60);
        }
        else {
            //Eventbus.setDefaultFps(Main.defFps, 60);
        }
        if(InputMap.isClick(LwjglKey.key("esc"))) {
            pause = !pause;
        }
        if(pause) {
            scenePause.tick();
            return;
        }
        if(time() <= 200) {
            if(time() % 120 == 0) {
                Enemy e1 = EnemyMap.enemies[0];
                addEne.add(e1, act1, null, scr.xc(), scr.y() + Mth.random(-200, -150), 1 + Mth.random(0.5, 1), Mth.random(-10, 10));
                addEne.add(e1, act1, null, scr.xc2(), scr.y() + Mth.random(-200, -150), 1 + Mth.random(0.5, 1), Mth.random(-190, -170));
            }
        }
        else if(time() == 250) {
            addEne.add(b1, null, scr.xc(), 0, 6, 78, scr.x(), 100);
        }
        else if(b1.isDead()) {
            Main.performed.sped = Mth.max(-25, Main.performed.sped - 0.1);

            if(Main.performed.sped <= -25) {
                Main.performed.newBoj = false;
            }
        }
        Main.performed.tick();

        Interact.run(dp1);
        particles.iterate(ptick, true);

        player.tick();
        if(InputMap.isOn(LwjglKey.key("z"))) {
            if(InputMap.isOn(LwjglKey.key("shift"))) {
                Bullet c1 = BulletMap.type_color_mat[101][7];
                if(time() % 2 == 0) {
                    shoot.add(c1, null, null, player.box().x(), player.box().yc() - 10, 15, -90 + Mth.random(-1, 1), Bullet.PLAYER, false);
                    shoot.add(c1, null, null, player.box().x() - 10, player.box().yc() - 10, 15, -90 + Mth.random(-1, 1), Bullet.PLAYER, false);
                    shoot.add(c1, null, null, player.box().x() + 10, player.box().yc() - 10, 15, -90 + Mth.random(-1, 1), Bullet.PLAYER, false);
                }
            }
            else {
                Bullet c1 = BulletMap.type_color_mat[100][7];
                if(time() % 4 == 0) {
                    shoot.add(c1, null, null, player.box().x(), player.box().yc() - 10, 12.5, -90, Bullet.PLAYER, false);
                    shoot.add(c1, null, null, player.box().x() - 10, player.box().yc() - 10, 12.5, -95, Bullet.PLAYER, false);
                    shoot.add(c1, null, null, player.box().x() + 10, player.box().yc() - 10, 12.5, -85, Bullet.PLAYER, false);
                    shoot.add(c1, null, null, player.box().x() - 20, player.box().yc() - 10, 12.5, -100, Bullet.PLAYER, false);
                    shoot.add(c1, null, null, player.box().x() + 20, player.box().yc() - 10, 12.5, -80, Bullet.PLAYER, false);
                }
                if(time() % 12 == 0) {
                    shoot.add(c1, null, null, player.box().x(), player.box().yc2(), 6.5, -90, Bullet.PLAYER, false);
                }
            }
        }
        if(bTime <= 0 && InputMap.isClick(LwjglKey.key("x"))) {
            bTime = 400;
            player.bomb(bTime);
            bomb();
        }
        bTime--;
        quake--;
        if(bTime > 0) {
            MORISABomb.box().loc(player.box().x(), player.box().y() - 264);
            shoot.clear(900, player.box().x(), player.box().y(), dp1);
            enemies.iterate((e, i) -> {
                Interact.hurt(e, this, 20);
            }, false);
        }

        if(enemies.contains(b1)) {
            roller.tick();
        }
    }

    int bTime;
    public int quake;

    Graphics2D g;
    Iterator<Enemy> eir = (o, i) -> o.render(g);
    Iterator<Bullet> bir = (o, i) -> o.render(g);
    Iterator<Particle> pir = (o, i) -> o.render(g);
    Iterator<Dropping> dir = (o, i) -> o.render(g);

    public void render(Graphics2D g)
    {
        g.getContext().clear();
        this.g = g;
        if(bTime > 0 || quake > 0) {
            double stn = Mth.min(bTime > 0 ? bTime : quake * 0.1, 16);
            //Milkyway.glBase.translation(Mth.random(-stn, stn), Mth.random(-stn, stn));
        }

        g.draw(Assets.loader.getTex("stg6bg"), 170 - 40, 20, 460 + 80, 560);
        for(int i = b2d.last(); i >= 0; i--) {
            RenderBuffer r = b2d.get(i);
            if(r != null) {
                r.render(g);
            }
        }

        Main.performed.render();

        if(enemies.contains(b1)) {
            roller.render(g);
        }

        enemies.iterate(eir, true);
        playerBullets.iterate(bir, true);
        player.render(g);
        droppings.iterate(dir, true);
        bullets.iterate(bir, true);
        player.renderPoint(g);

        particles.iterate(pir, true);

        if(enemies.contains(b1)) {
            bossBar.render(g);
        }

        g.draw(Assets.loader.getTex("overlay"), 0, 0, 640, 480);

        //player.render();
        Font f = Assets.loader.getFont("en_us");
        Color c = Color.C1111;
        g.drawText(f, c, "Bullet Count: " + Data.toString(bullets.size()), 20, 460, false);
        g.drawText(f, c, "RD: " + Data.toString(60), 200, 460, false);
        g.drawText(f, c, "TK: " + Data.toString(60), 300, 460, false);
        g.drawText(f, c, "Score: " + Data.toString(score), 540, 20, false);

        //Debug.renderDebug(Assets.loader.getFont("en_us"));

        g.getContext().paint();

        if(pause) {
            scenePause.render(g);
        }
    }

}

