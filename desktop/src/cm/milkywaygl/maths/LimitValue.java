package cm.milkywaygl.maths;

public class LimitValue
{

    //return the value + crease with limit.
    //if crease > 0, check value < limit, else too.
    public static double ofDouble(double value, double crease, double limit)
    {
        double cache = value + crease;

        if(crease > 0) {
            if(cache < limit) {
                return value + crease;
            }
            else if(cache >= limit) {
                return limit;
            }
        }
        else if(crease < 0) {
            if(cache > limit) {
                return value + crease;
            }
            else if(cache <= limit) {
                return limit;
            }
        }

        return value;
    }

    public static double ofDouble(double value, double crease)
    {
        return ofDouble(value, crease, 0);
    }

    public static int ofInt(int value, int crease, int limit)
    {
        return Maths.toInt(ofDouble(value, crease, limit));
    }

    public static int ofInt(int value, int crease)
    {
        return ofInt(value, crease, 0);
    }

}
