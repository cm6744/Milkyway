package cm.milkywaygl.text;

import cm.milkywaygl.Platform;
import cm.milkywaygl.resource.Path;
import cm.milkywaygl.resource.Resource;
import cm.milkywaygl.util.container.List;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.badlogic.gdx.utils.JsonWriter;

import java.io.FileWriter;
import java.io.IOException;

public class JsonFile
{

    private final String path;
    private JsonValue nativeJson;
    private boolean openedRd;

    private JsonFile(String pth)
    {
        path = pth;
    }

    public static JsonFile load(String path)
    {
        return new JsonFile(path);
    }

    public void openReading()
    {
        if(!openedRd) {
            nativeJson = new JsonReader().parse(Path.readerJar(path));
        }
        openedRd = true;
    }

    //GET

    public JsonEntry entry(String key)
    {
        openReading();

        JsonValue v1 = nativeJson.get(key);
        if(v1 == null) {
            return new JsonEntry(null);
        }

        if(v1.isString()) {
            return new JsonEntry(v1.asString());
        }
        else if(v1.isDouble()) {
            return new JsonEntry(v1.asDouble());
        }
        else if(v1.isNumber()) {
            return new JsonEntry(v1.asInt());
        }
        else if(v1.isBoolean()) {
            return new JsonEntry(v1.asBoolean());
        }

        return new JsonEntry(null);
    }

}
