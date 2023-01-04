package cm.backends;

import java.util.StringJoiner;

public class DebugLog
{

    public static void print(int... ints)
    {
        StringJoiner j = new StringJoiner(",", "[", "]");
        for(int i : ints) {
            j.add(String.valueOf(i));
        }
        System.out.println(j);
    }

}
