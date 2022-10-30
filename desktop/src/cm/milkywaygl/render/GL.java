package cm.milkywaygl.render;

import cm.milkywaygl.Platform;
import cm.milkywaygl.render.inat.Context;
import cm.milkywaygl.render.nnat.GL8;
import cm.milkywaygl.render.wrapper.Color4;
import cm.milkywaygl.render.wrapper.Font2;
import com.badlogic.gdx.Gdx;

import static com.badlogic.gdx.graphics.GL20.GL_COLOR_BUFFER_BIT;
import static com.badlogic.gdx.graphics.GL20.GL_DEPTH_BUFFER_BIT;

public class GL implements GLObject
{

    public static GL2 gl2;
    public static GL3 gl3;
    public static GL8 gl8;
    public static GL gl;

    public double zoom, zoomedW, zoomedH;
    public double cornerX, cornerY;
    public boolean initialized;
    public boolean blocked;
    State mutable = new State();
    State store = new State();

    public static void create()
    {
        gl = new GL();
        gl2 = new GL2(gl);
        gl3 = new GL3(gl);
        gl8 = new GL8(gl2);
        gl.init();

        Platform.log("GL was created. Rendering opened.");
    }

    public void init()
    {
        if(!Context.isFullScreen()) {
            gl.zoom = Context.winHeight() / Context.height();
        }
        else {
            gl.zoom = 1;
        }

        gl.zoomedH = Context.height() * gl.zoom;
        gl.zoomedW = Context.width() * gl.zoom;
        gl.cornerX = (Context.winWidth() - (zoomedW)) / 2;
        gl.cornerY = 0;

        gl2.init();
        gl3.init();
        gl.initialized = true;

        Platform.log("GL calculate current zoom scale: " + zoom);
    }

    public void dispose()
    {
        gl2.dispose();
        gl3.dispose();

        Platform.log("GL was disposed.");
    }

    public double calcP(double value)
    {
        return (value);
    }

    public double calcY(double value)
    {
        return (Context.height() - value);
    }

    public double calcX(double value)
    {
        return (value);
    }

    public void viewPort()
    {
        Gdx.gl.glViewport((int) cornerX, (int) cornerY, (int) zoomedW, (int) zoomedH);
    }

    public void viewPortBack()
    {
        Gdx.gl.glViewport(0, 0, (int) Context.winWidth(), (int) Context.winHeight());
    }

    public void begin()
    {
        if(blocked) {
            Platform.error("Cannot use multi renderer at same time.");
        }
        blocked = true;
    }

    public void end()
    {
        if(!blocked) {
            Platform.error("Cannot use multi renderer at same time.");
        }
        blocked = false;
    }

    //EFFECTS

    public void rotate(double degree, double x, double y)
    {
        mutable.rotation += degree;
        mutable.lx = x;
        mutable.ly = y;
    }

    public void clearRotation()
    {
        mutable.rotation = 0;
        mutable.lx = mutable.ly = 0;
    }

    public void opacity(double v)
    {
        mutable.alpha = v;
    }

    public void clearOpacity()
    {
        mutable.alpha = 1;
    }

    public void dyeDraw(Color4 v)
    {
        mutable.dye = v;
    }

    public void clearDyeDraw()
    {
        mutable.dye = null;
    }

    public void cacheState()
    {
        store.copy(mutable);
    }

    public void readState()
    {
        mutable.copy(store);
    }

    public State pullState()
    {
        return mutable;
    }

    public State copyState()
    {
        return new State().copy(mutable);
    }

    public void pushState(State s)
    {
        mutable = s;
    }

    public void mirrored(boolean mir)
    {
        mutable.mirror = mir;
    }

    public void color(Color4 c4f)
    {
        mutable.colorNow = c4f;
    }

    public void font(Font2 f2f)
    {
        mutable.fontNow = f2f;
    }

    public void clear()
    {
        Gdx.gl.glClearColor(0, 0, 0, 1f);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    }

    public static class State
    {

        double rotation;
        double lx, ly;
        double alpha = 1.0;
        boolean mirror;
        Color4 dye = Color4.BLACK;
        Color4 colorNow = Color4.WHITE;
        Font2 fontNow = null;

        public State copy(State state)
        {
            rotation = state.rotation;
            lx = state.lx;
            ly = state.ly;
            alpha = state.alpha;
            mirror = state.mirror;
            dye = state.dye;
            colorNow = state.colorNow;
            fontNow = state.fontNow;
            return this;
        }

    }

}
