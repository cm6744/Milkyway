package cm.milkywaygl.maths;

public class RandomMap
{

    int[] indexes;

    public RandomMap(int... index)
    {
        indexes = index;
    }

    public int rand()
    {
        return indexes[Maths.randomInt(0, indexes.length)];
    }

}
