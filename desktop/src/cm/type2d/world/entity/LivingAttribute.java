package cm.type2d.world.entity;

public class LivingAttribute
{

    public double health, hunger, attack, sanity, thirst;
    public double hungerRate, thirstRate;
    public double hungerMax, thirstMax, healthMax, sanityMax;

    public LivingAttribute(double maxHeal, double maxHun, double maxSan, double maxThr, double rateHun, double rateThir, double attackDamage)
    {
        health = healthMax = maxHeal;
        hunger = hungerMax = maxHun;
        thirst = thirstMax = maxThr;
        sanity = sanityMax = maxSan;
        thirstRate = rateThir;
        hungerRate = rateHun;
        attack = attackDamage;
    }

    public LivingAttribute copy()
    {
        return new LivingAttribute(healthMax, hungerMax, sanityMax, thirstMax, hungerRate, thirstRate, attack);
    }

}
