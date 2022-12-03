package cm.milkywaygl.render.g2d;

import cm.milkywaytool.Disposable;

/**
 * Json field: "characters"
 */
public interface FontType extends Disposable
{

    Font2 generate(Color4 col, double size);

}
