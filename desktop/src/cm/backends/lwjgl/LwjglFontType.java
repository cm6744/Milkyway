package cm.backends.lwjgl;

import cm.backends.tipy.DefaultTipyReader;
import cm.milkyway.lang.io.Access;
import cm.milkyway.opengl.render.g2d.Font;
import cm.milkyway.opengl.render.g2d.FontType;
import cm.milkyway.tipy.Tipy;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

public class LwjglFontType implements FontType
{

    FreeTypeFontGenerator.FreeTypeFontParameter param;
    FreeTypeFontGenerator generator;
    FileHandle font;
    Tipy desc;
    String chars;

    public LwjglFontType(Access path, Access descPath)
    {
        param = new FreeTypeFontGenerator.FreeTypeFontParameter();
        font = Gdx.files.absolute(path.path());
        desc = new DefaultTipyReader().read(descPath);
        chars = desc.getString("characters");
        generator = new FreeTypeFontGenerator(font);
        //register dispose task

        LwjglEnvironment.autoDispose(this);
    }

    public Font generate(double size)
    {
        param.borderColor = new Color(1, 1, 1, 1);
        param.borderWidth = (float) 0;
        param.color = new Color(1, 1, 1, 1);
        param.size = (int) size;
        param.characters = chars;
        BitmapFont bf = generator.generateFont(param);
        bf.getRegion().getTexture().setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        Font f = new LwjglFont(bf, size);
        //register dispose task

        LwjglEnvironment.autoDispose(f);

        return f;
    }

    public void dispose()
    {
        generator.dispose();
    }

}
