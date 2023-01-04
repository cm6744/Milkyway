package cm.milkyway.tipy;

public class DoubleArrayValue extends TipyValue
{

    double[] v;
    int[] trsIntArr;

    public DoubleArrayValue(double[] arr)
    {
        v = arr;
        trsIntArr = new int[v.length];
        for(int i = 0; i < v.length; i++)
        {
            trsIntArr[i] = (int) v[i];
        }
    }

    public Type type()
    {
        return Type.ARRAY_DOUBLE;
    }

    public double[] getArrayDouble()
    {
        return v;
    }

    public int[] getArrayInt()
    {
        return super.getArrayInt();
    }

}
