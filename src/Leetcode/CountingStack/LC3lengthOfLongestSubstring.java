package Leetcode.CountingStack;

import java.util.*;

public class LC3lengthOfLongestSubstring {
    public static void main(String[] args) {
        String s = "ccbbcc";
        System.out.println(lengthOfLongestSubstringArr(s));
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



    //"abcabcbb"
    public static int lengthOfLongestSubstringArr(String s) {
        int[] size = new int[256];
        int l = 0;
        int max = 0;
        for (int r = 0; r < s.length(); r++) {
            char c = s.charAt(r);
            l = Math.max(l, size[c]);
            max = Math.max(max, r - l + 1);
            size[c] = r + 1;
        }
        return max;
    }

    public static int lengthOfLongestSubstringArr1(String s) {
        int[] lastIndex = new int[256];
        int l = 0;
        int max = 0;

        for (int r = 0; r < s.length(); r++) {
            char c = s.charAt(r);

            // Move left boundary forward if the character was seen inside the current window
            l = Math.max(l, lastIndex[c]);

            // Update max with the current valid window length: (r - l + 1)
            max = Math.max(max, r - l + 1);

            // Store 1-based index to distinguish between index 0 and unvisited (default 0)
            lastIndex[c] = r + 1;
        }

        return max;
    }

}
