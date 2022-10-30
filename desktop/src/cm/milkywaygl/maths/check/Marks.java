package cm.milkywaygl.maths.check;

import cm.milkywaygl.util.container.List;

public class Marks
{

    List<String> marks = new List<>();

    public static Marks create()
    {
        return new Marks();
    }

    public void mark(String mk)
    {
        marks.add(mk);
    }

    public boolean hasMark(String mk)
    {
        return marks.contains(mk);
    }

}
