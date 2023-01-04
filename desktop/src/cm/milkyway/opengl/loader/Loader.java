package cm.milkyway.opengl.loader;

import cm.milkyway.lang.container.list.List;
import cm.milkyway.opengl.render.g2d.Tex;
import cm.milkyway.opengl.render.g2d.Color;
import cm.milkyway.opengl.render.g2d.Font;
import cm.milkyway.opengl.render.g2d.FontType;
import cm.milkyway.opengl.audio.Sound;
import cm.milkyway.lang.Task;
import cm.milkyway.lang.container.map.Map;

public abstract class Loader
{

    double progress;
    double total;
    double run;
    boolean done;
    protected Map<String, Tex> texMap = new Map<>();
    protected Map<String, FontType> fontTypeMap = new Map<>();
    protected Map<String, Font> fontMap = new Map<>();
    protected Map<String, Sound> soundMap = new Map<>();
    protected List<Task> tasks = new List<>();

    protected void offer(Task task)
    {
        tasks.add(task);
    }

    public abstract void loadTex(String name, String path);

    public abstract void loadFontType(String name, String path, String desc);

    public abstract void loadFont(String name, String typeName, Color color, double size);

    public abstract void loadSound(String name, String path);

    public Tex getTex(String name)
    {
        return texMap.get(name);
    }

    public FontType getFontType(String name)
    {
        return fontTypeMap.get(name);
    }

    public Font getFont(String name)
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
