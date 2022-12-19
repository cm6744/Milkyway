package cm.milkyway.lang.text;

public class DataType
{

    public static int intAt(int value, int index)
    {
        return intAt(Data.toString(value), index);
    }

    public static int intAt(String str, int index)
    {
        if(Data.isEmpty(str)) {
            return 0;
        }

        return str.charAt(index) - '0';
    }

    public static int parseInt(String str)
    {
        if(Data.isEmpty(str)) {
            return 0;
        }

        try {
            return Integer.parseInt(str);
        }
        catch(NumberFormatException e) {
            return 0;
        }
    }

    public static double parseDouble(String str)
    {
        if(Data.isEmpty(str)) {
            return 0;
        }

        try {
            return Double.parseDouble(str);
        }
        catch(NumberFormatException e) {
            return 0;
        }
    }

    public static boolean isNumber(String str)
    {
        if(Data.isEmpty(str)) {
            return false;
        }

        try {
            Double.parseDouble(str);
        }
        catch(NumberFormatException e) {
            return false;
        }
        return true;
    }

    public static boolean isBool(String str)
    {
        return Data.compare(str, "true") || Data.compare(str, "false");
    }

    /**
     * 0--false
     * 1--true
     */
    public static boolean parseBoolean1or0(String str, int index)
    {
        return intAt(str, index) == 1;
    }

    public static boolean parseBoolean(String str)
    {
        if(Data.isEmpty(str)) {
            return false;
        }

        return Boolean.parseBoolean(str);
    }

    public static String booleanTo1or0(boolean b)
    {
        return b ? "1" : "0";
    }

    public static int lengthOf(int value)
    {
        return Data.lengthOf(Data.toString(value));
    }

}
