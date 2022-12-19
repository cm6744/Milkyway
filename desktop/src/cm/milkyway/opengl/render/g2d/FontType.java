package cm.milkyway.opengl.render.g2d;

import cm.milkyway.lang.Disposable;

/**
 * Json field: "characters"
 */
public interface FontType extends Disposable
{

    Font2 generate(Color4 col, double size);

}
