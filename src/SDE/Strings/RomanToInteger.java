package SDE.Strings;

import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {
    public static void main(String[] args) {

        String s = "MCMXCIV";
        System.out.println(romanToInt(s));
    }

    public static int romanToInt(String s) {
        Map<Character, Integer> charMap = new HashMap<>();
        charMap.put('I', 1);
        charMap.put('V', 5);
        charMap.put('X', 10);
        charMap.put('L', 50);
        charMap.put('C', 100);
        charMap.put('D', 500);
        charMap.put('M', 1000);

        int res = 0;
        int prev = 0;
        for (int i = s.length() - 1; i >= 0; i--) {
            int curr = charMap.get(s.charAt(i));
            if(curr < prev){
                res -= curr;
            }else {
                res += curr;
            }
            prev = curr;
        }
        return res;
    }






}
