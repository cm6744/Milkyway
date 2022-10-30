package cm.milkywaygl.util.container;

import java.util.ArrayDeque;

public class Queue<E>
{

    private final java.util.Queue<E> queue = new ArrayDeque<>();//@Nt

    public void offer(E obj)
    {
        queue.offer(obj);
    }

    public E take()
    {
        if(queue.size() == 0) {
            return null;
        }
        return queue.remove();
    }

    public int size()
    {
        return queue.size();
    }

}
