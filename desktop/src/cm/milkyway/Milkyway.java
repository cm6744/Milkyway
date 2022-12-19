package cm.milkyway;

import cm.milkyway.opengl.input.InputRegistry;
import cm.milkyway.opengl.input.Keys;
import cm.milkyway.opengl.io.Accessor;
import cm.milkyway.opengl.json.Json;
import cm.milkyway.opengl.render.*;
import cm.milkyway.opengl.render.g2d.Graphics;
import cm.milkyway.opengl.audio.Audio;

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
