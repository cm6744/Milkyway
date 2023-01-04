package cm.type2d;

import cm.backends.lwjgl.LwjglFontType;
import cm.backends.lwjgl.LwjglSoundClip;
import cm.backends.lwjgl.LwjglTexture;
import cm.milkyway.lang.io.AccessLocal;
import cm.milkyway.opengl.audio.Sound;
import cm.milkyway.opengl.loader.Loader;
import cm.milkyway.opengl.render.g2d.Tex;
import cm.milkyway.opengl.render.g2d.Color;
import cm.milkyway.opengl.render.g2d.FontType;

public class Load
{

    public static Loader loader = new LoaderImpl();

    static {
        loader.loadTex("shade", "textures/misc/shade.png");

        loader.loadTex("chr1_walk", "textures/character/chr1walk.png");
        loader.loadTex("chr1_stay", "textures/character/chr1stay.png");
        loader.loadTex("chr1_r1", "textures/character/chr1blink.png");
        loader.loadTex("chr1_r2", "textures/character/chr1pick.png");

        loader.loadFontType("all", "fonts/en_us.ttf", "fonts/en_us_desc.json");
        loader.loadFont("all", "all", Color.C1111, 12);

        loader.loadTex("bronze", "textures/item/bronze.png");
        loader.loadTex("electrum", "textures/item/electrum.png");

        loader.loadTex("room1_1", "textures/bgs/bg1.png");
        loader.loadTex("room1_2", "textures/bgs/fr1.png");
        loader.loadTex("room1_3", "textures/bgs/effect1.png");
        loader.loadTex("sky_n1", "textures/bgs/sky_n1.png");

        loader.loadTex("hud", "textures/gui/hud.png");
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
