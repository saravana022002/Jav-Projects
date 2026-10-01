package Leetcode.Ch01ComplexityAmortisationAndCounting;

import java.util.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;

/**
 * <pre>
 * LC 3. Longest Substring Without Repeating Characters   [Medium]
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 * Topics: Hash Table, String, Sliding Window
 * Book: Algorithmic Thinking - ch. 1 Complexity, amortisation, and counting, Practice #5
 *       also: ch. 4 Frequency and multiset reasoning (#10), ch. 9 Sliding window, chasing (#4)
 *
 * PROBLEM
 *   Among the contiguous pieces (substrings) of s, find the longest one in which every
 *   character is different, and return its length. It must be a real substring: skipping
 *   letters is not allowed ("pwke" is not a substring of "pwwkew").
 *
 * EXAMPLES
 *   s = "abcabcbb"  ->  3     "abc" (so are "bca" and "cab")
 *   s = "bbbbb"     ->  1     "b"
 *   s = "pwwkew"    ->  3     "wke"
 *
 * CONSTRAINTS
 *   0 <= s.length <= 10^5,   s consists of English letters, digits, symbols and spaces
 *
 * INTUITION
 *   Keep a window s[l..r] that never contains a repeated character and push r to the right
 *   one step at a time. If s[r] is already inside the window, no window can hold both copies,
 *   so the left edge must move to just past the old copy. After each step the window is the
 *   longest repeat-free substring that ends at r, so the answer is the biggest window seen.
 *   Both edges only ever move forward, which is why the work is linear (every character
 *   enters the window once and leaves it at most once).
 *
 * APPROACHES IN THIS FILE
 *   A. lengthOfLongestSubstring()     - ArrayDeque as the window; poll from the front until the
 *                                       repeated character is gone - O(n * m)
 *   B. lengthOfLongestSubstringArr()  - int[256] with the last position of every character;
 *                                       l jumps past the old copy in one step - O(n)
 *                                       <- main() runs this one
 *   C. lengthOfLongestSubstringArr1() - the same algorithm as B, with your own explanatory
 *                                       comments - O(n)
 *   (n = s.length(), m = number of distinct characters the window can hold.)
 *
 * APPROACH (as implemented below, method B)
 *   1. size[c] = (last index where character c was seen) + 1, and 0 means "never seen".
 *      l = left edge of the window, max = best length so far.
 *   2. For each r, with c = s.charAt(r):
 *        l = max(l, size[c])         jump past the old copy of c - only if it is inside the
 *                                    window; an older copy left of l changes nothing
 *        max = max(max, r - l + 1)   s[l..r] has no repeats now
 *        size[c] = r + 1             c was last seen at r (stored 1-based)
 *   3. Return max.
 *
 * DRY RUN   s = "ccbbcc" (the input main() runs)
 *    r  c   size[c] before   l after   window   max
 *    0  c   0                0         "c"      1
 *    1  c   1                1         "c"      1    old c at index 0 is inside -> l jumps to 1
 *    2  b   0                1         "cb"     2
 *    3  b   3                3         "b"      2    old b at index 2 -> l jumps to 3
 *    4  c   2                3         "bc"     2    old c (stored 2) is LEFT of l = 3 -> l stays
 *    5  c   5                5         "c"      2    old c at index 4 -> l jumps to 5
 *   return 2
 *
 * COMPLEXITY
 *   A     : Time O(n * m) - each character is offered once and polled at most once, but every
 *           hello.contains() scans the queue.  Space O(m)
 *   B, C  : Time O(n) - one pass, O(1) work per character.  Space O(1) - a fixed 256 slots
 *
 * GOTCHAS
 *   - Never move l backwards: in "abba" the last 'a' has its old copy at index 0, already left
 *     of l = 2, so l must stay - that is the whole purpose of Math.max(l, ...).
 *   - Storing r + 1 instead of r does two jobs: l lands just AFTER the old copy, and the
 *     default 0 means "never seen" with no special case (see the comment in method C).
 *   - The array is called size, but it holds positions, not sizes.
 *   - The character is used directly as an array index, so a character above code 255 would
 *     throw ArrayIndexOutOfBoundsException. Fine for the constraints (English letters,
 *     digits, symbols, spaces).
 *   - Empty string -> 0, because the loops never run.
 *
 * STATUS
 *   All three methods are correct (checked against a brute force on random strings).
 *   main() only runs lengthOfLongestSubstringArr (method B); A and C are not called.
 *
 * SEE ALSO
 *   src/Leetcode/Ch04FrequencyAndMultisetReasoning/
 *     LC3LongestSubstringWithoutRepeatingCharacters.java - a HashSet brute force per start
 *     index, plus a boolean[128] sliding window
 *   src/Practice/SlidingWindow/LC3LongestSubstringWithoutRepeatingCharactersQueueWindow.java
 *     - the queue window of method A, documented in detail
 *   src/Practice/SlidingWindow/LC3LongestSubstringWithoutRepeatingCharactersThreeApproaches.java
 *     - queue, HashSet window and last-index HashMap versions side by side
 *
 * RELATED
 *   - LC 992 Subarrays with K Different Integers (same chapter) - also a window over distinct
 *     values, there counting windows with at most K distinct numbers.
 *   - LC 1695 Maximum Erasure Value - the same repeat-free window on an int array,
 *     maximising the window's sum instead of its length.
 * </pre>
 */
public class LC3LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        // "ccbbcc": the longest repeat-free pieces are "cb" and "bc" -> prints 2 (method B)
        String s = "ccbbcc";
        System.out.println(lengthOfLongestSubstringArr(s));
        // run the test cases (TESTS section at the end of this class)
        runTests();
    }
    // Approach A: a queue is the window; poll from the front until the repeat has left. O(n * m)
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



    //"abcabcbb"
    // Approach B: last position of every character in an array, l jumps past a repeat. O(n)
    public static int lengthOfLongestSubstringArr(String s) {
        // despite the name: size[c] = (last index where c was seen) + 1; 0 = never seen
        int[] size = new int[256];
        // left edge of the window s[l..r]
        int l = 0;
        int max = 0;
        for (int r = 0; r < s.length(); r++) {
            char c = s.charAt(r);
            // old copy inside the window -> move l just past it; an older copy leaves l alone
            l = Math.max(l, size[c]);
            // the window s[l..r] has no repeats now
            max = Math.max(max, r - l + 1);
            // c was last seen at r (stored 1-based)
            size[c] = r + 1;
        }
        return max;
    }

    // Approach C: the same algorithm as B, with your own explanatory comments. O(n)
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

    // ================================ TESTS =================================
    // Tests for this class 
    // (LC 3. Longest Substring Without Repeating Characters)
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Cases: the three official examples + edge cases; every input is run through all three
    // methods of the solution (queue window, int[256] version, and its commented copy).
    // Expected values come from the statement, from the reasoning noted per case, and (checked
    // offline) from an O(n^2) brute force with a HashSet.
    //   Expected result: 21/21 passed
    // ==========================================================================
    private static void runTests() {
        // official examples
        all("example 1: abcabcbb", "abcabcbb", 3);
        all("example 2: bbbbb", "bbbbb", 1);
        all("example 3: pwwkew", "pwwkew", 3);
        // edge: the empty string is allowed (length 0)
        all("empty string", "", 0);
        // edge: the old 'a' is already left of the window, so the window must not shrink back
        all("abba", "abba", 2);
        // edge: a single space (spaces are allowed characters)
        all("single space", " ", 1);
        // edge: 10^5 characters cycling through the 95 printable ASCII characters; every window
        // of 95 neighbours is all-different and any 96 must repeat, so the answer is 95
        StringBuilder cycle = new StringBuilder();
        for (int i = 0; i < 100000; i++) cycle.append((char) (' ' + i % 95));
        all("10^5 chars, 95 distinct in a cycle", cycle.toString(), 95);
        System.out.println(passed + "/" + total + " passed");
    }

    private static int passed = 0, total = 0;

    // runs one input through the three methods of the solution
    private static void all(String name, String s, int expected) {
        one(name, "queue", s, expected,
                LC3LongestSubstringWithoutRepeatingCharacters::lengthOfLongestSubstring);
        one(name, "Arr", s, expected,
                LC3LongestSubstringWithoutRepeatingCharacters::lengthOfLongestSubstringArr);
        one(name, "Arr1", s, expected,
                LC3LongestSubstringWithoutRepeatingCharacters::lengthOfLongestSubstringArr1);
    }

    private static void one(String name, String method, String s, int expected,
                            ToIntFunction<String> f) {
        check(name + "  [" + method + "]", expected, () -> f.applyAsInt(s));
    }

    // one case: a wrong value or an exception counts as FAIL; the run always continues
    private static void check(String name, Object expected, Supplier<Object> actual) {
        total++;
        Object got;
        try {
            got = actual.get();
        } catch (Throwable t) {
            got = "threw " + t.getClass().getSimpleName();
        }
        boolean ok = Objects.deepEquals(expected, got);
        if (ok) passed++;
        System.out.println((ok ? "PASS  " : "FAIL  ") + name
                + (ok ? "" : "   expected " + show(expected) + ", got " + show(got)));
    }

    private static String show(Object o) {
        if (o instanceof int[]) return Arrays.toString((int[]) o);
        if (o instanceof long[]) return Arrays.toString((long[]) o);
        if (o instanceof char[]) return Arrays.toString((char[]) o);
        if (o instanceof boolean[]) return Arrays.toString((boolean[]) o);
        if (o instanceof Object[]) return Arrays.deepToString((Object[]) o);
        return String.valueOf(o);
    }

    // ============================== END TESTS ===============================
}
