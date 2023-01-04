package cm.backends.lwjgl;

import cm.milkyway.lang.Disposable;
import cm.milkyway.lang.Platform;

public class LwjglBlockedQueue
{

    static LwjglBatch blocking;

    //ensure the given batch is begun.
    //if given batch is null, it will not begin a new batch.
    public static void ensure(LwjglBatch batch)
    {
        if(blocking != batch) {
            if(blocking != null) {
                blocking.end();
            }
            if(batch != null) {
                batch.begin();
            }
        }
    }

    public static void begin(LwjglBatch batch)
    {
        if(blocking != null) {
            Platform.error("Cannot use multi renderer at same time.");
        }
        if(blocking == batch) {
            Platform.error("Cannot begin batches multi times.");
        }
        blocking = batch;
    }

    public static void end(LwjglBatch batch)
    {
        if(blocking == null) {
            Platform.error("Cannot end batches multi times.");
        }
        if(blocking != batch) {
            Platform.error("Cannot use multi renderer at same time.");
        }
        blocking = null;
    }

    public interface LwjglBatch extends Disposable
    {

        void nbegin();

        void nend();

        default void begin()
        {
            LwjglBlockedQueue.begin(this);
            nbegin();
        }

        default void end()
        {
            nend();
            LwjglBlockedQueue.end(this);
        }

    }

}
