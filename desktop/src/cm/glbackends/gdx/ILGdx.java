package cm.glbackends.gdx;

import cm.milkywaygl.TaskCaller;
import com.badlogic.gdx.ApplicationListener;

public class ILGdx implements ApplicationListener
{

    @Override
    public void create()
    {
        TaskCaller.init();
    }

    @Override
    public void resize(int i, int i1)
    {
    }

    @Override
    public void render()
    {
        TaskCaller.tick();
        TaskCaller.render();
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
        TaskCaller.dispose();
    }

}

