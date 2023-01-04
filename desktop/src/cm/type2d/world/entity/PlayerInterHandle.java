package cm.type2d.world.entity;

import cm.backends.lwjgl.LwjglKey;
import cm.milkyway.lang.maths.VecMth;
import cm.milkyway.opengl.input.InputMap;
import cm.milkyway.opengl.input.Key;
import cm.milkywayx.inventoryx.Stack;

public class PlayerInterHandle
{

    Entity goal;
    static Key F = LwjglKey.key("f");
    static Key Q = LwjglKey.key("q");
    static Key CTRL = LwjglKey.key("ctrl");
    static Key ML = LwjglKey.mouse("left");
    int interDelay;

    public void dropHelp(Stack src, Player player, boolean all)
    {
        if(!src.isEmpty()) {
            Stack s1;
            if(all) {
                s1 = src.copy();
                src.set(0);
            }
            else {
                s1 = src.split(1);
            }
            new EntityItem(player.world, player.room, s1, player.bound().x(), player.bound().y()).spawn();
            player.play(1);
            interDelay = 10;
        }
    }

    public void onTick(Player player)
    {
        interDelay--;
        if(interDelay > 0) {
            return;
        }

        if(InputMap.isClick(ML) && goal == null) {
            Stack hold = player.inv.hold;

            if(hold.isEmpty()) {
                Entity inter = player.world.getEntity(InputMap.x(), InputMap.y());
                if(inter != null) {
                    goal = inter;
                    interDelay = 10;
                }
            }
        }

        if(InputMap.isClick(Q)) {
            Stack hang = player.inv.hang;
            dropHelp(hang, player, InputMap.isOn(CTRL));
        }

        if((InputMap.isClick(F) || InputMap.downTime(F) > 40) && goal == null)
        {
            Entity nearest = player.world.getNearestEntity();
            if(nearest != null) {
                goal = nearest;
                interDelay = 10;
            }
        }

        if(goal != null && player.actTime < 0) {
            if(VecMth.distanceBetweenAB(player.bound(), goal.bound()) >= 100) {
                if(goal.bound().x() > player.bound().x()) {
                    player.taskRight = true;
                }
                else {
                    player.taskLeft = true;
                }
            }
            else {
                player.taskLeft = player.taskRight = false;
                goal.interact(player);
                goal = null;
            }
        }
    }

}
