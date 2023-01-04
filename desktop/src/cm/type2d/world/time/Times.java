package cm.type2d.world.time;

public class Times
{

    public static int tinyTime;
    public static int tinyTimeKp;
    public static int time;
    public static int days;
    public static int totalTinyTimesATime = 120;
    public static int totalTimesADay = 120;
    public static int totalDaysAYear = 120;
    public static int totalTinyTimesADay = totalTimesADay * totalTinyTimesATime;

    public static void upTime()
    {
        tinyTime++;
        tinyTimeKp++;

        if(tinyTime >= totalTinyTimesATime) {
            tinyTime = 0;
            time++;
        }
        if(time >= totalDaysAYear) {
            time = 0;
            days++;
        }
    }

    public static double dayPer()
    {
        return ((tinyTimeKp % (totalTinyTimesADay)) / (double)(totalTinyTimesADay));
    }

    public static double yearPer()
    {
        return ((days % totalDaysAYear) / (double)totalDaysAYear);
    }

}
