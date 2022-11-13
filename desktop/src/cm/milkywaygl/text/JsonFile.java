package cm.milkywaygl.text;

import cm.milkywaygl.Platform;
import cm.milkywaygl.resource.Path;
import cm.milkywaygl.resource.Resource;
import cm.milkywaygl.util.container.List;
import cm.milkywaygl.util.container.Map;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;

import java.io.FileWriter;
import java.io.IOException;

public class JsonFile
{

    private final String path;
    //nullable
    private final JsonFile parent;

    private JsonValue nativeJson;

    //nullables
    Boolean v_bool;
    Double v_double;
    Integer v_int;
    String v_str;

    private JsonFile(String pth)
    {
        path = pth;
        parent = null;
        nativeJson = new JsonReader().parse(Path.readerJar(path));
    }

    private JsonFile(JsonFile pare)
    {
        path = pare.path;
        parent = pare;
    }

    public static JsonFile load(String path)
    {
        return new JsonFile(path);
    }

    //GET

    public JsonFile entry(JsonValue v1)
    {
        if(v1 == null) {
            return null;
        }

        JsonFile jf = new JsonFile(this);

        if(v1.isString()) {
            jf.v_str = v1.asString();
        }
        else if(v1.isDouble()) {
            jf.v_double = v1.asDouble();
        }
        else if(v1.isNumber()) {
            jf.v_int = v1.asInt();
        }
        else if(v1.isBoolean()) {
            jf.v_bool = v1.asBoolean();
        }
        else if(v1.isObject()) {
            jf.nativeJson = v1;
        }
        else {
            return null;
        }

        return jf;
    }

    public JsonFile entry(int index)
    {
        JsonValue v1 = nativeJson.get(index);
        return entry(v1);
    }

    public JsonFile entry(String key)
    {
        JsonValue v1 = nativeJson.get(key);
        return entry(v1);
    }

    public Map<String, JsonFile> toMap()
    {
        Map<String, JsonFile> map = new Map<>();

        for(JsonValue value : nativeJson) {
            map.put(value.name, entry(value));
        }

        return map;
    }

    public int toInt()
    {
        return v_int;
    }

    public double toDouble()
    {
        return v_double;
    }

    public String toString()
    {
        return v_str;
    }

    public boolean toBool()
    {
        return v_bool;
    }

}
