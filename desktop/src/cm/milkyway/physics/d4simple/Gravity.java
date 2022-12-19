package cm.milkyway.physics.d4simple;

public class Gravity
{

    double gravity = 9.8;

    public Gravity(double gr) {
        gravity = gr;
    }

    public double trsDown(double time)
    {
        return 0.5 * gravity * time * time;
    }

}
