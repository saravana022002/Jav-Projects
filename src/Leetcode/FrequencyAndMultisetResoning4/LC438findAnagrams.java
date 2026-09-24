package Leetcode.FrequencyAndMultisetResoning4;

import java.util.ArrayList;
import java.util.List;

public class LC438findAnagrams {
    public static void main(String[] args) {
        String s = "abab", p = "ab";
        System.out.println(findAnagramsOpt(s, p));
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


    public static List<Integer> findAnagramsOpt(String s, String p) {
        if (s.length() < p.length()){
            return null;
        }
        int[] need = new int[26];
        int[] have = new int[26];
        for (int i = 0; i < p.length(); i++) {
            need[p.charAt(i) - 'a']++;
        }

        int matching = 0;
        for (int i = 0; i < 26; i++) {
            if(need[i] == 0){
                matching++;
            }
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            int in = s.charAt(i) - 'a';
            if(need[in] == have[in]){
                matching--;
            }
            have[in]++;
            if(need[in] == have[in]){
                matching++;
            }

            if(i >= p.length()){
                int out = s.charAt(i - p.length()) - 'a';
                if(need[out] == have[out]){
                    matching--;
                }
                have[out]--;
                if(need[out] == have[out]){
                    matching++;
                }
            }
            if(matching == 26){
                ans.add(i - p.length() + 1);
            }
        }
        return ans;

    }
}
