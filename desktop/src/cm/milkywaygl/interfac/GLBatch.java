package cm.milkywaygl.interfac;

import cm.milkywaygl.render.GL;

public abstract class GLBatch implements GLHeavy
{

    boolean beginState = false;

    protected abstract void nbegin();

    protected abstract void nend();

    public void begin()
    {
        //safe
        if(beginState) {
            return;
        }
        GL.gl.begin(this);
        GL.gl.viewPort();
        nbegin();
        beginState = true;
    }

    public void end()
    {
        if(!beginState) {
            return;
        }
        nend();
        GL.gl.end(this);
        GL.gl.viewPortBack();
        beginState = false;
    }

}
