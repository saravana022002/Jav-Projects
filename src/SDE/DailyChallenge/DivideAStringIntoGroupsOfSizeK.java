package SDE.DailyChallenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DivideAStringIntoGroupsOfSizeK {
    public static void main(String[] args) {
        String s = "abcdefghik";
        int k = 3;
        char fill = 'x';
        System.out.println(Arrays.toString(divideString(s, k, fill)));
    }
    public static String[] divideString(String s, int k, char fill) {
        int n = s.length();
        List<String> str = new ArrayList<>();
        int curr = 0;
        while (curr < n){
            int end = Math.min(curr + k, n);
            str.add(s.substring(curr, end));
            curr = curr + k;
        }
        String last = str.get(str.size() - 1);
        if(last.length() < k){
            last += String.valueOf(fill).repeat(k - last.length());
            str.set(str.size() - 1, last);
        }
        return str.toArray(new String[0]);
    }
}
