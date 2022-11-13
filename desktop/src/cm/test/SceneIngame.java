package cm.test;

import cm.milkywaygl.maths.Maths;
import cm.milkywaygl.maths.RandomMap;
import cm.milkywaygl.maths.check.Box4;
import cm.milkywaygl.render.GL;
import cm.milkywaygl.input.InputMap;
import cm.milkywaygl.TaskCaller;
import cm.milkywaygl.render.wrapper.Area;
import cm.milkywaygl.render.wrapper.Color4;
import cm.milkywaygl.input.Key;
import cm.milkywaygl.sound.ClipPlayer;
import cm.milkywaygl.text.Data;
import cm.milkywaygl.text.JsonFile;
import cm.milkywaygl.util.container.List;
import cm.milkywaylib.buffers.Dialog;
import cm.milkywaylib.buffers.ParticleAuto;
import cm.milkywaytype.stg.*;
import cm.milkywaygl.render.wrapper.AnimatedArea;
import cm.milkywaylib.base.RenderBuffer;
import cm.milkywaylib.buffers.Bounder;

public class SceneIngame extends SceneSTG
{

    List<RenderBuffer> b2d = new List<>();

    Box4 scr = Box4.offset(170, 20, 460, 560);
    Bounder point = new Bounder();
    Player player;
    RandomMap map = new RandomMap(1, 2, 3, 4);
    Bullets shoot = new Bullets(this);
    Act1 act1 = new Act1();
    Bullet c1;
    Dialog dialog;
    Enemy e1;
    Enemies addEne = new Enemies(this);

    public void init()
    {
        super.init();
        Bullet.TEXTURE = Assets.bullet1;
        Bullet c = new Bullet();
        c.box4().setSize(24, 24);
        c.box4().loc(400, 200);
        c.vec2().vec(1, time * 5);
        c.bound().setSize(5, 5);
        c.retype(0);
        c.dye(map.rand());
        c1 = c;
        e1 = new Enemy();
        e1.setTexture(new AnimatedArea(Assets.enemy, 4, 2, 0).perTime(15), Enemy.STAY);
        e1.setTexture(new AnimatedArea(Assets.enemy, 4, 2, 1).perTime(15), Enemy.MOVE);
        e1.setMaxHealth(100);
        e1.cure();
        e1.box4().setSize(48, 48);
        e1.bound().setSize(60, 60);
        bullets.addGroup("default");
        enemies.addGroup("default");
        point.box4().setSize(14, 14);
        point.setTexture(Area.create(Assets.point));
        point.bound().setSize(6, 6);
        player = new Player(5.5, 2, point, Box4.offset(170, 20, 460, 560));
        player.setTexture(new AnimatedArea(Assets.player1, 4, 2, 0).perTime(5), Player.STAY);
        player.setTexture(new AnimatedArea(Assets.player1, 4, 2, 1).perTime(5), Player.MOVE);
        player.box4().setSize(42, 54);
        player.box4().loc(380, 420);
        player.vec2().vec(0, -90);
        Action.scene = this;
        Action.bullets = shoot;
        Action.player = player;
        dialog = new Dialog();
        dialog.setColor(Color4.SHADOW);
        dialog.box4().loc(400, 500);
        dialog.box4().setSize(500, 100);
        dialog.from(JsonFile.load("texts/dialog01.json"));
        JsonFile keys = JsonFile.load("keys.json");
        player.keyBind(
                Key.key(keys.entry("right").toString()),
                Key.key(keys.entry("left").toString()),
                Key.key(keys.entry("up").toString()),
                Key.key(keys.entry("down").toString()),
                Key.key(keys.entry("mode").toString())
        );
    }

    public void tickThen()
    {
        if(time() % 100 == 0) {
            addEne.add(e1, null, "default", scr.xc(), scr.y(), 2, 0);
        }
        dialog.tick();
        Main.performed.tick();
        for(int i = 0; i < 1; i++) {
            if(time % 10 != 0) {
                break;
            }
            c1.dye(map.rand());
            c1.retype(1);
            shoot.ring(c1, act1, "default", 400, 100, 36, 50, Maths.random(0.5, 3), Maths.random(0, 90));
            ///shoot.ring(c1, null, "default", 400, 200, 12, 50, 2,
            //        VMaths.degreeBetweenAB(player.box4().x(), player.box4().y(), 400, 200));
        }
        if(TaskCaller.time % 500 == 0) {
            ParticleAuto cp = new ParticleAuto();
            particles.buf().add(cp);
            cp.setTexture(Area.create(Assets.moon));
            cp.box4().setSize(0, 0);
            cp.box4().loc(scr.x(), scr.y() - 82);
            cp.size(1);
            cp.opacity(-0.001);
            cp.rotate(5);
        }
        List<Bullet> bullet = bullets.getGroup("default").buf();
        List<Enemy> enemy = enemies.getGroup("default").buf();
        for(int e = enemy.last(); e >= 0; e--) {
            Enemy ene = enemy.get(e);
            ene.tick();
            if(!ene.box4().boundAsRct(scr) || ene.isForRemove()) {
                enemy.remove(e);
            }
            for(int i = bullet.last(); i >= 0; i--) {
                Bullet r = bullet.get(i);
                if(r != null) {
                    if(e == 0) {
                        r.tick();
                    }
                    if(!r.box4().boundAsRct(scr) || r.isForRemove()) {
                        bullet.remove(i);
                        bulletPool.reuse(r);
                    }
                    if(ene.action() != null) {
                        ene.action().buf = ene;
                        ene.action().tickWith(r);
                    }
                    if(ene.bound().boundAsCir(r.bound())) {
                        ene.beShot(0);
                    }
                    if(player.hasBound() && player.bound().boundAsCir(r.bound())) {
                        player.beShot();
                        ClipPlayer.play("sounds/effect/player_die.wav");
                    }
                }
            }
        }
        particles.buf().iterate((o, i) -> {
            o.tick();
            if(o.isForRemove()) {
                particles.buf().remove(i);
            }
        }, true);

        player.tick();

        if(InputMap.isOn(Key.key("ctrl"))) {
            TaskCaller.setDefaultFps(1000, 60);
        }
        else {
            TaskCaller.setDefaultFps(Main.defFps, 60);
        }
    }

    public void render()
    {
        //GL.gl3.cameraLookPosTranslate(-0.1, 0, 0);
        if(time() >= 600) {
            Main.performed.sped = 5;
        }
        GL.gl.clear();

        GL.gl2.dim(Assets.stg6bg, 170 - 40, 20, 460 + 80, 560);
        for(int i = b2d.last(); i >= 0; i--) {
            RenderBuffer r = b2d.get(i);
            if(r != null) {
                r.render();
            }
        }

        Main.performed.render();

        enemies.render();
        player.render();
        bullets.render();

        particles.buf().iterate((o, i) -> {
            o.render();
        }, true);

        GL.gl.curState().font(Assets.ch);
        dialog.render();

        GL.gl2.dim(Assets.overlay1, 0, 0, 800, 600);

        //player.render();
        GL.gl.curState().font(Assets.en);
        GL.gl.curState().color(Color4.GOLD);
        GL.gl2f.text("Bullet Count: " + Data.toString(bullets.getGroup("default").buf().size()), 20, 570, false);
        GL.gl2f.text(Data.toString(TaskCaller.nowFpsRender), 200, 570, false);
        GL.gl2f.text(Data.toString(TaskCaller.nowFpsUpdate), 300, 570, false);
        //GL.gl2.vertex(Assets.character, 50, 30, 170, 170);
        GL.gl.freeAll();
    }

}

