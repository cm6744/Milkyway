package cm.milkyway.lang.container.list;

import cm.milkyway.lang.container.Iterable;
import cm.milkyway.lang.container.Iterator;

public class List<E> extends AbstractList<E> implements Iterable<E>
{

    private static final int DEFAULT_CAPACITY = 10;
    public Object[] objects;
    private int size;

    public List(int capacity)
    {
        objects = new Object[capacity];
    }

    public List()
    {
        this(DEFAULT_CAPACITY);
    }

    public static <E> List<E> of(E... os)
    {
        List<E> list = new List<>();
        for(int i = 0; i < os.length; i++) {
            list.add(os[i]);
        }
        return list;
    }

    public static void arrayCopy(Object[] oldArray, Object[] newArray, int oldStartPos, int newStartPos, int copyLength)
    {
        for(int i = 0; i < copyLength; ++i) {
            if(i + newStartPos >= newArray.length) {
                continue;
            }
            if(i + oldStartPos >= oldArray.length) {
                continue;
            }
            newArray[i + newStartPos] = oldArray[i + oldStartPos];
        }
    }

    public void checkRangeNeedIncrease(int index, Object obj)
    {
        if(size >= objects.length) {
            Object[] newObjects = new Object[size * 2];

            if(index == -1 && obj == null) {
                arrayCopy(objects, newObjects, 0, 0, size);
            }
            else {
                arrayCopy(objects, newObjects, index, index + 1, size - index);
            }

            objects = newObjects;
        }
    }

    public boolean checkIndex(int index)
    {
        return index >= 0 && index < size();
    }

    public int size()
    {
        return size;
    }

    public int indexOf(E obj)
    {
        if(obj != null) {
            for(int i = 0; i < size(); i++) {
                if(obj.equals(objects[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    public void set(int i, E obj)
    {
        if(checkIndex(i)) {
            objects[i] = obj;
        }
        throw new IndexOutOfBoundsException("out bound: " + i);
    }

    public void add(E obj)
    {
        checkRangeNeedIncrease(-1, null);
        objects[size++] = obj;
    }

    public E get(int index)
    {
        if(checkIndex(index)) {
            return (E) objects[index];
        }
        throw new IndexOutOfBoundsException("out bound: " + index);
    }

    public void clear()
    {
        for(int i = 0; i < size; i++) {
            objects[i] = null;
        }
        size = 0;
    }

    public E remove(int index)
    {
        if(checkIndex(index)) {
            Object obj = objects[index];

            if(index == size) {
                objects[index] = null;
                return (E) obj;
            }
            else {
                arrayCopy(objects, objects, index + 1, index, size - index);
            }
            size--;
            return (E) obj;
        }

        return null;
    }

    //ITERATE

    /**
     * Care:!
     * this is a method which will waste lots of efficiency!
     */
    public void iterate(Iterator<E> itr, boolean opposite)
    {
        if(opposite) {
            for(int i = last(); i >= 0; i--) {
                E o = get(i);
                itr.iterate(o, i);
            }
        }
        else {
            for(int i = 0; i < size(); i++) {
                E o = get(i);
                itr.iterate(o, i);
            }
        }
    }

}
