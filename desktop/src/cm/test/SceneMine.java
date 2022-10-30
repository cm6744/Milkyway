package cm.test;

import cm.milkywaygl.GLRenderable;
import cm.milkywaygl.maths.Maths;
import cm.milkywaygl.maths.RandomMap;
import cm.milkywaygl.maths.VMaths;
import cm.milkywaygl.maths.check.Box4;
import cm.milkywaygl.render.GL;
import cm.milkywaygl.render.GL3Performed;
import cm.milkywaygl.render.nnat.InputMap;
import cm.milkywaygl.render.nnat.TaskCaller;
import cm.milkywaygl.render.wrapper.Color4;
import cm.milkywaygl.render.wrapper.Font2;
import cm.milkywaygl.render.wrapper.Keys;
import cm.milkywaygl.resource.Resource;
import cm.milkywaygl.sound.ClipPlayer;
import cm.milkywaygl.text.Data;
import cm.milkywaygl.text.I18n;
import cm.milkywaygl.util.container.List;
import cm.milkywaylib.linkdown.AnimatedRenderer;
import cm.milkywaylib.linkdown.BufButton;
import cm.milkywaylib.linklib.ChildBuffer2D;
import cm.milkywaylib.linklib.ChildBuffer3D;
import cm.milkywaylib.linklib.RenderBuffer;
import cm.milkywaylib.linklib.Scene;
import cm.milkywaylib.linkdown.BufBound;
import cm.milkywaylib.linkdown.stg.BufBullet;
import cm.milkywaylib.linkdown.stg.BufPlayer;

import java.nio.charset.StandardCharsets;

public class SceneMine extends Scene
{

    ChildBuffer2D b2d = new ChildBuffer2D();
    ChildBuffer2D bullet = new ChildBuffer2D();
    ChildBuffer3D b3d = new ChildBuffer3D();

    Box4 scr = Box4.offset(170, 20, 460, 560);
    BufBound point = new BufBound();
    BufButton button = new BufButton();
    BufPlayer player;
    GL3Performed gl3;
    RandomMap map = new RandomMap(2, 3, 4);

    public void init()
    {
        GL.gl.font(Assets.all);
        point.box4().setSize(14, 14);
        point.pushTexture(Assets.point);
        point.bound().setSize(6, 6);
        player = new BufPlayer(5.5, 2, point, new AnimatedRenderer(Assets.player1, 4, 2, 0).perTime(5), new AnimatedRenderer(Assets.player1, 4, 2, 1).perTime(5), Box4.offset(170, 20, 460, 560));
        player.pushTexture(Assets.player1);
        player.box4().setSize(42, 54);
        player.box4().loc(380, 420);
        player.vec2().vec(0, -90);
        gl3 = new GL3Performed(GL.gl);
        button = new BufButton();
        button.box4().setSize(200, 20);
        button.box4().loc(400, 200);
        button.pushTexture(Assets.button);
        button.append(I18n.translate("button.1"));
        button.font(Assets.all, null, 0);
    }

    public void tick()
    {
        gl3.tick();
        button.tick();
        if(button.clickOn()) {
            System.out.println("Hello!");
            button.callDown(30);
        }
        for(int i = 0; i < 1; i++) {
            BufBullet c;
            bullet.addChild(c = new BufBullet());
            c.pushTexture(Assets.bullet1);
            c.box4().setSize(24, 24);
            c.box4().loc(400, 200);
            c.vec2().vec(Maths.random(0.1, 3), Maths.random(-5, 5)+ VMaths.degreeBetweenAB(player.box4().x(), player.box4().y(), c.box4().x(), c.box4().y()));
            c.bound().setSize(5, 5);
            c.retype(0);
            c.dye(map.rand());
        }
        if(TaskCaller.globalTime % 10000 == 0) {
            RenderBuffer cp = new BufBound();
            b2d.addChild(cp);
            cp.pushTexture(Assets.moon);
            cp.box4().setSize(0, 120);
            cp.box4().loc(scr.x(), scr.y() - 82);
        }
        int times = 0;
        for(int i = bullet.childList().last(); i >= 0; i--) {
            BufBound r = (BufBound) bullet.childList().get(i);
            if(r != null) {
                for(int i2 = bullet.childList().size() - 1; i2 >= 0; i2--) {
                    RenderBuffer r2 = bullet.childList().get(i);
                    if(r2 != null && r2 != r && r2.box4().boundAsCir(r.box4()) && Maths.random() < 0.001) {
                        bullet.childList().remove(i);
                    }
                    times++;
                }
                r.tick();
                if(!r.box4().boundAsRct(scr)) {
                    bullet.childList().remove(i);
                }
                if(player.bound().boundAsCir(r.bound())) {
                    ClipPlayer.play("sounds/effect/player_die.wav");
                }
            }
        }
        for(int i = b2d.childList().last(); i >= 0; i--) {
            RenderBuffer r = b2d.childList().get(i);
            if(r != null) {
                r.tick();
                r.effect().mvOpacity(-0.001);
                r.box4().mvSize(4, 4);
                if(r.effect().opacity() <= 0) {
                    b2d.childList().remove(i);
                }
            }
        }
        player.tick();
        point.box4().loc(player.box4().x(), player.box4().y());

        if(InputMap.keyOn(Keys.CTRL)) {
            TaskCaller.setDefaultFps(1000, 60);
        }
        else {
            TaskCaller.setDefaultFps(60, 60);
        }
    }

    public void render()
    {
        //GL.gl3.cameraLookPosTranslate(-0.1, 0, 0);
        if(TaskCaller.globalTime >= 600) {
            gl3.sped=5;
        }
        GL.gl.clear();
        GL.gl2.begin();

        GL.gl8.draw(Assets.background2, 170 - 40, 20, 460 + 80, 560);
        for(int i = b2d.childList().last(); i >= 0; i--) {
            RenderBuffer r = b2d.childList().get(i);
            if(r != null) {
                r.render();
            }
        }
        GL.gl2.end();

        GL.gl3.begin();
        gl3.render();
        GL.gl3.end();

        GL.gl2.begin();

        player.render();
        for(int i = bullet.childList().last(); i >= 0; i--) {
            GLRenderable r = bullet.childList().get(i);
            if(r != null) {
                r.render();
            }
        }
        point.render();
        button.render();
        GL.gl8.draw(Assets.overlay1, 0, 0, 800, 600);

        //player.render();
        GL.gl.font(Assets.all);
        GL.gl.color(Color4.GOLD);
        GL.gl8.drawText(I18n.translate("t1"), 400, 400, true);
        GL.gl8.drawText("Bullet Count: " + Data.toString(bullet.childList().size()), 20, 570, false);
        GL.gl8.drawText(Data.toString(TaskCaller.nowFpsRender), 200, 570, false);
        GL.gl8.drawText(Data.toString(TaskCaller.nowFpsUpdate), 300, 570, false);
        //GL.gl8.drawUV(Assets.character, 50, 30, 170, 170);
        GL.gl2.end();
    }

}

