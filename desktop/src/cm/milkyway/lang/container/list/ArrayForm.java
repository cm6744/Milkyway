package cm.milkyway.lang.container.list;

public interface ArrayForm<E>
{

    void add(E e);

    void addAll(ArrayForm<E> e);

    void set(int i, E e);

    E remove(int i);

    E remove(E e);

    E get(int i);

    int indexOf(E e);

    boolean contains(E e);

    boolean isEmpty();

    void clear();

    int size();

    int last();

    E lastObj();

    E firstObj();

    E take();

}
