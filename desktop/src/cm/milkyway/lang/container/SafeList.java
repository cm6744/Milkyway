package cm.milkyway.lang.container;

//avoid null, but use a default value
public class SafeList<E> extends List<E>
{

    E filling;

    public SafeList(E fill, int cap)
    {
        super(cap);
        filling = fill;
    }

    public SafeList(E fill)
    {
        filling = fill;
    }

    public void add(E obj)
    {
        if(obj == null) {
            super.add(filling);
        }
        super.add(obj);
    }

    public E get(int index)
    {
        E o = super.get(index);
        return o == null ? filling : o;
    }

}
