package cm.glbackends.gdx;

import cm.milkywaygl.input.Key;
import cm.milkywaygl.input.Keys;
import cm.milkywaytool.container.Map;
import com.badlogic.gdx.Input;

public class KeysGdx implements Keys
{

    public Key mouse(String s)
    {
        return mouseMap.get(s);
    }

    public Key key(String s)
    {
        return keyMap.get(s);
    }

    static Map<String, Key> keyMap = new Map<>();
    static Map<String, Key> mouseMap = new Map<>();

    static {
        putKey("a", Input.Keys.A);
        putKey("b", Input.Keys.B);
        putKey("c", Input.Keys.C);
        putKey("d", Input.Keys.D);
        putKey("e", Input.Keys.E);
        putKey("f", Input.Keys.F);
        putKey("g", Input.Keys.G);
        putKey("h", Input.Keys.H);
        putKey("i", Input.Keys.I);
        putKey("j", Input.Keys.J);
        putKey("k", Input.Keys.K);
        putKey("l", Input.Keys.L);
        putKey("m", Input.Keys.M);
        putKey("n", Input.Keys.N);
        putKey("o", Input.Keys.O);
        putKey("p", Input.Keys.P);
        putKey("q", Input.Keys.Q);
        putKey("r", Input.Keys.R);
        putKey("s", Input.Keys.S);
        putKey("t", Input.Keys.T);
        putKey("u", Input.Keys.U);
        putKey("v", Input.Keys.V);
        putKey("w", Input.Keys.W);
        putKey("x", Input.Keys.X);
        putKey("y", Input.Keys.Y);
        putKey("z", Input.Keys.Z);
        putKey("left", Input.Keys.LEFT);
        putKey("up", Input.Keys.UP);
        putKey("right", Input.Keys.RIGHT);
        putKey("down", Input.Keys.DOWN);
        putKey("shift", Input.Keys.SHIFT_LEFT);
        putKey("ctrl", Input.Keys.CONTROL_LEFT);
        putKey("1", Input.Keys.NUM_1);
        putKey("2", Input.Keys.NUM_2);
        putKey("3", Input.Keys.NUM_3);
        putKey("4", Input.Keys.NUM_4);
        putKey("5", Input.Keys.NUM_5);
        putKey("6", Input.Keys.NUM_6);
        putKey("7", Input.Keys.NUM_7);
        putKey("8", Input.Keys.NUM_8);
        putKey("9", Input.Keys.NUM_9);
        putKey("0", Input.Keys.NUM_0);
        putKey("~", Input.Keys.GRAVE);
        putKey("esc", Input.Keys.ESCAPE);
        putMouse("left", Input.Buttons.LEFT);
        putMouse("right", Input.Buttons.RIGHT);
    }

    private static void putKey(String n, int c)
    {
        keyMap.put(n, new KeyGdx(c, false));
    }

    private static void putMouse(String n, int c)
    {
        mouseMap.put(n, new KeyGdx(c, true));
    }

}
