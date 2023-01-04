package cm.milkyway.tipy;

public class IntArrayValue extends TipyValue
{

    int[] v;
    double[] trsDoubleArr;

    public IntArrayValue(int[] arr)
    {
        v = arr;
        trsDoubleArr = new double[v.length];
        for(int i = 0; i < v.length; i++)
        {
            trsDoubleArr[i] = v[i];
        }
    }

    public Type type()
    {
        return Type.ARRAY_INT;
    }

    public int[] getArrayInt()
    {
        return v;
    }

    public double[] getArrayDouble()
    {
        return trsDoubleArr;
    }

}
