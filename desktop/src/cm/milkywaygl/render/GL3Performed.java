package cm.milkywaygl.render;

import cm.milkywaygl.render.nnat.TaskCaller;
import cm.milkywaygl.render.wrapper.Color4;
import cm.milkywaygl.util.IntBuffer;
import cm.milkywaygl.util.container.List;
import cm.milkywaylib.linklib.ModelBuffer;
import cm.test.Assets;

public class GL3Performed implements GLObject
{

    public GL gl;

    public List<ModelBuffer> inss = new List<>();
    public double sped = 0.2;
    public boolean newBoj = true;
    IntBuffer md;
    IntBuffer roof;

    public GL3Performed(GL g)
    {
        gl = g;
    }

    public void init()
    {
        GL.gl3.cameraPos(0, 0, 10);
        GL.gl3.cameraLookPos(-7, 0, 0);
        GL.gl3.cameraSight(1, 5800);
        md = GL.gl3.genModel(700, 420, 5);
        GL.gl3.modelBindTex(md, Assets.win);
        roof = GL.gl3.genModel(700, 2, 420);
        GL.gl3.modelBindColor(roof, Color4.BLACK);
    }

    private void addPair(float x)
    {
        ModelBuffer b1 = new ModelBuffer();
        b1.pushBind(GL.gl3.createObj(md));
        b1.pos().vec(x, 0, -200);
        ModelBuffer b4 = new ModelBuffer();
        b4.pushBind(GL.gl3.createObj(md));
        b4.pos().vec(x, 0, 200);
        ModelBuffer b2 = new ModelBuffer();
        b2.pushBind(GL.gl3.createObj(roof));
        b2.pos().vec(x, 210, -10);
        ModelBuffer b3 = new ModelBuffer();
        b3.pushBind(GL.gl3.createObj(roof));
        b3.pos().vec(x, -210, -10);
        inss.add(b1);
        inss.add(b2);
        inss.add(b3);
        inss.add(b4);
    }

    public void tick()
    {
        if(TaskCaller.globalTime == 0) {
            init();
            addPair(0);
            addPair(-700);
            addPair(-1400);
            addPair(-2100);
            addPair(-2800);
            addPair(-3500);
            addPair(-4200);
            addPair(-4900);
            addPair(-5600);
        }
        boolean add = false;
        float lastX = 0;
        for(int i = inss.last(); i >= 0; i--) {
            ModelBuffer in = inss.get(i);
            in.pos().mvVec(sped, 0, 0);
            if(in.pos().x() >= 700) {
                add = true;
                inss.remove(i);
            }
            if(in.pos().x() < lastX) {
                lastX = (float) in.pos().x();
            }
        }
        if(add && newBoj) {
            addPair(lastX - 700);
        }
    }

    public void dispose()
    {
    }

    public void begin()
    {

    }

    public void flush()
    {

    }

    public void end()
    {

    }

    public void render()
    {
        for(int i = 0; i < inss.size(); i++) {
            inss.get(i).render();
        }
    }

}
