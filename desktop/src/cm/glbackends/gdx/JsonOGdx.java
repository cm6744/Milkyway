package cm.glbackends.gdx;

import cm.glbackends.BasePath;
import cm.milkywaygl.json.JsonObject;
import cm.milkywaytool.container.Map;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

public class JsonOGdx implements JsonObject
{

    private final String path;
    //nullable
    private final JsonOGdx parent;

    private JsonValue nativeJson;

    //nullables
    boolean v_bool;
    double v_double;
    String v_str;

    //type of value.
    int type;

    public static final int NONE = 0;
    public static final int NUM = 1;
    public static final int BOOL = 2;
    public static final int STRING = 3;
    public static final int OBJECT = 4;

    private JsonOGdx(String pth)
    {
        path = pth;
        parent = null;
        nativeJson = new JsonReader().parse(BasePath.readerJar(path));
    }

    private JsonOGdx(JsonOGdx pare)
    {
        path = pare.path;
        parent = pare;
    }

    public static JsonOGdx load(String path)
    {
        return new JsonOGdx(path);
    }

    //GET

    private JsonOGdx entry(JsonValue v1)
    {
        if(v1 == null) {
            return null;
        }

        JsonOGdx jf = new JsonOGdx(this);

        if(v1.isString()) {
            jf.v_str = v1.asString();
            jf.type = STRING;
        }
        else if(v1.isDouble()) {
            jf.v_double = v1.asDouble();
            jf.type = NUM;
        }
        else if(v1.isNumber()) {
            jf.v_double = v1.asInt();
            jf.type = NUM;
        }
        else if(v1.isBoolean()) {
            jf.v_bool = v1.asBoolean();
            jf.type = BOOL;
        }
        else if(v1.isObject()) {
            jf.nativeJson = v1;
            jf.type = OBJECT;
        }
        else {
            jf.type = NONE;
        }

        return jf;
    }

    public JsonOGdx entry(int index)
    {
        JsonValue v1 = nativeJson.get(index);
        return entry(v1);
    }

    public JsonOGdx entry(String key)
    {
        JsonValue v1 = nativeJson.get(key);
        return entry(v1);
    }

    public Map<String, JsonObject> toMap()
    {
        Map<String, JsonObject> map = new Map<>();

        for(JsonValue value : nativeJson) {
            map.put(value.name, entry(value));
        }

        return map;
    }

    public int asInt()
    {
        return (int) asDouble();
    }

    public double asDouble()
    {
        return v_double;
    }

    public String asString()
    {
        return v_str;
    }

    public boolean asBool()
    {
        return v_bool;
    }

    public boolean isNumber()
    {
        return type == NUM;
    }

    public boolean isBool()
    {
        return type == BOOL;
    }

    public boolean isString()
    {
        return type == STRING;
    }

    public boolean isObject()
    {
        return type == OBJECT;
    }

}
