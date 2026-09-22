package Leetcode.FrequencyAndMultisetResoning4;

import java.util.ArrayList;
import java.util.List;

public class LC438findAnagrams {
    public static void main(String[] args) {
        String s = "abab", p = "ab";
        System.out.println(findAnagrams(s, p));
    }
    public static List<Integer> findAnagrams(String s, String p) {
        int l = 0;
        List<Integer> ans = new ArrayList<>();
        for (int i = p.length(); i <= s.length(); i++) {
            String temp = s.substring(l, i);
            if (isAnagram(temp, p)) {
                ans.add(l);
            }
            l++;
        }
        return ans;
    }

    public static boolean isAnagram(String charp, String window){
        int[] and = new int[26];
        for (int i = 0; i < charp.length(); i++) {
            and[charp.charAt(i) - 'a']++;
            and[window.charAt(i) - 'a']--;
        }
        for (int i = 0; i < 26; i++) {
            if(and[i] != 0){
                return false;
            }
        }
        return true;
    }
}
