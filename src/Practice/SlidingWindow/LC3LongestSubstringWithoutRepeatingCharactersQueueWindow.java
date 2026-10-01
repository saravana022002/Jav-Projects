package Practice.SlidingWindow;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

// ============================================================================
// LC 3. Longest Substring Without Repeating Characters   [Medium]
// https://leetcode.com/problems/longest-substring-without-repeating-characters/
// Topics: Hash Table, String, Sliding Window
// ============================================================================
//
// PROBLEM
//   Given a string s, return the length of the longest substring (a contiguous piece of s)
//   in which no character appears more than once.
//
// EXAMPLES
//   s = "abcabcbb"  ->  3     "abc" (also "bca", "cab")
//   s = "bbbbb"     ->  1     "b"
//   s = "pwwkew"    ->  3     "wke"; "pwke" is not contiguous, so it does not count
//
// CONSTRAINTS
//   0 <= s.length <= 10^5,   s consists of English letters, digits, symbols and spaces
//
// INTUITION
//   Slide a window over s that never holds a repeated character. When the next character c
//   is already inside the window, no valid window can contain both copies, so the left
//   edge has to move past the old copy of c: drop characters from the front until it is
//   gone, then let c in. After each step the window is the longest repeat-free substring
//   that ends at the current character, so the answer is the largest window ever seen.
//
// APPROACHES IN THIS FILE
//   A. lengthOfLongestSubstring() - queue as the window - O(n * m)   <- main() runs this one
//   B. longestSubstring()         - private, never called, unfinished HashMap attempt -
//                                   WRONG (see STATUS)
//
// APPROACH (as implemented below, lengthOfLongestSubstring)
//   1. The queue hello holds the current window, oldest character at the front.
//   2. For each s.charAt(i): while hello already contains it, poll() the front.
//   3. offer() the character at the back - the window is repeat-free again.
//   4. max = max(max, hello.size()).
//
// DRY RUN   s = "pwwkew"
//   i=0 'p'  no clash                hello = [p]       max = 1
//   i=1 'w'  no clash                hello = [p,w]     max = 2
//   i=2 'w'  clash: poll p, poll w   hello = [w]       max = 2
//   i=3 'k'  no clash                hello = [w,k]     max = 2
//   i=4 'e'  no clash                hello = [w,k,e]   max = 3
//   i=5 'w'  clash: poll w           hello = [k,e,w]   max = 3
//   return 3
//
// STATUS
//   Known issue in the private helper longestSubstring (never called): it moves l using
//   charIndex.get(chars[l]) instead of the repeated character chars[r], and it updates max
//   only when a repeat is found. "abc" returns Integer.MIN_VALUE (correct: 3) and "dvdf"
//   returns 2 (correct: 3). lengthOfLongestSubstring, which main() runs, is correct.
//   The code is left unchanged on purpose.
//
// COMPLEXITY   (lengthOfLongestSubstring)
//   Time : O(n * m), m = min(n, number of distinct characters) - each character is offered
//          once and polled at most once, but every hello.contains() scans the whole queue
//   Space: O(m) - the queue never holds a character twice
//
// GOTCHAS
//   - Substring, not subsequence: in "pwwkew" the answer is 3, not 4 ("pwke").
//   - Poll until the old copy itself has left - everything before it must go too, or the
//     window would not be contiguous.
//   - Empty string -> 0 (the loop never runs).
//   - Queue.contains is linear; a HashSet or a last-seen-index map makes the check O(1).
//
// SEE ALSO
//   LC3LongestSubstringWithoutRepeatingCharactersThreeApproaches.java - queue, HashSet window
//     and last-seen-index HashMap versions
//   src/Leetcode/CountingStack/LC3lengthOfLongestSubstring.java - this queue window plus a
//     last-index array version
//   src/Leetcode/FrequencyAndMultisetResoning4/LC3LengthOfLongestSubstring.java - brute force
//     and a boolean[128] window version
//
// RELATED
//   - LC 1695 Maximum Erasure Value - the same repeat-free window on an int array, maximising
//     the window's sum instead of its length.
//   - LC 2405 Optimal Partition of String - also reacts to "this character is already in the
//     current piece", but starts a new piece instead of shrinking.
// ============================================================================
public class LC3LongestSubstringWithoutRepeatingCharactersQueueWindow {


    public static void main(String[] args) {
        // Example 3 - expected output: 3 ("wke")
        String s = "pwwkew";
        System.out.println(lengthOfLongestSubstring(s));
    }

    // Unfinished HashMap attempt (private, never called) - WRONG, see STATUS. A working
    // version sets l = max(l, last index of chars[r] + 1) and updates max on every step.
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


    // Queue window: returns the length of the longest substring without repeats. O(n * m)
    public static int lengthOfLongestSubstring(String s) {
        // the current window, oldest character at the front
        Queue<Character> hello = new ArrayDeque<>();
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            // drop from the front until the old copy of s.charAt(i) has left the window
            while(!hello.isEmpty() && hello.contains(s.charAt(i))){
                hello.poll();
            }
            // the window is repeat-free again - measure it
            hello.offer(s.charAt(i));
            max = Math.max(max, hello.size());
        }
        return max;
    }






}
