package cm.type2d.logic;

public class Times
{

    public static int tinyTime;
    public static int time;
    public static int days;
    public static int totalTinyTimesATime = 120;
    public static int totalTimesADay = 120;
    public static int totalDaysAYear = 120;

    public static void upTime()
    {
        tinyTime++;

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
        return ((time % totalTimesADay) / (double)totalTimesADay);
    }

    public static double yearPer()
    {
        return ((days % totalDaysAYear) / (double)totalDaysAYear);
    }

}
