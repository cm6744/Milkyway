package cm.milkywaygl.render.wrapper;

import cm.milkywaygl.resource.Path;
import cm.milkywaygl.resource.Resource;
import cm.milkywaygl.text.Data;
import cm.milkywaygl.text.JsonFile;
import cm.milkywaygl.util.container.List;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

/**
 * Json field: "characters"
 */
public class FontType
{

    static List<FontType> management = new List<>();
    static List<Font2> generated = new List<>();

    public static void dispose()
    {
        for(int i = 0; i < management.size(); i++) {
            management.get(i).generator.dispose();
        }
        for(int i = 0; i < generated.size(); i++) {
            generated.get(i)._nativeFont.dispose();
        }
    }

    FreeTypeFontGenerator.FreeTypeFontParameter param;
    FreeTypeFontGenerator generator;
    FileHandle font;
    JsonFile desc;
    String chars;

    public FontType(String path, String descPath)
    {
        param = new FreeTypeFontGenerator.FreeTypeFontParameter();
        font = Path._libJar(path);
        desc = JsonFile.load(descPath);
        chars = desc.entry("characters").toString();
        generator = new FreeTypeFontGenerator(font);
        management.add(this);
    }

    public Font2 generate(Color4 col, double size)
    {
        param.borderColor = col._nativeColor;
        param.borderWidth = (float) 0;
        param.color = col._nativeColor;
        param.size = (int) size;
        param.characters = chars;
        BitmapFont bf = generator.generateFont(param);
        Font2 f2f = new Font2(bf, size);
        generated.add(f2f);
        return f2f;
    }

}
