package cm.glbackends.gdx;

import cm.milkywaygl.Milkyway;
import cm.milkywaygl.Platform;
import cm.milkywaygl.TaskCaller;
import cm.milkywaygl.render.GL3D;
import cm.milkywaygl.render.GLShape;
import cm.milkywaygl.render.GLText;

public class GdxGLCreator
{

    public static void create()
    {
        //GDX IMPL*
        GLGdxBase glBase;
        GLGdx2D gl2d;
        GL3D gl3d;
        GLShape glShape;
        GLText glText;

        glBase = new GLGdxBase();
        glBase.updateFrameData();

        gl2d = new GLGdx2D(glBase);
        gl3d = new GLGdx3D(glBase);
        glShape = new GLGdxShape(glBase);
        glText = new GLGdxText(glBase, gl2d);

        Milkyway.glBase = glBase;
        Milkyway.gl2d = gl2d;
        Milkyway.gl3d = gl3d;
        Milkyway.glShape = glShape;
        Milkyway.glText = glText;
        //GDX IMPL*

        gl2d.init();
        gl3d.init();
        glShape.init();

        TaskCaller.register(() -> {
            gl2d.dispose();
            gl3d.dispose();
            glShape.dispose();

            Platform.log("Milkyway was destroyed. Rendering disabled.");
        }, TaskCaller.DISPOSE);

        Platform.log("Milkyway was created. Rendering enabled.");
    }

}
