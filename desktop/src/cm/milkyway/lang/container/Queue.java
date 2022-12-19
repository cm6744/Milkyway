package cm.milkyway.lang.container;

public class Queue<E> implements Iterable<E>
{

    private final List<E> allObj = new List<>();

    public void offer(E obj)
    {
        allObj.add(obj);
    }

    public E take()
    {
        if(allObj.size() == 0) {
            return null;
        }
        return allObj.remove(0);
    }

    public int size()
    {
        return allObj.size();
    }

    public void iterate(Iterator<E> itr, boolean opposite)
    {
        allObj.iterate(itr, opposite);
    }

}
