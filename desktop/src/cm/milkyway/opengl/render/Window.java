package cm.milkyway.opengl.render;

import cm.milkyway.opengl.input.InputCallback;
import cm.milkyway.opengl.input.InputRegistry;
import cm.milkyway.opengl.render.graphics.Graphics2D;
import cm.milkyway.opengl.render.graphics.Graphics3D;

public interface Window
{

    void create(Preference pref);

    Graphics2D getG2D();

    Graphics3D getG3D();

    double width();

    double height();

    double winWidth();

    double winHeight();

    InputRegistry getInputRegistry();

    Preference getPreference();

    boolean isFullScreen();

    void destroy();

}
