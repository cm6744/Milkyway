package cm.test;

import cm.milkywaygl.inter.GLRenderable;
import cm.milkywaygl.maths.RandomMap;
import cm.milkywaygl.maths.check.Box4;
import cm.milkywaygl.render.GL;
import cm.milkywaygl.render.GL3Performed;
import cm.milkywaygl.render.nnat.InputMap;
import cm.milkywaygl.render.nnat.TaskCaller;
import cm.milkywaygl.render.wrapper.Color4;
import cm.milkywaygl.render.wrapper.Keys;
import cm.milkywaygl.sound.ClipPlayer;
import cm.milkywaygl.text.Data;
import cm.milkywaygl.text.I18n;
import cm.milkywaygl.text.JsonFile;
import cm.milkywaygl.util.container.List;
import cm.milkywaylib.linkdown.BufDialog;
import cm.milkywaylib.stg.*;
import cm.milkywaylib.util.AnimatedRenderer;
import cm.milkywaylib.linklib.RenderBuffer;
import cm.milkywaylib.linkdown.BufBound;

public class SceneMine extends SceneSTG
{

    List<RenderBuffer> b2d = new List<>();

    Box4 scr = Box4.offset(170, 20, 460, 560);
    BufBound point = new BufBound();
    BufPlayer player;
    GL3Performed gl3;
    RandomMap map = new RandomMap(3, 4);
    Bullets shoot = new Bullets(this);
    Act1 act1 = new Act1();
    BufBullet c1;
    BufDialog dialog;

    public void init()
    {
        super.init();
        BufBullet c = new BufBullet();
        c.pushTexture(Assets.bullet1);
        c.box4().setSize(24, 24);
        c.box4().loc(400, 200);
        c.vec2().vec(1, time * 5);
        c.bound().setSize(5, 5);
        c.retype(0);
        c.dye(map.rand());
        c1 = c;
        addGroup("default");
        point.box4().setSize(14, 14);
        point.pushTexture(Assets.point);
        point.bound().setSize(6, 6);
        player = new BufPlayer(5.5, 2, point, new AnimatedRenderer(Assets.player1, 4, 2, 0).perTime(5), new AnimatedRenderer(Assets.player1, 4, 2, 1).perTime(5), Box4.offset(170, 20, 460, 560));
        player.pushTexture(Assets.player1);
        player.box4().setSize(42, 54);
        player.box4().loc(380, 420);
        player.vec2().vec(0, -90);
        gl3 = new GL3Performed(GL.gl);
        gl3.init();
        Action.scene = this;
        Action.bullets = shoot;
        Action.player = player;
        dialog = new BufDialog();
        dialog.setColor(Color4.SHADOW);
        dialog.box4().loc(400, 500);
        dialog.box4().setSize(500, 100);
        dialog.from(JsonFile.load("texts/dialog01.json"));
    }

    public void tickThen()
    {
        dialog.tick();
        gl3.tick();
        for(int i = 0; i < 1; i++) {
            if(time % 3 != 0) {
                break;
            }
            c1.dye(map.rand());
            c1.retype(1);
            shoot.ring(c1, act1, "default", 400, 100, 12, 0, 2, 90);
            ///shoot.ring(c1, null, "default", 400, 200, 12, 50, 2,
            //        VMaths.degreeBetweenAB(player.box4().x(), player.box4().y(), 400, 200));
        }
        if(TaskCaller.time % 100 == 0) {
            RenderBuffer cp = new BufBound();
            b2d.add(cp);
            cp.pushTexture(Assets.moon);
            cp.box4().setSize(0, 120);
            cp.box4().loc(scr.x(), scr.y() - 82);
        }
        List<BufBullet> bullet = bullets.get("default");
        for(int i = bullet.last(); i >= 0; i--) {
            BufBullet r = bullet.get(i);
            if(r != null) {
                r.tick();
                if(!r.box4().boundAsRct(scr) || r.isForRemove()) {
                    bullet.remove(i);
                    bulletPool.reuse(r);
                }
                if(player.hasBound() && player.bound().boundAsCir(r.bound())) {
                    player.beShot();
                    ClipPlayer.play("sounds/effect/player_die.wav");
                }
            }
        }
        for(int i = b2d.last(); i >= 0; i--) {
            RenderBuffer r = b2d.get(i);
            if(r != null) {
                r.tick();
                r.effect().mvOpacity(-0.001);
                r.box4().mvSize(4, 4);
                if(r.effect().opacity() <= 0) {
                    b2d.remove(i);
                }
            }
        }
        player.tick();

        if(InputMap.keyOn(Keys.CTRL)) {
            TaskCaller.setDefaultFps(1000, 60);
        }
        else {
            TaskCaller.setDefaultFps(Main.defFps, 60);
        }
    }

    public void render()
    {
        //GL.gl3.cameraLookPosTranslate(-0.1, 0, 0);
        if(TaskCaller.time >= 600) {
            gl3.sped = 5;
        }
        GL.gl.clear();

        GL.gl2.dim(Assets.background2, 170 - 40, 20, 460 + 80, 560);
        for(int i = b2d.last(); i >= 0; i--) {
            RenderBuffer r = b2d.get(i);
            if(r != null) {
                r.render();
            }
        }

        gl3.render();

        player.render();
        List<BufBullet> bullet = bullets.get("default");
        for(int i = bullet.last(); i >= 0; i--) {
            GLRenderable r = bullet.get(i);
            if(r != null) {
                r.render();
            }
        }

        GL.gl.curState().font(Assets.ch);
        dialog.render();

        GL.gl2.dim(Assets.overlay1, 0, 0, 800, 600);

        //player.render();
        GL.gl.curState().font(Assets.en);
        GL.gl.curState().color(Color4.GOLD);
        GL.gl2f.text("Bullet Count: " + Data.toString(bullet.size()), 20, 570, false);
        GL.gl2f.text(Data.toString(TaskCaller.nowFpsRender), 200, 570, false);
        GL.gl2f.text(Data.toString(TaskCaller.nowFpsUpdate), 300, 570, false);
        //GL.gl2.vertex(Assets.character, 50, 30, 170, 170);
        GL.gl.freeAll();
    }

}

