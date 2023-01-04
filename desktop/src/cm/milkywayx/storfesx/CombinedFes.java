package cm.milkywayx.storfesx;

import cm.milkyway.lang.container.map.Map;
import cm.milkywayx.storfesx.single.*;

public class CombinedFes implements Fes
{

    Map<String, Fes> map = new Map<>();

    public void putInt(String key, int v)
    {
        map.put(key, new IntFes(v));
    }

    public void putDouble(String key, double v)
    {
        map.put(key, new DoubleFes(v));
    }

    public void putBoolean(String key, boolean v)
    {
        map.put(key, new BooleanFes(v));
    }

    public void putString(String key, String v)
    {
        map.put(key, new StringFes(v));
    }

    public void put(String key, Fes v)
    {
        map.put(key, v);
    }

    public int getInt(String key)
    {
        return ((SingleFes) map.get(key)).asInt();
    }

    public double getDouble(String key)
    {
        return ((SingleFes) map.get(key)).asDouble();
    }

    public boolean getBoolean(String key)
    {
        return ((SingleFes) map.get(key)).asBoolean();
    }

    public String getString(String key)
    {
        return ((SingleFes) map.get(key)).asString();
    }

    public Fes get(String key)
    {
        return map.get(key);
    }

    public CombinedFes getChild(String key)
    {
        return (CombinedFes) map.get(key);
    }

}
