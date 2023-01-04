package cm.type25d.tile;

import cm.milkyway.lang.maths.Mth;
import cm.milkyway.opengl.render.graphics.Graphics2D;

public class TileIns
{

    public static final TileIns NULL = new TileIns(null);

    Tile tile;

    double rotation;

    public static final int FLIP_NONE = 0;
    public static final int FLIP_X = 1;
    public static final int FLIP_Y = 2;
    public static final int FLIP_XY = 3;
    int flip;
    int state;

    double offsetX, offsetY;

    public TileIns(Tile t)
    {
        tile = t;
    }

    public void rand4way()
    {
        rotation = Mth.randomInt(0, 3) * 90;
    }

    public void randFlip()
    {
        flip = Mth.randomInt(0, 3);
    }

    public void randState()
    {
        if(this == NULL) return;
        state = Mth.randomInt(0, tile.prop.maxExistState);
    }

    public void randOffset(double x, double y)
    {
        offsetX = Mth.random(-x, x);
        offsetY = Mth.random(-y, y);
    }

    public void setFlip(int f)
    {
        flip = f;
    }

    public int getFlip()
    {
        return flip;
    }

    public void setRotation(double rot)
    {
        rotation = rot;
    }

    public double getRotation()
    {
        return rotation;
    }

    public void setOffset(double x, double y)
    {
        offsetX = x;
        offsetY = y;
    }

    public double getOffsetX()
    {
        return offsetX;
    }

    public double getOffsetY()
    {
        return offsetY;
    }

    public void setState(int st)
    {
        state = st;
    }

    public int getState()
    {
        return state;
    }

    public void render(Graphics2D g, double i, double j, double ofx, double ofy)
    {
        if(this == NULL) return;

        if(flip == FLIP_X || flip == FLIP_XY) {
            g.setFlipX(true);
        }
        if(flip == FLIP_Y || flip == FLIP_XY) {
            g.setFlipY(true);
        }
        g.setRotation(rotation);
        g.draw(tile.prop.tex[state], i * 32 + ofx + offsetX, j * 32 + ofy + offsetY, 32, 32);
        g.setFlipX(false);
        g.setFlipY(false);
        g.setRotation(0);
    }

}
