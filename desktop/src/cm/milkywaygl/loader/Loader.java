package cm.milkywaygl.loader;

import cm.milkywaygl.Milkyway;
import cm.milkywaygl.render.g2d.BufferTex;
import cm.milkywaygl.render.g2d.Color4;
import cm.milkywaygl.render.g2d.Font2;
import cm.milkywaygl.render.g2d.FontType;
import cm.milkywaygl.sound.Sound;
import cm.milkywaytool.Task;
import cm.milkywaytool.container.Map;
import cm.milkywaytool.container.Queue;

public class Loader
{

    double progress;
    double total;
    double run;
    boolean done;
    Map<String, BufferTex> texMap = new Map<>();
    Map<String, FontType> fontTypeMap = new Map<>();
    Map<String, Font2> fontMap = new Map<>();
    Map<String, Sound> soundMap = new Map<>();
    Queue<Task> tasks = new Queue<>();

    public void loadTex(String name, String path)
    {
        tasks.offer(() -> {
            BufferTex buf = Milkyway.graphics.newTex();
            Milkyway.gl2d.loadTexture(buf, path);
            texMap.put(name, buf);
        });
    }

    public void loadFontType(String name, String path, String desc)
    {
        tasks.offer(() -> {
            FontType fontType = Milkyway.graphics.newType(path, desc);
            fontTypeMap.put(name, fontType);
        });
    }

    public void loadFont(String name, String typeName, Color4 color, double size)
    {
        tasks.offer(() -> {
            FontType fontType = getFontType(typeName);
            fontMap.put(name, fontType.generate(color, size));
        });
    }

    public void loadSound(String name, String path)
    {
        tasks.offer(() -> {
            Sound sound = Milkyway.audio.newSound(path);
            soundMap.put(name, sound);
        });
    }

    public BufferTex getTex(String name)
    {
        return texMap.get(name);
    }

    public FontType getFontType(String name)
    {
        return fontTypeMap.get(name);
    }

    public Font2 getFont(String name)
    {
        return fontMap.get(name);
    }

    public Sound getSound(String name)
    {
        return soundMap.get(name);
    }

    public void update()
    {
        Task r = tasks.take();
        if(r == null) {
            done = true;
            progress = 1;
            return;
        }
        r.run();
        run++;
        progress = run / total;
    }

    public double progress()
    {
        return progress;
    }

    public boolean isDone()
    {
        return done;
    }

}
