package cm.milkywaygl;

import cm.milkywaygl.input.InputRegistry;
import cm.milkywaygl.input.Keys;
import cm.milkywaygl.io.Accessor;
import cm.milkywaygl.json.Json;
import cm.milkywaygl.render.*;
import cm.milkywaygl.render.g2d.Graphics;
import cm.milkywaygl.sound.Audio;

public class Milkyway
{

    public static Window window;

    public static GL2D gl2d;
    public static GL3D gl3d;
    public static GLShape glShape;
    public static GLText glText;
    public static GLBase glBase;

    public static InputRegistry input;
    public static Keys keys;

    public static Audio audio;
    public static Graphics graphics;
    public static Json json;
    public static Accessor accessor;

}
