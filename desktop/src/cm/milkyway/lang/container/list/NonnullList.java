package cm.milkyway.lang.container.list;

//avoid null, but use a default value
public class NonnullList<E> extends List<E>
{

    E filling;

    public NonnullList(E fill, int cap)
    {
        super(cap);
        filling = fill;
    }

    public NonnullList(E fill)
    {
        filling = fill;
    }

    public void add(E obj)
    {
        if(obj == null) {
            super.add(filling);
        }
        else {
            super.add(obj);
        }
    }

    public E get(int index)
    {
        E o = super.get(index);
        return o == null ? filling : o;
    }

}
