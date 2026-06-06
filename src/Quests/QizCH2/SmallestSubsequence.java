package Quests.QizCH2;

import java.util.*;

public class SmallestSubsequence {
    public static void main(String[] args) {
        String s = "bcabc";
        System.out.println(smallestSubsequence(s));
    }

    public static String smallestSubsequence(String s) {
        Map<Character, Integer> map = new HashMap<>();
        char[] chars = s.toCharArray();
        for (int i = chars.length - 1; i >= 0; i--) {
            map.putIfAbsent(chars[i], i);
        }
        boolean[] seen = new boolean[26];
        Stack<Character> stk = new Stack<>();
        stk.push(chars[0]);
        seen[chars[0] - 'a'] = true;
        for (int i = 1; i < chars.length; i++) {
            // skip duplicates
            if (seen[chars[i] - 'a']) {
                continue;
            }
            while (!stk.isEmpty() && stk.peek() > chars[i] && map.get(stk.peek()) > i){
                Character c = stk.pop();
                seen[c - 'a'] = false;
            }
            stk.push((chars[i]));
            seen[chars[i] - 'a'] = true;
        }
        return stk.toString();
    }


}
