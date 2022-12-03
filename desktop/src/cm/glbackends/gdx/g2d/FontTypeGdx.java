package cm.glbackends.gdx.g2d;

import cm.milkywaygl.TaskCaller;
import cm.glbackends.BasePath;
import cm.glbackends.gdx.JsonOGdx;
import cm.milkywaygl.render.g2d.Color4;
import cm.milkywaygl.render.g2d.Font2;
import cm.milkywaygl.render.g2d.FontType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class FontTypeGdx implements FontType
{

    FreeTypeFontGenerator.FreeTypeFontParameter param;
    FreeTypeFontGenerator generator;
    FileHandle font;
    JsonOGdx desc;
    String chars;

    public FontTypeGdx(String path, String descPath)
    {
        TaskCaller.checkPreInit();

        param = new FreeTypeFontGenerator.FreeTypeFontParameter();
        font = Gdx.files.absolute(BasePath.jar(path));
        desc = JsonOGdx.load(descPath);
        chars = desc.entry("characters").asString();
        generator = new FreeTypeFontGenerator(font);
        //register dispose task
        TaskCaller.register(this::dispose, TaskCaller.DISPOSE);
    }

    public Font2 generate(Color4 col, double size)
    {
        param.borderColor = ((Color4Gdx) col)._nativeColor;
        param.borderWidth = (float) 0;
        param.color = ((Color4Gdx) col)._nativeColor;
        param.size = (int) size;
        param.characters = chars;
        BitmapFont bf = generator.generateFont(param);
        bf.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        Font2 f2f = new Font2Gdx(bf, size);
        //register dispose task
        TaskCaller.register(bf::dispose, TaskCaller.DISPOSE);
        return f2f;
    }

    public void dispose()
    {
        generator.dispose();
    }

}
