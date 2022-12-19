package cm.milkyway.opengl.json;

import cm.milkyway.lang.container.Map;

/** May not be implemented in some backends! */
public interface JsonObject
{

    JsonObject entry(int index);

    JsonObject entry(String key);

    Map<String, JsonObject> toMap();

    int asInt();

    double asDouble();

    String asString();

    boolean asBool();

    boolean isNumber();

    boolean isBool();

    boolean isString();

    boolean isObject();

}
