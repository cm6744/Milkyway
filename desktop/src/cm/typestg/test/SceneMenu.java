package cm.typestg.test;

import cm.milkyway.Milkyway;
import cm.milkyway.TaskCaller;
import cm.milkyway.opengl.render.g2d.AreaStatic;
import cm.milkyway.opengl.render.g2d.Text;
import cm.milkywayx.widgetx.base.Scene;
import cm.milkywayx.widgetx.base.SceneManager;
import cm.milkywayx.widgetx.widget.button.Button;
import cm.milkywayx.widgetx.widget.button.ButtonKey;
import cm.milkywayx.widgetx.widget.button.ButtonKeyManager;
import cm.milkywayx.widgetx.widget.choice.Choice;
import cm.milkywayx.widgetx.widget.choice.ChoiceKey;
import cm.milkyway.lang.text.Data;

import static cm.typestg.test.Main.performed;

public class SceneMenu extends Scene
{

    ButtonKey button1;
    ButtonKey button3;
    ButtonKey button2;
    Choice choice;
    ButtonKeyManager manager;

    public void init()
    {
        Main.performed = new GL3Performed(Milkyway.glBase);
        Main.performed.init();
        manager = new ButtonKeyManager();
        choice = new ChoiceKey();
        choice.toUpDownDirection();
        choice.disableMove();
        choice.box().resize(300, 300);
        choice.box().loc(120, 240 + 50);
        choice.setElementsSpace(30);
        button1 = doProcess(0, 0, 200, "Start");
        button3 = doProcess(0, 0, 200, "Option");
        button2 = doProcess(0, 0, 200, "exit");
        choice.append(button1, Text.create(Assets.loader.getFont("en_us"), ""));
        choice.append(button3, Text.create(Assets.loader.getFont("en_us"), ""));
        choice.append(button2, Text.create(Assets.loader.getFont("en_us"), ""));
        manager.add(button1);
        manager.add(button3);
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
        performed.tick();
        choice.tick();
        if(button1.clickOn()) {
            button1.callDown(20);
            SceneManager.scene(new SceneIngame());
        }
        if(button2.clickOn()) {
            button2.callDown(20);
            TaskCaller.dispose();
        }
        manager.tick();
        manager.set(choice.getCurrent());
    }

    public void render()
    {
        Milkyway.gl2d.dim(Assets.loader.getTex("stg6bg"), 0, 0, 800, 600);
        performed.render();
        choice.render();
        //manager.render();
        Milkyway.glBase.state().font(Assets.loader.getFont("en_us"));
        Milkyway.glText.text("RD: " + Data.toString(TaskCaller.renderSync().fps()), 200, 570, false);
        Milkyway.glText.text("TK: " + Data.toString(TaskCaller.tickSync().fps()), 300, 570, false);
        Milkyway.glBase.freeAll();
    }

}
