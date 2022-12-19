package cm.type2d.logic;

import cm.milkyway.Milkyway;
import cm.milkyway.opengl.render.g2d.Color4;

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

    static Color4 s1 = Milkyway.graphics.newColor(1, 1, 1);
    static Color4 s2 = Milkyway.graphics.newColor(1, 0.85, 0.9);
    static Color4 s3 = Milkyway.graphics.newColor(1, 1, 1);
    static Color4 s4 = Milkyway.graphics.newColor(0.7, 0.9, 1);

    public static Color4 getDye()
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
