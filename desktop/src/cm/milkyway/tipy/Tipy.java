package cm.milkyway.tipy;

import cm.milkyway.lang.container.map.Map;

public abstract class Tipy
{

    public abstract Tipy object(String key);

    public abstract void put(String key, Tipy tipy);

    public abstract void put(String key, TipyValue value);

    public final void putBoolean(String key, boolean v)
    {
        put(key, new BooleanValue(v));
    }

    public final void putInt(String key, int v)
    {
        put(key, new DoubleValue(v));
    }

    public final void putDouble(String key, double v)
    {
        put(key, new DoubleValue(v));
    }

    public final void putArray(String key, double[] v)
    {
        put(key, new DoubleArrayValue(v));
    }

    public final void putArray(String key, int[] v)
    {
        put(key, new IntArrayValue(v));
    }

    public final void putArray(String key, boolean[] v)
    {
        put(key, new BooleanArrayValue(v));
    }

    public final void putArray(String key, String[] v)
    {
        put(key, new StringArrayValue(v));
    }

    public final void putString(String key, String v)
    {
        put(key, new StringValue(v));
    }

    public abstract Tipy get(String key);

    public abstract TipyValue getValue(String key);

    public final boolean getBoolean(String key)
    {
        return getValue(key).getBoolean();
    }

    public final String getString(String key)
    {
        return getValue(key).getString();
    }

    public final double getDouble(String key)
    {
        return getValue(key).getDouble();
    }

    public final int getInt(String key)
    {
        return getValue(key).getInt();
    }

    public final double[] getArrayDouble(String key)
    {
        return getValue(key).getArrayDouble();
    }

    public final int[] getArrayInt(String key)
    {
        return getValue(key).getArrayInt();
    }

    public final boolean[] getArrayBoolean(String key)
    {
        return getValue(key).getArrayBoolean();
    }

    public final String[] getArrayString(String key)
    {
        return getValue(key).getArrayString();
    }

    public abstract boolean has(String key);

    public abstract Map<String, Tipy> toMap();

}
