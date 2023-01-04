package cm.type2d.room;

import cm.type2d.world.entity.ActionResult;
import cm.type2d.world.entity.Entity;
import cm.type2d.world.entity.Player;
import cm.type2d.serialize.FesSerializer;
import cm.type2d.world.FixedWorld;

public class RoomDoor extends Entity
{

    boolean click;
    Room nextRoom;
    double plx, ply;

    //the py is player's foot's height
    public RoomDoor(FixedWorld w, Room r, double x, double y, boolean needClick, Room next, double px, double py)
    {
        super(w, r, x, y);
        click = needClick;
        nextRoom = next;
        plx = px;
        ply = py;
    }

    public String name()
    {
        return "entity_room_door";
    }

    public void tickThen()
    {
        Player player = world.player;
        if(!click && bound().interacts(player.bound())) {
            interact(player);
        }
    }

    public ActionResult interact(Player player)
    {
        world.enterWithEffect(nextRoom);
        player.bound().loc(plx, ply - player.bound().h() / 2);
        player.move(nextRoom);
        return ActionResult.SUCCESS;
    }

    public boolean canBeInteracted(Player player)
    {
        return click;
    }

    public boolean canBeFind(Player player)
    {
        return false;
    }

    public int getLevel()
    {
        return -100;
    }

    public FesSerializer<RoomDoor> getSerializer()
    {
        return null;
    }

    public boolean needToSave()
    {
        return false;
    }

}
