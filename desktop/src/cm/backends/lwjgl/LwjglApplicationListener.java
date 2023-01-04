package cm.backends.lwjgl;

import cm.milkyway.eventbus.Eventbus;
import com.badlogic.gdx.ApplicationListener;

public class LwjglApplicationListener implements ApplicationListener
{

    @Override
    public void create()
    {
        Eventbus.init();
    }

    @Override
    public void resize(int i, int i1)
    {
    }

    @Override
    public void render()
    {
        Eventbus.tick();
        Eventbus.render();
    }

    @Override
    public void pause()
    {
    }

    @Override
    public void resume()
    {
    }

    @Override
    public void dispose()
    {
        Eventbus.dispose();
    }

}
