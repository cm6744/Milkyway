package cm.milkywaygl.render.nnat;

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

    public static boolean keyOn(int code)
    {
        return keyState[code];
    }

    public static boolean mouseOn(int code)
    {
        return mouseState[code];
    }

    public static void keyStateUpdate()
    {
        for(int i = 0; i < keyTimer.length; i++) {
            if(keyOn(i)) {
                keyTimer[i]++;
            }
            else {
                keyTimer[i] = 0;
            }
        }
        for(int i = 0; i < mouseTimer.length; i++) {
            if(mouseOn(i)) {
                mouseTimer[i]++;
            }
            else {
                mouseTimer[i] = 0;
            }
        }
    }

    public static int keyDownTime(int code)
    {
        return keyTimer[code];
    }

    public static boolean keyClick(int code)
    {
        return keyDownTime(code) == 0 && keyOn(code);
    }

    public static int mouseDownTime(int code)
    {
        return mouseTimer[code];
    }

    public static boolean mouseClick(int code)
    {
        return mouseDownTime(code) == 0 && mouseOn(code);
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
