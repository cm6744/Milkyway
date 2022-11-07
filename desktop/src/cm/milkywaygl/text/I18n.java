package cm.milkywaygl.text;

public class I18n
{

    static JsonFile map;

    public static void load(String path)
    {
        map = JsonFile.load(path);
        map.openReading();
    }

    public static String translate(String key)
    {
        return map.entry(key).toString();
    }

}
