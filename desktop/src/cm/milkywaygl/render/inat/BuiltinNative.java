package cm.milkywaygl.render.inat;

import cm.milkywaygl.render.nnat.TaskCaller;
import com.badlogic.gdx.ApplicationListener;

class BuiltinNative implements ApplicationListener
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

