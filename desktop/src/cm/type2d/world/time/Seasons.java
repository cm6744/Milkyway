package cm.type2d.world.time;

import cm.milkyway.opengl.render.g2d.Color;

public enum Seasons
{
    SPRING("Spring"),
    SUMMER("Summer"),
    AUTUMN("Autumn"),
    WINTER("Winter");

    String n;

    Seasons(String name) {
        n = name;
    }

    public String spellName()
    {
        return n;
    }

    static Color s1 = new Color(1, 1, 1);
    static Color s2 = new Color(1, 0.85, 0.9);
    static Color s3 = new Color(1, 1, 1);
    static Color s4 = new Color(0.7, 0.9, 1);

    public static Color getDye()
    {
        switch(byDay()) {
            case SPRING:
                return s1;
            case SUMMER:
                return s2;
            case AUTUMN:
                return s3;
            case WINTER:
                return s4;
        }
        return s1;
    }

    public static Seasons byDay()
    {
        int d = Times.days % 120;
        if(d >= 0 && d < 30) {
            return SPRING;
        }
        if(d >= 30 && d < 60) {
            return SUMMER;
        }
        if(d >= 60 && d < 90) {
            return AUTUMN;
        }
        if(d >= 90) {
            return WINTER;
        }
        return SPRING;
    }

}
