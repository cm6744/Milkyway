package cm.milkyway.lang.container;

public interface Iterable<E>
{

    @Deprecated
    void iterate(Iterator<E> itr, boolean opposite);

}
