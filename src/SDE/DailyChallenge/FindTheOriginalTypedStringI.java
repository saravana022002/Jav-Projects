package SDE.DailyChallenge;

import java.util.HashMap;
import java.util.Map;

public class FindTheOriginalTypedStringI {
    public static void main(String[] args) {
        System.out.println(possibleStringCount("abbcccc"));
        System.out.println(possibleStringCount("abcd"));
        System.out.println(possibleStringCount("aaaa"));
        System.out.println(possibleStringCount("ere"));
    }
    public static int possibleStringCount(String word) {
        Character prev = word.charAt(0);
        char curr;
        int count = 1;
        for (int i = 1; i < word.length(); i++) {
            curr = word.charAt(i);
            if(prev.equals(curr)) {
                count++;
            }else {
                prev = curr;
            }
        }
        return count;
    }
}
