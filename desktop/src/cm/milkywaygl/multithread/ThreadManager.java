package cm.milkywaygl.multithread;

import cm.milkywaytool.Task;
import cm.milkywaytool.container.Map;

public class ThreadManager
{

    static Map<Task, RThread> threads = new Map<>();

    //put up a new thread to run ThreadTask
    public static void join(Task r)
    {
        RThread thread = new RThread(r);
        threads.put(r, thread);
    }

    //return start() is invoked
    public static boolean isStarted(Task r)
    {
        return threads.get(r).isStarted;
    }

    //start the thread
    public static void start(Task r)
    {
        threads.get(r).start();
    }

    //stop the thread
    //if ThreadTask.run is a while-loop task
    //use while(ThreadManager.isNotDisposed) {...}
    public static void dispose(Task r)
    {
        threads.get(r).dispose();
    }

    public static boolean isNotDisposed(Task r)
    {
        return !threads.get(r).isInterrupted();
    }

    public static void sleep(Task r, int ms)
    {
        threads.get(r).sleep(ms);
    }

    private static class RThread extends Thread
    {

        Task task;
        boolean isStarted;

        public RThread(Task r)
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

}
