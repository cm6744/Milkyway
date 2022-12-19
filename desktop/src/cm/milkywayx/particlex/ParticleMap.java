package cm.milkywayx.particlex;

import cm.milkyway.lang.container.List;
import cm.milkyway.physics.shapes.Rect;

public class ParticleMap
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

    public void render()
    {
        Particle p;
        for(int i = particles.last(); i >= 0; i--) {
            p = particles.get(i);
            p.render();
        }
    }

}
