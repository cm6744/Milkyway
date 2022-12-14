package cm.milkyway.lang.text;

import cm.milkyway.lang.container.list.List;

public class Data
{

    public static String BLANK = "";
    public static String SPACE = " ";

    public static String listToOne(List<String> lst)
    {
        StringBuilder builder = new StringBuilder();
        for(int i = 0; i < lst.size(); i++) {
            builder.append(lst.get(i));
        }
        return builder.toString();
    }

    public static char charAt(String str, int index)
    {
        return str.charAt(index);
    }

    public static int lengthOf(String str)
    {
        if(str == null) {
            return 0;
        }
        return str.length();
    }

    /**
     * check str is not "", " " or null
     */
    public static boolean isEmpty(String str)
    {
        if(str == null) {
            return true;
        }

        return compare(str, BLANK) || compare(str, SPACE) || lengthOf(str) == 0;
    }

    public static boolean compare(String s1, String s2)
    {
        if(s1 == null || s2 == null) {
            return false;
        }

        return s1.equals(s2);
    }

    /**
     * find first {find}'s index degree {str}
     */
    public static int indexOf(String str, String find)
    {
        if(str == null) {
            return -1;
        }

        return str.indexOf(find);
    }

    public static int indexOfLast(String str, String find)
    {
        if(str == null) {
            return -1;
        }

        return str.lastIndexOf(find);
    }


    /**
     * replace all {find} in {str} with {rep}
     */
    public static String replace(String str, String find, String rep)
    {
        return str.replace(find, rep);
    }

    //find sub locate

    /**
     * example:
     * subString("0123456", 0, 5) -> return "1234"
     */
    public static String subString(String str, int a, int b)
    {
        if(a == -1) {
            return BLANK;
        }

        return str.substring(a + 1, b);//加一才能正确切割
    }

    /**
     * example:
     * nextString("0123456", 3) -> return "456"
     */
    public static String nextString(String str, int a)
    {
        if(a == -1) {
            return BLANK;
        }

        return str.substring(a + 1);//加一才能正确切割
    }

    /**
     * example:
     * lastString("0123456", 3) -> return "012"
     */
    public static String lastString(String str, int a)
    {
        if(a == -1) {
            return BLANK;
        }

        return str.substring(0, a);//加一才能正确切割
    }

    //find sub

    public static String subString(String str, String spikeA, String spikeB)
    {
        int a = indexOf(str, spikeA);
        int b = indexOf(str, spikeB);
        if(a == -1 || b == -1) {
            return BLANK;
        }
        return subString(str, a, b);
    }

    public static String subString2Dr(String str, String spikeA, String spikeB)
    {
        int a = indexOf(str, spikeA);
        int b = indexOfLast(str, spikeB);
        if(a == -1 || b == -1) {
            return BLANK;
        }
        return subString(str, a, b);
    }

    public static String nextString(String str, String spike)
    {
        return nextString(str, indexOf(str, spike));
    }

    public static String lastString(String str, String spike)
    {
        return lastString(str, indexOf(str, spike));
    }

    //checking & turning

    public static boolean startWith(String str, String comp)
    {
        return str.startsWith(comp);
    }

    public static boolean startIgnSpace(String str, String comp)
    {
        return str.trim().startsWith(comp);
    }

    public static boolean contain(String str, String cont)
    {
        return str.contains(cont);
    }

    public static String compress(String str)
    {
        return str.replace(SPACE, BLANK);
    }

    /**
     * example:
     * cutBy("0:1:2", ":") -> return {"0", "1", "2"}
     */
    public static String[] cutBy(String str, String cut)
    {
        return str.split(cut);
    }

    //to string

    public static String toString(int n)
    {
        return Integer.toString(n);
    }

    public static String toString(double n)
    {
        return Double.toString(n);
    }

    public static String toString(double n, int scale)
    {
        return String.format("%." + scale + "f", n);
    }

    public static String toString(boolean b)
    {
        return String.valueOf(b);
    }

}
