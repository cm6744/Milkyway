package cm.backends.lwjgl;

import cm.milkyway.lang.io.AccessLocal;
import cm.milkyway.lang.maths.ScreenMap;
import cm.milkyway.opengl.render.graphics.Shader;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

public class LwjglShader implements Shader
{

    ShaderProgram shader;

    public static LwjglShader file(String vtx, String frg)
    {
        return new LwjglShader(new AccessLocal(vtx).readPass(), new AccessLocal(frg).readPass());
    }

    public LwjglShader(String vtx, String frg)
    {
        shader = new ShaderProgram(vtx, frg);

        LwjglEnvironment.autoDispose(this);
    }

    public void setCurrent()
    {
        LwjglEnvironment.curSha = shader;
    }

    public void dispose()
    {
        shader.dispose();
    }

    public void uniformInt(String u, int v)
    {
        shader.setUniformi(u, v);
    }

    public void uniformDouble(String u, double v)
    {
        shader.setUniformf(u, (float) v);
    }

    public void uniformMap(String w, String h, String u, ScreenMap v)
    {
        uniformInt(w, v.getW());
        uniformInt(h, v.getH());
        shader.setUniform1fv(u, v.getVal(), 0, v.getVal().length);
    }

}
