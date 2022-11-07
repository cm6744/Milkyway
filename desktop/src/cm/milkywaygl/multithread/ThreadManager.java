package cm.milkywaygl.multithread;

import cm.milkywaygl.inter.GLTickable;
import cm.milkywaygl.util.container.List;
import cm.milkywaygl.util.container.Map;

public class ThreadManager
{

    static Map<GLTickable, RThread> threads = new Map<>();

    //put up a new thread to run ThreadTask
    public static void join(GLTickable r)
    {
        RThread thread = new RThread(r);
        threads.put(r, thread);
    }

    //return start() is invoked
    public static boolean isStarted(GLTickable r)
    {
        return threads.get(r).isStarted;
    }

    //start the thread
    public static void start(GLTickable r)
    {
        threads.get(r).start();
    }

    //stop the thread
    //if ThreadTask.run is a while-loop task
    //use while(ThreadManager.isNotDisposed) {...}
    public static void dispose(GLTickable r)
    {
        threads.get(r).dispose();
    }

    public static boolean isNotDisposed(GLTickable r)
    {
        return !threads.get(r).isInterrupted();
    }

    public static void sleep(GLTickable r, int ms)
    {
        threads.get(r).sleep(ms);
    }

    private static class RThread extends Thread
    {

        GLTickable task;
        boolean isStarted;

        public RThread(GLTickable r)
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
            task.tick();
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
