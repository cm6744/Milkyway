package cm.milkywaygl.input;

import cm.milkywaygl.render.GL;

public class InputMap
{

    //PROBABLY NATIVE INVOKES
    public static boolean[] keyState = new boolean[256];
    public static boolean[] mouseState = new boolean[3];

    //END REGION
    public static int[] keyTimer = new int[256];
    public static int[] mouseTimer = new int[3];

    public static double mx, my;
    public static String inputString;

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
        return (mx - GL.gl.cornerX) / GL.gl.zoom;
    }

    public static double y()
    {
        return (my - GL.gl.cornerY) / GL.gl.zoom;
    }

}
