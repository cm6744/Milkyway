package cm.milkywaygl.util.container;

public class VarList extends SafeList<Integer>
{

    public VarList(int cap)
    {
        super(0, cap);
    }

    public VarList()
    {
        super(0);
    }

}
