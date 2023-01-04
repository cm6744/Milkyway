package cm.typestg.test;

import cm.backends.lwjgl.LwjglFontType;
import cm.backends.lwjgl.LwjglSoundClip;
import cm.backends.lwjgl.LwjglTexture;
import cm.milkyway.lang.io.AccessLocal;
import cm.milkyway.opengl.audio.Sound;
import cm.milkyway.opengl.loader.Loader;
import cm.milkyway.opengl.render.g2d.Color;
import cm.milkyway.opengl.render.g2d.FontType;
import cm.milkyway.opengl.render.g2d.Tex;

public class Assets
{

    public static Loader loader = new LoaderImpl();

    public static void loadAll()
    {
        loader.loadTex("loading", "textures/font/loading.png");
        loader.loadTex("progress", "textures/misc/progress.png");
        loader.loadTex("stg6bg", ("textures/4.png"));
        loader.loadTex("stg8bg", ("textures/stg8bg.png"));
        loader.loadTex("stg8bgovl", ("textures/stg8bgovl.png"));
        loader.loadTex("window", ("textures/win.png"));

        loader.loadTex("b16", ("textures/bullet/s16.png"));
        loader.loadTex("b32", ("textures/bullet/s32.png"));
        loader.loadTex("b64", ("textures/bullet/s64.png"));
        loader.loadTex("bulletFogs", ("textures/bullet/effect.png"));

        loader.loadTex("enemy1", "textures/enemy.png");
        loader.loadTex("enemyGlow", "textures/enemyglo.png");
        loader.loadTex("boss1", "textures/boss1.png");
        loader.loadTex("enemyDeath", "textures/effect/shootup.png");
        loader.loadTex("fire", "textures/effect/fire.png");
        loader.loadTex("magicCir", "textures/effect/magic.png");
        loader.loadTex("pl1b", "textures/mrspb.png");
        loader.loadTex("drops", "textures/item/droppings.png");
        loader.loadTex("overlay", ("textures/3.png"));
        loader.loadTex("pl1", ("textures/mrsp.png"));
        loader.loadTex("boundPoint", ("textures/boundpoint.png"));
        loader.loadTex("button", ("textures/button.png"));
        loader.loadFontType("ttf1", "fonts/en_us.ttf", "fonts/en_us_desc.json");
        loader.loadFont("en_us", "ttf1", Color.C1111, 12);
        loader.loadFontType("ttf2", "fonts/zh_cn.ttf", "fonts/zh_cn_desc.json");
        loader.loadFont("zh_cn", "ttf2", Color.C1111, 12);

        loader.loadSound("stg6", "sounds/th08_13.wav");
        loader.loadSound("st1", "sounds/effect/shoot1.wav");
        loader.loadSound("st2", "sounds/effect/shoot2.wav");
    }

    static class LoaderImpl extends Loader
    {

        public void loadTex(String name, String path)
        {
            offer(() -> {
                Tex buf = new LwjglTexture();
                buf.load(new AccessLocal(path));
                buf.set(Tex.NEAREST, Tex.NEAREST);
                texMap.put(name, buf);
            });
        }

        public void loadFontType(String name, String path, String desc)
        {
            offer(() -> {
                FontType fontType = new LwjglFontType(new AccessLocal(path), new AccessLocal(desc));
                fontTypeMap.put(name, fontType);
            });
        }

        public void loadFont(String name, String typeName, Color color, double size)
        {
            offer(() -> {
                FontType fontType = getFontType(typeName);
                fontMap.put(name, fontType.generate(size));
            });
        }

        public void loadSound(String name, String path)
        {
            offer(() -> {
                Sound sound = new LwjglSoundClip(new AccessLocal(path));
                soundMap.put(name, sound);
            });
        }

    }

}
