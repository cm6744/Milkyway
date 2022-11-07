package cm.milkywaygl.inter;

public abstract class GLTimeline implements GLTickable
{

    protected int time;

    public final void tick()
    {
        tickThen();
        promote();
    }

    public abstract void tickThen();

    public void promote()
    {
        time++;
    }

    public int time()
    {
        return time;
    }

    public void setTime(int tm)
    {
        time = tm;
    }

    public boolean forEach(int period)
    {
        return time() % period == 0;
    }

    public boolean forEach(int period, int keep)
    {
        return time() % period <= keep;
    }

}
