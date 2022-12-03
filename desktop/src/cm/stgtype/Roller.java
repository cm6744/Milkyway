package cm.stgtype;

import cm.milkywaygl.Milkyway;
import cm.milkywaygl.render.g2d.BufferTex;
import cm.milkywaylib.base.Scene;
import cm.milkywaytool.maths.Mth;

import static cm.stgtype.Scr.scr;

public class Roller extends Scene
{

    BufferTex bufferTex;
    BufferTex overlay;
    double spd;
    double yOffset;
    double w, h;

    public void rolling(BufferTex tex, double speed)
    {
        bufferTex = tex;
        spd = speed;
    }

    public void overlay(BufferTex over)
    {
        overlay = over;
    }

    public void overlayRender()
    {
        Milkyway.glBase.curState().opacity(Mth.min(1, time() * 0.05));
        Milkyway.gl2d.dim(overlay, scr);
    }

    public void rollingRender()
    {
        Milkyway.glBase.curState().opacity(Mth.min(1, time() * 0.05));
        Milkyway.gl2d.dim(bufferTex, scr, 0, yOffset, w, h);
    }

    public void tickThen()
    {
        w = bufferTex.w();
        h = bufferTex.h() / 2;
        yOffset -= spd;
        if(yOffset > h) {
            yOffset -= h;
        }
        if(yOffset < 0) {
            yOffset += h;
        }
    }

    public void render()
    {
    }

}
