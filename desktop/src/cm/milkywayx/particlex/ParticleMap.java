package cm.milkywayx.particlex;

import cm.milkyway.lang.container.list.List;
import cm.milkyway.lang.maths.shapes.Rect;
import cm.milkyway.opengl.render.graphics.Graphics2D;
import cm.milkywayx.widgetx.Renderable2D;
import cm.milkywayx.widgetx.Tickable;

public class ParticleMap implements Renderable2D, Tickable
{

    List<Particle> particles = new List<>();
    Rect removeLine;

    public ParticleMap(Rect remove)
    {
        removeLine = remove;
    }

    public void add(Particle p)
    {
        particles.add(p);
    }

    public void tick()
    {
        Particle p;
        for(int i = particles.last(); i >= 0; i--) {
            p = particles.get(i);
            p.tick();

            if(p.isForRemove()
                    || !removeLine.interacts(p.box())) {
                particles.remove(i);
                p.disposeLight();
            }
        }
    }

    public void render(Graphics2D g)
    {
        Particle p;
        for(int i = particles.last(); i >= 0; i--) {
            p = particles.get(i);
            p.render(g);
        }
    }

}
