package cm.milkywaygl.util.container;

public class List<E>
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
        return index >= 0 && index < size;
    }

    public int size()
    {
        return size;
    }

    public int last()
    {
        return size - 1;
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

    public void add(E obj)
    {
        checkRangeNeedIncrease(-1, null);
        objects[size++] = obj;
    }

    public void addAll(E[] obj)
    {
        for(int i = 0; i < obj.length; i++) {
            add(obj[i]);
        }
    }

    public E get(int index)
    {
        if(checkIndex(index)) {
            return (E) objects[index];
        }
        return null;
    }

    public E getLast()
    {
        return get(last());
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

    public E remove(E obj)
    {
        return remove(indexOf(obj));
    }

    public boolean contains(E obj)
    {
        return indexOf(obj) >= 0;
    }

}
