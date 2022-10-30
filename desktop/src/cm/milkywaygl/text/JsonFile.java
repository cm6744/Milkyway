package cm.milkywaygl.text;

import cm.milkywaygl.resource.Path;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

public class JsonFile
{

    private final JsonValue nativeJson;

    private JsonFile(JsonValue nat)
    {
        nativeJson = nat;
    }

    public static JsonFile load(String path)
    {
        return new JsonFile(new JsonReader().parse(Path.readerJar(path)));
    }

    public JsonFile getObject(String key)
    {
        return new JsonFile(nativeJson.getChild(key));
    }

    public int getInt(String key)
    {
        return nativeJson.getInt(key);
    }

    public double getDouble(String key)
    {
        return nativeJson.getDouble(key);
    }

    public String getString(String key)
    {
        return nativeJson.getString(key);
    }

    public boolean getBool(String key)
    {
        return nativeJson.getBoolean(key);
    }

}
