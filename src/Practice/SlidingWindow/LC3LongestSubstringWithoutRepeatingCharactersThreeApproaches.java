package Practice.SlidingWindow;

import java.util.*;

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
//   Slide a window s[l..r] that never holds a repeated character. When s[r] already appears
//   inside the window, no valid window can contain both copies, so l has to move just past
//   the old copy. Then the window is the longest repeat-free substring ending at r, and the
//   answer is the largest window ever seen. The three methods differ only in HOW they move
//   l: one step at a time (queue, set) or in one jump using the last index where s[r] was
//   seen (map).
//
// APPROACHES IN THIS FILE
//   A. lengthOfLongestSubstring()          - queue window, poll until the old copy has left
//                                            - O(n * m)
//   B. optimalLengthOfLongestSubstring()   - HashSet window, remove from the left - O(n)
//   C. optimalSoLengthOfLongestSubstring() - HashMap char -> last index, jump l - O(n)
//   main() runs all three on "abba" (each prints 2). APPROACH + DRY RUN describe C.
//
// APPROACH (as implemented below, optimalSoLengthOfLongestSubstring)
//   characterVsCount maps each character to the LAST index where it was seen (despite the
//   name, it stores indices, not counts). For each r:
//   1. If s[r] was seen before, l = max(l, lastIndex + 1) - skip past the old copy, but only
//      if that copy is still inside the window.
//   2. Store r as the new last index of s[r].
//   3. longestSize = max(longestSize, r - l + 1).
//
// DRY RUN   C on s = "abba"
//   r=0 'a'  new                               {a:0}       l=0  window "a"   longest=1
//   r=1 'b'  new                               {a:0,b:1}   l=0  window "ab"  longest=2
//   r=2 'b'  seen at 1 -> l = max(0, 1+1) = 2  {a:0,b:2}   l=2  window "b"   longest=2
//   r=3 'a'  seen at 0 -> l = max(2, 0+1) = 2  {a:3,b:2}   l=2  window "ba"  longest=2
//   return 2. Without max(), r=3 would move l BACK to 1 and count "bba" (3) - wrong.
//
// COMPLEXITY   (m = min(n, number of distinct characters))
//   A: Time O(n * m) - each character is added and polled once, but contains() scans the
//      queue.  Space O(m)
//   B: Time O(n) - each index enters and leaves the set at most once.  Space O(m)
//   C: Time O(n) - one pass with O(1) map work per character.  Space O(m)
//
// GOTCHAS
//   - "abba": when r = 3 the old 'a' (index 0) is already left of l, so l must not move
//     back - that is why C uses Math.max(l, ...).
//   - In A, characters.poll() != s.charAt(i) compares char values (the Character is unboxed
//     because the other side is a char), so the loop stops right after the old copy.
//   - Empty string -> 0 in all three (the loops never run).
//
// SEE ALSO
//   LC3LongestSubstringWithoutRepeatingCharactersQueueWindow.java - ArrayDeque version of A
//   src/Leetcode/CountingStack/LC3lengthOfLongestSubstring.java - the queue window plus a
//     last-index array version
//   src/Leetcode/FrequencyAndMultisetResoning4/LC3LengthOfLongestSubstring.java - brute force
//     and a boolean[128] window version
//
// RELATED
//   - LC 1695 Maximum Erasure Value - the same repeat-free window on an int array, maximising
//     the window's sum instead of its length.
//   - LC 2260 Minimum Consecutive Cards to Pick Up - the same "last index of each value" map,
//     used to measure the distance between two equal cards.
// ============================================================================
public class LC3LongestSubstringWithoutRepeatingCharactersThreeApproaches {
    public static void main(String[] args) {
        // "abba" is the tricky case: at the last 'a' the old 'a' is already outside the window.
        // Expected output: 2, 2, 2 (one line per approach)
        String str = "abba";
        System.out.println(lengthOfLongestSubstring(str));
        System.out.println(optimalLengthOfLongestSubstring(str));
        System.out.println(optimalSoLengthOfLongestSubstring(str));
    }

    // Approach A: the queue is the window; on a repeat, poll from the front until the old
    // copy has been removed. O(n * m)
    public static int lengthOfLongestSubstring(String s) {
        Queue<Character> characters = new LinkedList<>();
        int longestSize = 0;
        for (int i = 0; i < s.length(); i++){
            // new character: just extend the window
            if(!characters.contains(s.charAt(i))) {
                characters.add(s.charAt(i));
            }else {
                // repeat: poll up to AND including the old copy, then append the new one
                while(!characters.isEmpty() && characters.poll() != s.charAt(i)){}
                characters.add(s.charAt(i));
            }
            if(characters.size() > longestSize){
                longestSize = characters.size();
            }
        }
        return longestSize;
    }

    // Approach B: HashSet holds the characters of the window s[l..r]. O(n)
    public static int optimalLengthOfLongestSubstring(String s) {
        Set<Character> characters = new HashSet<>();
        int longestSize = 0;
        int l = 0;
        int r = 0;
        while(r < s.length()){
            // s[r] is already inside: shrink from the left until its old copy is gone
            if(characters.contains(s.charAt(r))) {
                while (l < r && characters.contains(s.charAt(r))) {
                    characters.remove(s.charAt(l));
                    l++;
                }
            }
            // s[l..r] is repeat-free now
            characters.add(s.charAt(r));
            longestSize = Math.max(longestSize, r - l + 1);
            r++;
        }
        return longestSize;
    }

    // Approach C: map each character to the last index where it was seen and jump l past
    // that index in one step. O(n)
    public static int optimalSoLengthOfLongestSubstring(String s) {
        // character -> last index seen (the name says Count, but it stores indices)
        Map<Character, Integer> characterVsCount = new HashMap<>();
        int longestSize = 0;
        int l = 0;
        int r = 0;
        while(r < s.length()){
            // step 1: skip past the old copy, but never move l backwards ("abba")
            if(characterVsCount.containsKey(s.charAt(r))) {
                l = Math.max(l , characterVsCount.get(s.charAt(r)) + 1);
            }
            // steps 2-3: r is now the last index of s[r]; measure the window s[l..r]
            characterVsCount.put(s.charAt(r), r);
            longestSize = Math.max(longestSize, r - l + 1);
            r++;
        }
        return longestSize;
    }
}
