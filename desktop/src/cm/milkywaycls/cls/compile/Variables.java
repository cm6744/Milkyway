package cm.milkywaycls.cls.compile;

import cm.milkywaycls.cls.value.CLSValue;
import cm.milkywaytool.container.List;
import cm.milkywaytool.container.Map;

public class Variables
{

    public List<String> varExists = new List<>();
    public Map<String, CLSValue> varsOrigins = new Map<>();
    public Map<String, CLSValue> vars = new Map<>();

    public void origin(String n, CLSValue v)
    {
        varsOrigins.put(n, v);
        vars.put(n, v);
        varExists.add(n);
    }

    public void change(String n, CLSValue v)
    {
        vars.put(n, v);
    }

    public void flush()
    {
        varExists.iterate((o, i) -> {
            vars.put(o, varsOrigins.get(o));
        }, false);
    }

    public CLSValue get(String n)
    {
        return vars.get(n);
    }

}
