package cm.type2d.res;

import cm.milkyway.opengl.loader.Loader;
import cm.milkyway.opengl.render.g2d.Color4;

public class Load
{

    public static Loader loader = new Loader();

    static {
        loader.loadTex("chr1_walk", "textures/character/chr1walk.png");
        loader.loadTex("chr1_stay", "textures/character/chr1stay.png");
        loader.loadTex("chr1_r1", "textures/character/chr1blink.png");

        loader.loadFontType("all", "fonts/en_us.ttf", "fonts/en_us_desc.json");
        loader.loadFont("all", "all", Color4.C1111, 12);

        loader.loadTex("bronze", "textures/item/bronze.png");
        loader.loadTex("electrum", "textures/item/electrum.png");
    }

}
