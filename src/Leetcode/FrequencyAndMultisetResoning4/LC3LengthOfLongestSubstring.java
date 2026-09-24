package Leetcode.FrequencyAndMultisetResoning4;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class LC3LengthOfLongestSubstring {
    public static void main(String[] args) {
        String s = "Sasdaf";
        System.out.println(lengthOfLongestSubstring(s));
    }

    public static int lengthOfLongestSubstring(String s) {
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            Set<Character> ch = new HashSet<>();
            for (int j = i; j < s.length(); j++) {
                if (!ch.add(s.charAt(i))) {
                    break;
                }
                max = Math.max(max, j - i + 1);
            }
        }

        return max;
    }


    public static int lengthOfLongestSubstringOpt(String s) {
        boolean[] seen = new boolean[128];
        int i = 0, max = 0;
        for (int j = 0; j < s.length(); j++) {
            int in = s.charAt(j);
            while (seen[in]){
                int out = s.charAt(i);
                seen[out] = false;
                i++;
            }
            seen[in] = true;
            max = Math.max(max, j - i + 1);
        }
        return max;
    }
}


