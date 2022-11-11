package cm.milkywaygl.render;

import cm.milkywaygl.interfac.GLBatch;
import cm.milkywaygl.Platform;
import cm.milkywaygl.render.nativegl.Context;
import cm.milkywaygl.render.wrapper.Color4;
import cm.milkywaygl.render.wrapper.Font2;
import cm.milkywaygl.render.wrapper.FontType;
import com.badlogic.gdx.Gdx;

import static com.badlogic.gdx.graphics.GL20.*;

public class GL
{

    public static GL2 gl2;
    public static GL3 gl3;
    public static GL4 gl4;
    public static GL2F gl2f;
    public static GL gl;

    public double zoom, zoomedW, zoomedH;
    public double cornerX, cornerY;
    public boolean initialized;

    GLBatch blocking;

    State mutable = new State();
    State store = new State();

    public static void create()
    {
        gl = new GL();
        gl2 = new GL2(gl);
        gl3 = new GL3(gl);
        gl4 = new GL4(gl);

        gl2f = new GL2F(gl, gl2);

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
        gl4.init();

        gl.initialized = true;

        Platform.log("GL calculate current zoom scale: " + zoom);
    }

    public void dispose()
    {
        gl2.dispose();
        gl3.dispose();
        gl4.dispose();
        FontType.dispose();

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

    public void clear()
    {
        Gdx.gl.glClearColor(0, 0, 0, 1f);
        Gdx.gl.glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT | GL_STENCIL_BUFFER_BIT);
    }

    public void viewPort()
    {
        Gdx.gl.glViewport((int) cornerX, (int) cornerY, (int) zoomedW, (int) zoomedH);
    }

    public void viewPortBack()
    {
        Gdx.gl.glViewport(0, 0, (int) Context.winWidth(), (int) Context.winHeight());
    }

    //BLOCKING

    //when your render method end, invoke this.
    public void freeAll()
    {
        ensure(null);
        blocking = null;
    }

    public GLBatch curBlocking()
    {
        return blocking;
    }

    //ensure the given batch is begun.
    //if given batch is null, it will not begin a new batch.
    public void ensure(GLBatch batch)
    {
        if(blocking != batch) {
            if(batch == null) {
                blocking.end();
            }
            else if(blocking == null) {
                batch.begin();
            }
            else {
                blocking.end();
                batch.begin();
            }
        }
    }

    public void begin(GLBatch batch)
    {
        if(blocking != null) {
            Platform.error("Cannot use multi renderer at same time.");
        }
        if(blocking == batch) {
            Platform.error("Cannot begin batches multi times.");
        }
        blocking = batch;
    }

    public void end(GLBatch batch)
    {
        if(blocking == null) {
            Platform.error("Cannot end batches multi times.");
        }
        if(blocking != batch) {
            Platform.error("Cannot use multi renderer at same time.");
        }
        blocking = null;
    }

    //STATEMENT

    public void cacheState()
    {
        store.copy(mutable);
    }

    public void readState()
    {
        mutable.copy(store);
    }

    public State curState()
    {
        return mutable;
    }

    public State copyCurState()
    {
        return new State().copy(mutable);
    }

    public void setCurState(State s)
    {
        mutable = s;
    }

    public static class State
    {

        public double rotation;
        public double lx, ly;
        public double alpha = 1.0;
        public boolean mirror;
        public Color4 colorNow = Color4.WHITE;
        public Font2 fontNow = null;

        public void rotate(double degree, double x, double y)
        {
            rotation += degree;
            lx = x;
            ly = y;
        }

        public void clearRotation()
        {
            rotation = 0;
            lx = ly = 0;
        }

        public void opacity(double v)
        {
            alpha = v;
        }

        public void clearOpacity()
        {
            alpha = 1;
        }

        public void mirrored(boolean mir)
        {
            mirror = mir;
        }

        public void color(Color4 c4f)
        {
            colorNow = c4f;
        }

        public void font(Font2 f2f)
        {
            fontNow = f2f;
        }

        public State copy(State state)
        {
            rotation = state.rotation;
            lx = state.lx;
            ly = state.ly;
            alpha = state.alpha;
            mirror = state.mirror;
            colorNow = state.colorNow;
            fontNow = state.fontNow;
            return this;
        }

    }

}
