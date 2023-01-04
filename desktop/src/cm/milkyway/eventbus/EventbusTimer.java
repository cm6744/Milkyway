package cm.milkyway.eventbus;

public class EventbusTimer
{

    int timer;

    void promote()
    {
        timer++;
    }

    public int time()
    {
        return timer;
    }

}
