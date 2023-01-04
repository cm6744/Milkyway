package cm.typestg.test;

import cm.milkyway.opengl.render.graphics.Graphics2D;
import cm.typestg.Roller;

public class SzyiRoller extends Roller
{

    public void render(Graphics2D g)
    {
        rollingRender(g);
        overlayRender(g);
    }

}
