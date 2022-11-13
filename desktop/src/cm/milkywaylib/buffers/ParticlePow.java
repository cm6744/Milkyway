package cm.milkywaylib.buffers;

public class ParticlePow extends Particle
{

    double period;

    public void tickThen()
    {
        super.tickThen();

        effect.setOpacity(opacityTimed());

        if(time() > period) {
            remove();
        }
    }

    private double opacityTimed()
    {
        double a = -4 / (period * period);
        return a * time() * (time() - period);
    }

    public void period(double p)
    {
        period = p;
    }

}
