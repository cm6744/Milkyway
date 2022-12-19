package cm.milkywayx.widgetx.base;

public abstract class Timeline
{

    private int time;

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
        return forEach(period, 0);
    }

    public boolean forEach(int period, int keep)
    {
        if(period == 0) {
            return true;
        }
        return time() % period <= keep;
    }

}
