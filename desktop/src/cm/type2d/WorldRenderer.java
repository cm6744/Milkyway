package cm.type2d;

import cm.milkyway.Milkyway;
import cm.milkyway.lang.maths.Mth;
import cm.milkyway.opengl.render.g2d.Color4;
import cm.milkyway.physics.d4simple.Gravity;
import cm.milkyway.physics.d4simple.World;

public class WorldRenderer
{

    World physic = new World();

    public WorldRenderer() {
        physic.dim(0, 420, 800, 300);
    }

    public void render() {

    }

}
