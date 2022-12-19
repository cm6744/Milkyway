package cm.typestg;

import cm.milkyway.opengl.render.g2d.Area;
import cm.milkyway.opengl.render.g2d.AreaStatic;
import cm.milkyway.opengl.render.g2d.BufferTex;
import cm.milkyway.opengl.audio.Sound;
import cm.milkyway.lang.Provider;
import cm.milkyway.lang.container.List;
import cm.typestg.test.Assets;

public class BulletMap
{

    public static List<Bullet> copies = new List<>();
    public static BufferTex SIZE16;
    public static BufferTex SIZE32;
    public static BufferTex SIZE64;

    public static Bullet[][] type_color_mat = new Bullet[1024][16];
    public static Sound[] type_sound_mat = new Sound[1024];

    public static void registerLaser(int type, int ln, int size, int bw, int bh, int rw, int rh, BufferTex tex, Sound sound)
    {
        register(Laser::new, type, ln, size, bw, bh, rw, rh, true, false, tex, sound);
    }

    public static void register(int type, int ln, int size, int bw, int bh, int rw, int rh, boolean rot, boolean roting, BufferTex tex, Sound sound)
    {
        register(Bullet::new, type, ln, size, bw, bh, rw, rh, rot, roting, tex, sound);
    }

    public static void register(Provider<? extends Bullet> obj, int type, int ln, int size, int bw, int bh, int rw, int rh, boolean rot, boolean roting, BufferTex tex, Sound sound)
    {
        Bullet[] col = type_color_mat[type];
        type_sound_mat[type] = sound;

        for(int i = 0; i < 16; i++) {
            Area area = AreaStatic.dim(tex, i * size, ln * size, size, size);
            col[i] = obj.get();
            col[i].retype(type);
            col[i].dye(i);
            col[i].setTexture(area);
            col[i].rotatable = rot;
            col[i].rotating = roting;
            col[i].box().resize(rw, rh);
            col[i].bound().resize(bw, bh);
        }
    }

    public static void registerAll()
    {

        Sound s1 = Assets.loader.getSound("st1");
        Sound s2 = Assets.loader.getSound("st2");
        //small
        register(0, 0, 16, 5, 5, 16, 16, false, false, SIZE16, s2);
        register(1, 1, 16, 5, 5, 16, 16, true, false, SIZE16, s2);
        register(2, 2, 16, 5, 5, 16, 16, true, false, SIZE16, s2);
        register(3, 3, 16, 5, 5, 16, 16, true, false, SIZE16, s2);
        register(4, 4, 16, 5, 5, 16, 16, true, true, SIZE16, s2);

        //mid
        register(50, 0, 32, 10, 10, 32, 32, false, false, SIZE32, s2);
        register(51, 1, 32, 5, 5, 32, 32, true, false, SIZE32, s2);

        //big
        register(80, 0, 64, 20, 20, 64, 64, false, false, SIZE64, s2);

        //players
        register(100, 2, 16, 20, 50, 70, 15, true, false, SIZE16, s1);
        register(101, 2, 16, 20, 50, 90, 8, true, false, SIZE16, s1);

        //movable_lasers
        register(150, 2, 16, 60, 3, 70, 10, true, false, SIZE16, s2);

        //stick_lasers
        registerLaser(250, 15, 16, 500, 4, 500, 10, SIZE16, s2);
    }

}
