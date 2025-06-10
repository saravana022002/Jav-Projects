package SDE.Strings;

import java.util.HashMap;
import java.util.Map;

public class CountAndSay {
    public static void main(String[] args) {
        System.out.println(countAndSayItr(321));
    }

    static Map<Integer, String> memo = new HashMap<>();

    public static String countAndSayItr(int n) {
        if (n == 1) return "1";
        String result = "1";
        for (int i = 2; i <= n; i++) {
            result = computeStr(result);
        }
        return result;
    }



    // top down
    public static String countAndSay(int n) {
        if (n == 1) return "1";
        if (memo.containsKey(n)) {
            return memo.get(n);
        }

        String prev = countAndSay(n - 1);
        String next = computeStr(prev);
        memo.put(n, next);
        return next;
    }

    // bottom up
    public static String computeRLE(int i, int n, String string){
        if(i == n) {
            return string;
        }
        if (memo.containsKey(i))
            return memo.get(i);
        String next = computeStr(string);
        memo.put(i, next);
        return computeRLE(i + 1, n, next);
    }




    public static String computeStr(String string){
        Character ch = string.charAt(0);
        int count = 1;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 1; i < string.length(); i++) {
            while (i < string.length() && ch == string.charAt(i)){
                count++;
                i++;
            }
            if(i < string.length()) {
                stringBuilder.append(count);
                stringBuilder.append(ch);
                ch = string.charAt(i);
                count = 1;
            }
        }
        stringBuilder.append(count);
        stringBuilder.append(ch);
        return stringBuilder.toString();
    }
}
