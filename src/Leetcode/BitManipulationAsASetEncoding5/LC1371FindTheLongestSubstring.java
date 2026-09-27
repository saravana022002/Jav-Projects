package Leetcode.BitManipulationAsASetEncoding5;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class LC1371FindTheLongestSubstring {
    public static void main(String[] args) {
        String s = "leet";
        System.out.println(findTheLongestSubstringOpt(s));
    }

    public static int findTheLongestSubstring(String s) {
        int length = s.length();
        int max = 0;
        for (int i = 0; i < length; i++) {
            int[] count = new int[5];
            for (int j = i; j < length; j++) {
                int k = "aeiou".indexOf(s.charAt(j));
                if(k > -1) {
                    count[k]++;
                }
                if(count[0] % 2 == 0 && count[1] % 2 == 0  && count[2] % 2 == 0  && count[3] % 2 == 0  && count[4] % 2 == 0){
                    max = Math.max(max, j - i);
                }
            }
        }
        return max;
    }



    static final int[] BIT = new int[26];
    static {
        BIT[0] = 1;  BIT['e' - 'a'] = 2;  BIT['i' - 'a'] = 4;
        BIT['o' - 'a'] = 8;  BIT['u' - 'a'] = 16;          // consonants stay 0
    }
    public static int findTheLongestSubstringOpt(String s) {
        int res = 0;
        int mask = 0;
        int[] map = new int[32];
        Arrays.fill(map, -2);
        map[0] = -1;
        for (int i = 0; i < s.length(); i++) {
            int k = "aeiou".indexOf(s.charAt(i));
            if(k > -1){
                mask ^=  BIT[s.charAt(i) - 'a'];
            }
            if(map[mask] > -2){
                res = Math.max(res, i - map[mask]);
            }else {
                map[mask] = i;
            }
        }
        return res;
    }
}
