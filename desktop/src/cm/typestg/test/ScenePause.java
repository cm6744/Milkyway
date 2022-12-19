package cm.typestg.test;

import cm.milkyway.Milkyway;
import cm.milkyway.opengl.render.g2d.AreaStatic;
import cm.milkyway.opengl.render.g2d.Text;
import cm.milkywayx.widgetx.base.Scene;
import cm.milkywayx.widgetx.base.SceneManager;
import cm.milkywayx.widgetx.widget.button.Button;
import cm.milkywayx.widgetx.widget.button.ButtonKey;
import cm.milkywayx.widgetx.widget.button.ButtonKeyManager;
import cm.milkywayx.widgetx.widget.choice.Choice;
import cm.milkywayx.widgetx.widget.choice.ChoiceKey;

import static cm.typestg.Scr.scr;

public class ScenePause extends Scene
{

    ButtonKey button1;
    ButtonKey button2;
    Choice choice;
    ButtonKeyManager manager;
    SceneIngame game;

    public ScenePause(SceneIngame ing)
    {
        game = ing;
        init();
    }

    public void init()
    {
        manager = new ButtonKeyManager();
        choice = new ChoiceKey();
        choice.toUpDownDirection();
        choice.box().resize(300, 300);
        choice.box().loc(scr.xc() + 120, scr.yc() + 80);
        choice.setElementsSpace(30);
        button1 = doProcess(0, 0, 200, "Resume");
        button2 = doProcess(0, 0, 200, "exit");
        choice.append(button1, Text.create(Assets.loader.getFont("en_us"), "Back to game"));
        choice.append(button2, Text.create(Assets.loader.getFont("en_us"), "Exit the game"));
        manager.add(button1);
        manager.add(button2);
        choice.vec().setSpeed(2.5);
    }

    private ButtonKey doProcess(double x, double y, double w, String n)
    {
        ButtonKey button1 = new ButtonKey();
        AreaStatic a1 = AreaStatic.dim01(Assets.loader.getTex("button"), 0, 0, 1, 1 / 3.0);
        button1.setTexture(a1, Button.UP);
        AreaStatic a2 = AreaStatic.dim01(Assets.loader.getTex("button"), 0, 1 / 3.0, 1, 1 / 3.0);
        button1.setTexture(a2, Button.HANG);
        AreaStatic a3 = AreaStatic.dim01(Assets.loader.getTex("button"), 0, 2 / 3.0, 1, 1 / 3.0);
        button1.setTexture(a3, Button.DOWN);

        button1.box().loc(x, y);
        button1.box().resize(w, 20);
        button1.append(Text.create(Assets.loader.getFont("en_us"), n));
        return button1;
    }

    public void tickThen()
    {
        choice.tick();
        if(button1.clickOn()) {
            button1.callDown(20);
            game.pause = false;
        }
        if(button2.clickOn()) {
            button2.callDown(20);
            SceneManager.scene(new SceneMenu());
        }
        manager.tick();
        manager.set(choice.getCurrent());
    }

    public void render()
    {
        choice.render();
        Milkyway.glBase.freeAll();
    }

}
