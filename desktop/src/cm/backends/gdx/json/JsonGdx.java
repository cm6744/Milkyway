package cm.backends.gdx.json;

import cm.milkyway.opengl.json.Json;

public class JsonGdx implements Json
{

    public JsonOGdx read(String file)
    {
        return JsonOGdx.load(file);
    }

}
