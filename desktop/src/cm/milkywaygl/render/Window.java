package cm.milkywaygl.render;

import cm.milkywaygl.input.InputCallback;
import cm.milkywaygl.input.InputRegistry;

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
