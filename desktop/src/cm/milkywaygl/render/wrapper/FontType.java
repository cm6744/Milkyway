package cm.milkywaygl.render.wrapper;

import cm.milkywaygl.resource.Path;
import cm.milkywaygl.resource.Resource;
import cm.milkywaygl.text.Data;
import cm.milkywaygl.text.JsonFile;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

/**
 * Json field: "characters"
 */
public class FontType
{

    FreeTypeFontGenerator.FreeTypeFontParameter param;
    FreeTypeFontGenerator generator;
    FileHandle font;
    JsonFile desc;

    public FontType(String path, String descPath)
    {
        param = new FreeTypeFontGenerator.FreeTypeFontParameter();
        font = Path._libJar(path);
        desc = JsonFile.load(descPath);
        generator = new FreeTypeFontGenerator(font);
    }

    public Font2 generate(Color4 inner, Color4 outer, double border, double size)
    {
        param.borderColor = outer._nativeColor;
        param.borderWidth = (float) border;
        param.color = inner._nativeColor;
        param.size = (int) size;
        param.characters = FreeTypeFontGenerator.DEFAULT_CHARS + desc.getString("characters");
        BitmapFont bf = generator.generateFont(param);
        return new Font2(bf);
    }

}
