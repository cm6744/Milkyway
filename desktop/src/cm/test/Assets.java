package cm.test;

import cm.milkywaygl.render.GL;
import cm.milkywaygl.render.wrapper.Color4;
import cm.milkywaygl.render.wrapper.Font2;
import cm.milkywaygl.render.wrapper.FontType;
import cm.milkywaygl.resource.AssetsLoading;
import cm.milkywaygl.util.IntBuffer;

public class Assets
{
    public static AssetsLoading loading = new AssetsLoading();

    public static IntBuffer background1 = IntBuffer.newBuf();
    public static IntBuffer background2 = IntBuffer.newBuf();
    public static IntBuffer overlay1 = IntBuffer.newBuf();
    public static IntBuffer bullet1 = IntBuffer.newBuf();
    public static IntBuffer cursor = IntBuffer.newBuf();
    public static FontType allType;
    public static Font2 all;
    public static IntBuffer player1 = IntBuffer.newBuf();
    public static IntBuffer point = IntBuffer.newBuf();
    public static IntBuffer character = IntBuffer.newBuf();
    public static IntBuffer win = IntBuffer.newBuf();
    public static IntBuffer moon = IntBuffer.newBuf();
    public static IntBuffer button = IntBuffer.newBuf();

    public static void loadAll()
    {
        loading.loadTex(Assets.background1, "textures/2.png");
        loading.loadTex(Assets.cursor, ("textures/misc/cursor.png"));
        loading.loadTex(Assets.bullet1, ("textures/1.png"));
        loading.loadTex(Assets.overlay1, ("textures/3.png"));
        loading.loadTex(Assets.background2, ("textures/4.png"));
        loading.loadTex(Assets.player1, ("textures/mrsp.png"));
        loading.loadTex(Assets.point, ("textures/boundpoint.png"));
        loading.loadTex(Assets.character, ("textures/5.png"));
        loading.loadTex(Assets.win, ("textures/win.png"));
        loading.loadTex(Assets.moon, ("textures/effect/moon.png"));
        loading.loadTex(Assets.button, ("textures/button.png"));
        loading.loadSpecial(() -> Assets.allType = new FontType("font/all.otf", "font/all_desc.json"));
        loading.loadSpecial(() -> Assets.all = Assets.allType.generate(Color4.WHITE, Color4.SHADOW, 2, 12));
    }

}
