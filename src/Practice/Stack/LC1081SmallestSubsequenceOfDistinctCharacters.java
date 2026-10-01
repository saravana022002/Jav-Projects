package Practice.Stack;

import java.util.*;

// ============================================================================
// LC 1081. Smallest Subsequence of Distinct Characters   [Medium]
// https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/
// Topics: String, Stack, Greedy, Monotonic Stack
// ============================================================================
//
// PROBLEM
//   From the string s, pick a subsequence (keep the original order, skip characters)
//   that contains every distinct letter of s exactly once, and make it the
//   alphabetically smallest such string.
//
// EXAMPLES
//   s = "bcabc"     ->  "abc"
//   s = "cbacdcbc"  ->  "acdb"
//
// CONSTRAINTS
//   1 <= s.length <= 1000,   s has only lowercase English letters
//
// INTUITION
//   Build the answer left to right on a stack. When a smaller letter arrives, a bigger
//   letter on top of the stack would be better placed AFTER it - and that is allowed
//   whenever the bigger letter appears again later in s (we can take that later copy).
//   So pop it. Letters that do not appear again must stay, or they would be lost.
//   Each letter is used once, so a letter already on the stack is simply skipped.
//
// APPROACH (as implemented below)
//   1. map[c] = last index of c in s (filled right to left, so the last one wins).
//   2. Push s[0]; seen[] marks the letters currently on the stack.
//   3. For each later character ch:
//        already on the stack -> skip it
//        while the top is > ch and the top appears again later (map[top] > i):
//          pop it and clear its seen flag
//        push ch and mark it seen
//   4. The stack now holds the answer, bottom to top.
//
// DRY RUN   s = "bcabc" (the input main() uses); last index: a=2, b=3, c=4
//   i=0 'b': push                                     stack [b]
//   i=1 'c': 'b' < 'c', push                          stack [b, c]
//   i=2 'a': pop 'c' (seen again at 4), pop 'b' (at 3), push   stack [a]
//   i=3 'b': push                                     stack [a, b]
//   i=4 'c': push                                     stack [a, b, c]  -> "abc"
//
// COMPLEXITY
//   Time : O(n) - every index is pushed at most once and popped at most once
//   Space: O(1) extra besides the char array - stack, map and seen hold at most 26 letters
//
// GOTCHAS
//   - Only pop a letter that appears again LATER; otherwise it would be missing.
//   - A letter already on the stack is skipped, even if it is smaller than the top.
//
// STATUS
//   Known issue: the method returns stk.toString(), which formats the stack as
//   "[a, b, c]" instead of joining the letters into "abc", so LeetCode would reject it.
//   The algorithm itself is correct. The code is left unchanged on purpose.
//
// RELATED
//   - LC 316 Remove Duplicate Letters - exactly the same problem.
//   - LC 1673 Find the Most Competitive Subsequence - same monotonic-stack idea.
// ============================================================================
public class LC1081SmallestSubsequenceOfDistinctCharacters {
    public static void main(String[] args) {
        // Example 1: prints [a, b, c] - the expected answer is "abc" (see STATUS)
        String s = "bcabc";
        System.out.println(smallestSubsequence(s));
    }

    public static String smallestSubsequence(String s) {
        // last index of every letter (scan right to left, keep the first seen = the last)
        Map<Character, Integer> map = new HashMap<>();
        char[] chars = s.toCharArray();
        for (int i = chars.length - 1; i >= 0; i--) {
            map.putIfAbsent(chars[i], i);
        }
        // seen[x] = letter x is currently on the stack
        boolean[] seen = new boolean[26];
        Stack<Character> stk = new Stack<>();
        stk.push(chars[0]);
        seen[chars[0] - 'a'] = true;
        for (int i = 1; i < chars.length; i++) {
            // skip duplicates
            if (seen[chars[i] - 'a']) {
                continue;
            }
            // a bigger top that comes back later is better placed after chars[i]
            while (!stk.isEmpty() && stk.peek() > chars[i] && map.get(stk.peek()) > i){
                Character c = stk.pop();
                seen[c - 'a'] = false;
            }
            stk.push((chars[i]));
            seen[chars[i] - 'a'] = true;
        }
        // Stack.toString() gives "[a, b, c]", not "abc" (see STATUS)
        return stk.toString();
    }


}
