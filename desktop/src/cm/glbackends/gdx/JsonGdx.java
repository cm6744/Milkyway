package cm.glbackends.gdx;

import cm.milkywaygl.json.Json;

public class JsonGdx implements Json
{

    public JsonOGdx read(String file)
    {
        return JsonOGdx.load(file);
    }

}
