package cm.milkyway.opengl.input;

import cm.milkyway.Milkyway;

public class InputMap
{

    public static boolean[] keyState = new boolean[256];
    public static boolean[] mouseState = new boolean[3];

    public static int[] keyTimer = new int[256];
    public static int[] mouseTimer = new int[3];

    public static double mx, my;
    public static String inputString;
    public static double scroll;
    public static int timerNotScroll;

    public static void keyStateUpdate()
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
    }

    public static boolean isOn(Key code)
    {
        if(code == null) {
            return false;
        }
        return code.isMouse() ? mouseState[code.code()] : keyState[code.code()];
    }

    public static int downTime(Key code)
    {
        if(code == null) {
            return 0;
        }
        return code.isMouse() ? mouseTimer[code.code()] : keyTimer[code.code()];
    }

    public static boolean isClick(Key code)
    {
        if(code == null) {
            return false;
        }
        return downTime(code) == 0 && isOn(code);
    }

    public static double x()
    {
        return Milkyway.glBase.calcMX(mx);
    }

    public static double y()
    {
        return Milkyway.glBase.calcMY(my);
    }

    public static double scroll()
    {
        return scroll;
    }

}
