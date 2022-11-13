package cm.milkywaylib.buffers;

public class ParticleAuto extends Particle
{

    public void tickThen()
    {
        super.tickThen();

        if(effect.opacity() <= 0) {
            remove();
        }

        if(sizeCrease > 0) {
            if(box4.width() > sizeLimit || box4.height() > sizeLimit) {
                remove();
            }
        }
        else {
            if(box4.width() < sizeLimit || box4.height() < sizeLimit) {
                remove();
            }
        }
    }

}
