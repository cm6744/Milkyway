package cm.milkywaygl.maths;

import cm.milkywaygl.Platform;

//a self-made random class.
//now has been replaced with Java Random.
public class Random
{

    public int seed;
    public int startSeed;

    int M = 197;
    int A = 484;
    int C = 103;

    public Random()
    {
        this(Maths.toInt(Platform.getTickNano()));
    }

    public Random(int seed)
    {
        setSeed(seed);
    }

    public void setSeed(int s)
    {
        seed = s;
        startSeed = s;
    }

    public double newSeed()
    {
        double gen = 1;
        int tryTimes = 0;
        int oldSeed = seed;

        do {
            tryTimes++;
            gen = (M * seed * gen + A) % (C + gen / M);
            seed = (seed * M + A) / C - A;
        } while(seed == oldSeed && tryTimes < 5);

        if(gen < -100 || gen > 100) {
            gen = 100;
        }

        return gen;
    }

    public double genPercent()
    {
        double i = Maths.abs(newSeed());
        return i * 0.01;
    }

}
