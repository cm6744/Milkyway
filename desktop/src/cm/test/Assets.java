package cm.test;

import cm.milkywaygl.render.wrapper.Color4;
import cm.milkywaygl.render.wrapper.Font2;
import cm.milkywaygl.render.wrapper.FontType;
import cm.milkywaygl.resource.AssetsLoading;
import cm.milkywaygl.util.IntBuffer;
import cm.milkywaytype.stg.Bullet;

public class Assets
{
    public static AssetsLoading loading = new AssetsLoading();

    public static IntBuffer white = IntBuffer.create();
    public static IntBuffer loadingFont = IntBuffer.create();
    public static IntBuffer loadingBG = IntBuffer.create();

    public static IntBuffer stg6bg = IntBuffer.create();
    public static IntBuffer overlay1 = IntBuffer.create();
    public static IntBuffer bullet1 = IntBuffer.create();
    public static IntBuffer bulletFG = IntBuffer.create();
    public static IntBuffer cursor = IntBuffer.create();
    public static FontType chType;
    public static Font2 ch;
    public static FontType enType;
    public static Font2 en;
    public static IntBuffer player1 = IntBuffer.create();
    public static IntBuffer point = IntBuffer.create();
    public static IntBuffer character = IntBuffer.create();
    public static IntBuffer win = IntBuffer.create();
    public static IntBuffer moon = IntBuffer.create();
    public static IntBuffer button = IntBuffer.create();
    public static IntBuffer progress = IntBuffer.create();
    public static IntBuffer enemy = IntBuffer.create();

    public static void loadAll()
    {
        loading.loadTexImmediately(Assets.white, "textures/dye/1111.png");
        loading.loadTexImmediately(Assets.loadingFont, "textures/font/loading.png");
        loading.loadTexImmediately(Assets.loadingBG, "textures/2.png");
        loading.loadTexImmediately(Assets.progress, "textures/misc/progress.png");
        loading.loadTexImmediately(Assets.stg6bg, ("textures/4.png"));
        loading.loadTexImmediately(Assets.moon, ("textures/effect/moon.png"));
        loading.loadTexImmediately(Assets.win, ("textures/win.png"));

        loading.loadTex(Assets.cursor, ("textures/misc/cursor.png"));
        loading.loadTex(Assets.bullet1, ("textures/1.png"));
        loading.loadTex(Assets.bulletFG, ("textures/1a.png"));
        loading.loadTex(enemy, "textures/enemy.png");
        Bullet.TEXTURE = bullet1;
        Bullet.FOG = bulletFG;
        loading.loadTex(Assets.overlay1, ("textures/3.png"));
        loading.loadTex(Assets.player1, ("textures/mrsp.png"));
        loading.loadTex(Assets.point, ("textures/boundpoint.png"));
        loading.loadTex(Assets.character, ("textures/5.png"));
        loading.loadTex(Assets.button, ("textures/button.png"));
        loading.loadSpecial(() -> Assets.enType = new FontType("font/en_us.ttf", "font/en_us_desc.json"));
        loading.loadSpecial(() -> Assets.en = Assets.enType.generate(Color4.WHITE, 16));
        loading.loadSpecial(() -> Assets.chType = new FontType("font/zh_cn.ttf", "font/zh_cn_desc.json"));
        loading.loadSpecial(() -> Assets.ch = Assets.chType.generate(Color4.WHITE, 16));
    }

}
