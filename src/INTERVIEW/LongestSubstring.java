package INTERVIEW;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

public class LongestSubstring {


//    Given a string s, find the length of the longest substring without duplicate characters.
//
//            Example 1:
//    Input: s = "abcabcbb"
//    Output: 3
//    Explanation: "abc" or "bca" or "cab"
//
//    Example 2:
//    Input: s = "bbbbb"
//    Output: 1
//
//    Example 3:
//    Input: s = "pwwkew"
//    Output: 3
//    Explanation: "wke" or "kew"
    public static void main(String[] args) {
        String s = "pwwkew";
        System.out.println(lengthOfLongestSubstring(s));
    }

    private static int longestSubstring(String s) {
        Map<Character, Integer> charIndex = new HashMap<>();
        char[] chars = s.toCharArray();
        int l = 0;
        int r = 0;
        int max = Integer.MIN_VALUE;
        while (r < chars.length){
            if(charIndex.containsKey(chars[r])){
                l = charIndex.get(chars[l]) + 1;
                max = Math.max(max, r - l + 1);
                charIndex.put(chars[l], l);
            }
            charIndex.put(chars[r], r);
            r++;
        }
        return max;
    }


    public static int lengthOfLongestSubstring(String s) {
        Queue<Character> hello = new ArrayDeque<>();
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            while(!hello.isEmpty() && hello.contains(s.charAt(i))){
                hello.poll();
            }
            hello.offer(s.charAt(i));
            max = Math.max(max, hello.size());
        }
        return max;
    }






}
