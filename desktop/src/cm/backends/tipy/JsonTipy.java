package cm.backends.tipy;

import cm.milkyway.tipy.*;
import cm.milkyway.lang.container.list.List;
import cm.milkyway.lang.container.map.Map;
import cm.milkyway.lang.io.Access;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

class JsonTipy extends Tipy
{

    private JsonValue value;

    //original entry
    public JsonTipy(Access pth)
    {
        try {
            value = new JsonReader().parse(new FileInputStream(pth.path()));
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    //from parent
    private JsonTipy(JsonValue ov)
    {
        value = ov;
    }

    public JsonTipy()
    {
        value = new JsonValue(JsonValue.ValueType.object);
    }

    public Tipy put(String key)
    {
        Tipy tipy = new JsonTipy();
        put(key, tipy);
        return tipy;
    }

    public Tipy object(String key)
    {
        Tipy tipy;
        put(key, tipy = new JsonTipy());
        return tipy;
    }

    public void put(String key, Tipy o)
    {
        value.addChild(key, ((JsonTipy) o).value);
    }

    public void put(String key, TipyValue v)
    {
        if(v instanceof StringValue)
            value.addChild(key, new JsonValue(v.getString()));
        else if(v instanceof DoubleValue)
            value.addChild(key, new JsonValue(v.getDouble()));
        else if(v instanceof BooleanValue)
            value.addChild(key, new JsonValue(v.getBoolean()));
        else if(v instanceof IntValue)
            value.addChild(key, new JsonValue(v.getInt()));
        else
            throw new RuntimeException("Not support writing TipyValue type: " + v.type().name());
    }

    public void write(Access access)
    {
        String s = value.prettyPrint(JsonWriter.OutputType.json, -1);
        access.write(List.of(s));
    }

    private Tipy n0enter(JsonValue v1)
    {
        return new JsonTipy(v1);
    }

    public Tipy get(String key)
    {
        JsonValue v1 = value.get(key);
        return n0enter(v1);
    }

    public TipyValue getValue(String key)
    {
        JsonValue v = value.get(key);
        switch(v.type()) {
            case booleanValue:
                return new BooleanValue(v.asBoolean());
            case doubleValue:
                return new DoubleValue(v.asDouble());
            case longValue:
                return new IntValue(v.asInt());
            case stringValue:
                return new StringValue(v.asString());
            case array:
                switch(v.get(0).type())
                {
                    case longValue:
                        return new IntArrayValue(v.asIntArray());
                    case stringValue:
                        return new StringArrayValue(v.asStringArray());
                    case doubleValue:
                        return new DoubleArrayValue(v.asDoubleArray());
                    case booleanValue:
                        return new BooleanArrayValue(v.asBooleanArray());
                    default:
                        throw new RuntimeException("Unknown type: " + v);
                }
        }
        return null;
    }

    public boolean has(String key)
    {
        return value.hasChild(key);
    }

    public Map<String, Tipy> toMap()
    {
        Map<String, Tipy> map = new Map<>();

        for(JsonValue value : value) {
            map.put(value.name, n0enter(value));
        }

        return map;
    }

}
