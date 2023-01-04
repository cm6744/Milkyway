package cm.type2d.registry;

import cm.type2d.world.entity.LivingAttribute;

public class RegisterEntityAttribute extends Register<LivingAttribute>
{

    public void call()
    {
        register("entity_player", new LivingAttribute(100, 100, 100, 100, 0.005, 0.01, 8));
    }

}
