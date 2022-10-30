package cm.milkywaygl.render.inat;

import cm.milkywaygl.render.nnat.InputCallback;
import com.badlogic.gdx.InputProcessor;

class BuiltinInput implements InputProcessor
{

    InputCallback caller;

    public BuiltinInput(InputCallback cb)
    {
        caller = cb;
    }

    public boolean keyDown(int keycode)
    {
        if(caller != null) {
            caller.keyTyped(keycode);
        }
        return true;
    }

    public boolean keyUp(int keycode)
    {
        if(caller != null) {
            caller.keyReleased(keycode);
        }
        return true;
    }

    public boolean keyTyped(char character)
    {
        if(caller != null) {
            caller.inputText(String.valueOf(character));
        }
        return true;
    }

    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        if(caller != null) {
            caller.mouseClicked(button);
        }
        return true;
    }

    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        if(caller != null) {
            caller.mouseReleased(button);
        }
        return true;
    }

    public boolean touchDragged(int screenX, int screenY, int pointer)
    {
        return false;
    }

    public boolean mouseMoved(int screenX, int screenY)
    {
        if(caller != null) {
            caller.mouseMove(screenX, screenY);
        }
        return true;
    }

    public boolean scrolled(float amountX, float amountY)
    {
        if(caller != null) {
            caller.mouseScroll(amountY);
        }
        return true;
    }

}
