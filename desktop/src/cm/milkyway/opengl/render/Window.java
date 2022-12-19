package cm.milkyway.opengl.render;

import cm.milkyway.opengl.input.InputCallback;
import cm.milkyway.opengl.input.InputRegistry;

public interface Window
{

    void create(Preference pref, InputCallback callback);

    double width();

    double height();

    double winWidth();

    double winHeight();

    InputRegistry getInputRegistry();

    Preference getPreference();

    boolean isFullScreen();

    void destroy();

}
