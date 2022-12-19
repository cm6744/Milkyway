package cm.backends.gdx;

import cm.milkyway.opengl.input.InputCallback;
import cm.milkyway.opengl.input.InputRegistry;
import cm.milkyway.lang.container.List;
import com.badlogic.gdx.InputProcessor;

public class IPGdx extends InputRegistry implements InputProcessor
{

    public boolean keyDown(int keycode)
    {
        for(int i = 0; i < callers.size(); i++) {
            callers.get(i).keyTyped(keycode);
        }
        return true;
    }

    public boolean keyUp(int keycode)
    {
        for(int i = 0; i < callers.size(); i++) {
            callers.get(i).keyReleased(keycode);
        }
        return true;
    }

    public boolean keyTyped(char character)
    {
        for(int i = 0; i < callers.size(); i++) {
            callers.get(i).inputText(String.valueOf(character));
        }
        return true;
    }

    public boolean touchDown(int screenX, int screenY, int pointer, int button)
    {
        for(int i = 0; i < callers.size(); i++) {
            callers.get(i).mouseClicked(button);
        }
        return true;
    }

    public boolean touchUp(int screenX, int screenY, int pointer, int button)
    {
        for(int i = 0; i < callers.size(); i++) {
            callers.get(i).mouseReleased(button);
        }
        return true;
    }

    public boolean touchDragged(int screenX, int screenY, int pointer)
    {
        return false;
    }

    public boolean mouseMoved(int screenX, int screenY)
    {
        for(int i = 0; i < callers.size(); i++) {
            callers.get(i).mouseMove(screenX, screenY);
        }
        return true;
    }

    public boolean scrolled(float amountX, float amountY)
    {
        for(int i = 0; i < callers.size(); i++) {
            callers.get(i).mouseScroll(amountY);
        }
        return false;
    }

}
