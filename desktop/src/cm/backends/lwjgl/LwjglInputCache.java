package cm.backends.lwjgl;

import cm.milkyway.opengl.input.InputCache;
import cm.milkyway.opengl.input.Key;
import com.badlogic.gdx.Gdx;

import static cm.backends.lwjgl.LwjglEnvironment.cache;

public class LwjglInputCache implements InputCache
{

    public boolean[] keyState = new boolean[256];
    public boolean[] mouseState = new boolean[3];

    public int[] keyTimer = new int[256];
    public int[] mouseTimer = new int[3];

    public double mx, my;
    public String inputString;
    public double scroll;
    public int timerNotScroll;

    public LwjglInputCache()
    {
        bind();
    }

    public void keyStateUpdate()
    {
        for(int i = 0; i < keyTimer.length; i++) {
            if(keyState[i]) {
                keyTimer[i]++;
            }
            else {
                keyTimer[i] = 0;
            }
        }
        for(int i = 0; i < mouseTimer.length; i++) {
            if(mouseState[i]) {
                mouseTimer[i]++;
            }
            else {
                mouseTimer[i] = 0;
            }
        }
        if(timerNotScroll > 0) {
            scroll = 0;
            timerNotScroll = 0;
        }
        if(scroll != 0) {
            timerNotScroll++;
        }
        mx = Gdx.input.getX();
        my = Gdx.input.getY();
    }

    public boolean isOn(Key code)
    {
        if(code == null) {
            return false;
        }
        return code.isMouse() ? mouseState[code.code()] : keyState[code.code()];
    }

    public int downTime(Key code)
    {
        if(code == null) {
            return 0;
        }
        return code.isMouse() ? mouseTimer[code.code()] : keyTimer[code.code()];
    }

    public boolean isClick(Key code)
    {
        if(code == null) {
            return false;
        }
        return downTime(code) == 0 && isOn(code);
    }

    public double x()
    {
        return LwjglEnvironment.context.calcMX(mx);
    }

    public double y()
    {
        return LwjglEnvironment.context.calcMY(my);
    }

    public double scroll()
    {
        return scroll;
    }

}
