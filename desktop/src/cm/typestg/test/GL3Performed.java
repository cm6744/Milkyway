package cm.typestg.test;

import cm.milkyway.Milkyway;
import cm.milkyway.opengl.render.GLBase;
import cm.milkyway.opengl.render.g2d.Color4;
import cm.milkyway.opengl.render.g3d.Camera;
import cm.milkyway.opengl.render.g3d.Model3D;
import cm.milkywayx.widgetx.base.ModelBuffer;
import cm.milkyway.lang.container.List;
import cm.milkyway.lang.maths.Mth;

public class GL3Performed
{

    public GLBase gl;

    public List<ModelBuffer> inss = new List<>();
    public double sped = -0.5;
    public boolean newBoj = true;
    Model3D md;
    Model3D roof;

    public GL3Performed(GLBase g)
    {
        gl = g;
    }

    public void init()
    {
        Camera camera = Milkyway.gl3d.camera();
        camera.pos(0, 0, 10);
        camera.lookPos(7, 0, 10);
        camera.sight(1, 2900, 2900);
        md = Milkyway.gl3d.genModel(700, 420, 5);
        md.bind(Assets.loader.getTex("window"));
        roof = Milkyway.gl3d.genModel(700, 2, 420);
        roof.bind(Color4.C0001);
        addPair(0);
        addPair(700);
        addPair(1400);
        addPair(2100);
        addPair(2800);
        addPair(3500);

    }

    private void addPair(float x)
    {
        ModelBuffer b1 = new ModelBuffer();
        b1.pushBind(md.newInstance());
        b1.pos().set(x, 0, -200);
        ModelBuffer b4 = new ModelBuffer();
        b4.pushBind(md.newInstance());
        b4.pos().set(x, 0, 200);
        ModelBuffer b2 = new ModelBuffer();
        b2.pushBind(roof.newInstance());
        b2.pos().set(x, 210, -10);
        ModelBuffer b3 = new ModelBuffer();
        b3.pushBind(roof.newInstance());
        b3.pos().set(x, -210, -10);
        inss.add(b1);
        inss.add(b2);
        inss.add(b3);
        inss.add(b4);
        b1.effect().setRotation(Mth.random() * 30);
    }

    public void tick()
    {
        boolean add = false;
        float lastX = 0;
        for(int i = inss.last(); i >= 0; i--) {
            ModelBuffer in = inss.get(i);
            in.pos().trs(sped, 0, 0);
            if(in.pos().x() <= -700) {
                add = true;
                inss.remove(i);
            }
            if(in.pos().x() > lastX) {
                lastX = (float) in.pos().x();
            }
        }
        if(add && newBoj) {
            addPair(lastX + 700);
        }
    }

    public void render()
    {
        //long time = Platform.getTickMill();
        for(int i = 0; i < inss.size(); i++) {
            inss.get(i).render();
        }
        //System.out.println(Platform.getTickMill() - time);
    }

}