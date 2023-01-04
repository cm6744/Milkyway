package cm.type25d;

import cm.backends.lwjgl.LwjglFontType;
import cm.backends.lwjgl.LwjglSoundClip;
import cm.backends.lwjgl.LwjglTexture;
import cm.milkyway.lang.io.AccessLocal;
import cm.milkyway.opengl.audio.Sound;
import cm.milkyway.opengl.loader.Loader;
import cm.milkyway.opengl.render.g2d.Color;
import cm.milkyway.opengl.render.g2d.FontType;
import cm.milkyway.opengl.render.g2d.Tex;

public class Load
{

    public static Loader loader = new LoaderImpl();

    static {
        loader.loadTex("tiles1", "textures/tiles/tiles1.png");
        loader.loadTex("gui_painter", "textures/gui/painter.png");
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
