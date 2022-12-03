package cm.milkywaygl.json;

import cm.milkywaytool.container.Map;

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
