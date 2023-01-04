package cm.type25d.tile;

import cm.backends.lwjgl.LwjglEnvironment;
import cm.backends.lwjgl.LwjglKey;
import cm.milkyway.lang.maths.shapes.Rect;
import cm.milkyway.lang.maths.shapes.Vec2;
import cm.milkyway.opengl.input.InputMap;
import cm.milkyway.opengl.render.g2d.Color;
import cm.milkyway.opengl.render.graphics.Graphics2D;

public class Player
{

    public Rect render = Rect.normal();
    public Rect bound = Rect.normal();
    public TileMap map;

    private final Vec2 accel = Vec2.normal();
    private final Vec2 speed = Vec2.normal();

    public Player()
    {
        render.resize(32, 32);
        bound.resize(24, 24);
    }

    public void tick()
    {
        accel.refresh();

        if(InputMap.isOn(LwjglKey.key("d"))) {
            accel.add(5, 0);
        }
        if(InputMap.isOn(LwjglKey.key("a"))) {
            accel.add(-5, 0);
        }
        if(InputMap.isOn(LwjglKey.key("s"))) {
            accel.add(0, 5);
        }
        if(InputMap.isOn(LwjglKey.key("w"))) {
            accel.add(0, -5);
        }
        speed.add(accel);
        speed.mul(0.65, 0.65);
        bound.mvLoc(speed.x(), speed.y());

        map.roll(-bound.x(), -bound.y());
        render.loc(bound.x() + map.rox + LwjglEnvironment.context.width() / 2,
                   bound.y() + map.roy + LwjglEnvironment.context.height() / 2);
    }

    public void render(Graphics2D g)
    {
        g.drawRect(Color.C1111, render);
    }

}
