package cm.milkyway.lang.multithread;

import cm.milkyway.BackendMark;
import cm.milkyway.lang.Task;
import cm.milkyway.lang.container.Map;

@BackendMark
public class ThreadManager
{

    static Map<Task, ThreadManagerWorker> threads = new Map<>();

    //put up a new thread to run ThreadTask
    public static void join(Task r)
    {
        ThreadManagerWorker thread = new ThreadManagerWorker(r);
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

}
