package cm.milkyway.opengl.render.graphics;

import cm.milkyway.lang.Disposable;
import cm.milkyway.lang.maths.ScreenMap;

public interface Shader extends Disposable
{

    void setCurrent();

    void uniformInt(String u, int v);

    void uniformDouble(String u, double v);

    void uniformMap(String w, String h, String u, ScreenMap v);

}
