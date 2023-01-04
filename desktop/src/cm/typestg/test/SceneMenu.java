package cm.typestg.test;

import cm.backends.lwjgl.LwjglKey;
import cm.milkyway.eventbus.Eventbus;
import cm.milkyway.opengl.render.g2d.AreaStatic;
import cm.milkyway.opengl.render.g2d.Color;
import cm.milkyway.opengl.render.g2d.Font;
import cm.milkyway.opengl.render.g2d.Text;
import cm.milkyway.opengl.render.graphics.Graphics2D;
import cm.milkywayx.widgetx.scene.Scene;
import cm.milkywayx.widgetx.scene.SceneManager;
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
        Main.performed = new GL3Performed();
        Main.performed.init();
        manager = new ButtonKeyManager();
        choice = new ChoiceKey();
        choice.toUpDown();
        choice.setKey(LwjglKey.key("up"), LwjglKey.key("down"));
        choice.disableMove();
        choice.box().resize(300, 300);
        choice.box().loc(120, 240 + 50);
        choice.setElementsSpace(30);
        button1 = doProcess(0, 0, 200, "Start");
        button3 = doProcess(0, 0, 200, "Option");
        button2 = doProcess(0, 0, 200, "exit");
        choice.append(button1, Text.create(Assets.loader.getFont("en_us"), Color.C1111, ""));
        choice.append(button3, Text.create(Assets.loader.getFont("en_us"), Color.C1111, ""));
        choice.append(button2, Text.create(Assets.loader.getFont("en_us"), Color.C1111, ""));
        manager.add(button1);
        manager.add(button3);
        manager.add(button2);
        choice.vec().setSpeed(2.5);
    }

    private ButtonKey doProcess(double x, double y, double w, String n)
    {
        ButtonKey button1 = new ButtonKey(LwjglKey.key("z"));
        AreaStatic a1 = AreaStatic.dim01(Assets.loader.getTex("button"), 0, 0, 1, 1 / 3.0);
        button1.setTexture(a1, Button.UP);
        AreaStatic a2 = AreaStatic.dim01(Assets.loader.getTex("button"), 0, 1 / 3.0, 1, 1 / 3.0);
        button1.setTexture(a2, Button.HANG);
        AreaStatic a3 = AreaStatic.dim01(Assets.loader.getTex("button"), 0, 2 / 3.0, 1, 1 / 3.0);
        button1.setTexture(a3, Button.DOWN);

        button1.box().loc(x, y);
        button1.box().resize(w, 20);
        button1.append(Text.create(Assets.loader.getFont("en_us"), Color.C1111, n));
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
            Eventbus.dispose();
        }
        manager.tick();
        manager.set(choice.getCurrent());
    }

    public void render(Graphics2D g)
    {
        g.getContext().clear();
        g.draw(Assets.loader.getTex("stg6bg"), 0, 0, 800, 600);
        performed.render();
        choice.render(g);
        //manager.render();
        Font f = Assets.loader.getFont("en_us");
        Color c = Color.C1111;
        g.drawText(f, c, "RD: " + Data.toString(60), 200, 570, false);
        g.drawText(f, c, "TK: " + Data.toString(60), 300, 570, false);
        g.getContext().paint();
    }

}
