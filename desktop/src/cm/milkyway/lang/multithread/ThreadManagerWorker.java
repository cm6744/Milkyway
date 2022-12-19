package cm.milkyway.lang.multithread;

import cm.milkyway.lang.Task;

class ThreadManagerWorker extends Thread
{

    Task task;
    boolean isStarted;

    public ThreadManagerWorker(Task r)
    {
        task = r;
    }

    public void start()
    {
        isStarted = true;
        super.start();
    }

    public void dispose()
    {
        interrupt();
    }

    public void run()
    {
        task.run();
    }

    public void sleep(int ms)
    {
        try {
            Thread.sleep(ms);
        }
        catch(InterruptedException ignored) {
        }
    }

}
