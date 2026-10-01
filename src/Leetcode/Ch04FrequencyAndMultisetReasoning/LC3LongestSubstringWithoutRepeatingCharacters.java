package Leetcode.Ch04FrequencyAndMultisetReasoning;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 3. Longest Substring Without Repeating Characters   [Medium]
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/
 * Topics: Hash Table, String, Sliding Window
 * Book: Algorithmic Thinking - ch. 4 Frequency and multiset reasoning, Practice #10
 *       also: ch. 1 Complexity, amortisation, and counting (#5), ch. 9 Sliding window, chasing (#4)
 *
 * PROBLEM
 *   Among the contiguous pieces (substrings) of s, find the longest one in which no character
 *   appears twice, and return its length. It must be a real substring: skipping letters is
 *   not allowed ("pwke" is not a substring of "pwwkew"). The empty string gives 0.
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
 *   Keep a window s[i..j] that holds no repeated character and push its right edge j to the
 *   right one step at a time. When s[j] is already inside the window, no repeat-free window
 *   can hold both copies, so the left edge i must move forward until the old copy has left;
 *   then s[j] can enter. After every step the window is the longest repeat-free substring
 *   that ends at j, so the answer is the largest window ever seen. Both edges only move
 *   forward: every character enters the window once and leaves it at most once.
 *
 * APPROACHES IN THIS FILE
 *   A. lengthOfLongestSubstring()    - brute force: from every start i, extend j while the
 *                                      characters stay distinct (a HashSet per start) - meant
 *                                      to be O(n * m); WRONG as written, see STATUS
 *                                      <- main() runs this one
 *   B. lengthOfLongestSubstringOpt() - sliding window with a boolean[128] flag per character
 *                                      - O(n) time, O(1) space; correct
 *   (n = s.length(), m = the longest repeat-free stretch, at most 128 characters.)
 *
 * APPROACH (as implemented below, method B - lengthOfLongestSubstringOpt)
 *   1. seen = new boolean[128]: seen[c] is true while character c is inside the window
 *      s[i..j] (the character code is the index, so ASCII only). i = left edge, max = best.
 *   2. For each right edge j: in = s.charAt(j).
 *   3. While seen[in] is true the character is already in the window: take out = s.charAt(i),
 *      set seen[out] = false and i++ - until the old copy of `in` has left.
 *   4. seen[in] = true, then max = Math.max(max, j - i + 1).
 *   5. Return max.
 *
 * DRY RUN   s = "Sasdaf"   (the input main() runs - B gives the right answer, see STATUS for A)
 *   j=0 'S'  no clash                                 window "S"      max = 1
 *   j=1 'a'  no clash                                 window "Sa"     max = 2
 *   j=2 's'  no clash ('s' is not 'S')                window "Sas"    max = 3
 *   j=3 'd'  no clash                                 window "Sasd"   max = 4
 *   j=4 'a'  clash: drop 'S' (i=1), drop 'a' (i=2)    window "sda"    max = 4
 *   j=5 'f'  no clash                                 window "sdaf"   max = 4
 *   return 4
 *
 * COMPLEXITY
 *   B: Time O(n) - j moves right n times and i at most n times in total, so each character
 *      enters and leaves the window once.  Space O(1) - a fixed table of 128 flags
 *   A: meant to be Time O(n * m), Space O(m) (the HashSet); as written its inner loop always
 *      stops after two steps, so it only does O(n) work - and gets the wrong answer
 *
 * GOTCHAS
 *   - Substring means contiguous: "pwke" does not count in "pwwkew".
 *   - Case matters: 'S' and 's' are different characters (see "Sasdaf").
 *   - B drops characters in a WHILE loop, not an if: the old copy of the repeated character
 *     can sit several positions in, and everything before it has to go as well.
 *   - boolean[128] uses the character code as the index, so a character with code 128 or more
 *     would throw ArrayIndexOutOfBoundsException. The constraints (English letters, digits,
 *     symbols and spaces) stay inside ASCII.
 *   - The empty string gives 0 (the loops never run).
 *
 * STATUS
 *   Known issue in lengthOfLongestSubstring (method A, the one main() runs): the HashSet add
 *   uses s.charAt(i) where it should use s.charAt(j). At j = i the set accepts s[i]; at
 *   j = i + 1 it is asked to add s[i] AGAIN, refuses, and the inner loop breaks. So every
 *   start position yields a window of length 1: the method returns 1 for every non-empty
 *   string and 0 for "". Example 1 "abcabcbb" returns 1 (correct 3) and main() prints 1 for
 *   "Sasdaf" (correct 4). The fix is ch.add(s.charAt(j)).
 *   lengthOfLongestSubstringOpt (method B) is correct. The code is left unchanged on purpose.
 *   The import of HashMap is unused.
 *
 * RELATED
 *   - LC 1695 Maximum Erasure Value - the same repeat-free window on an int array,
 *     maximising the window's sum instead of its length.
 *   - LC 992 Subarrays with K Different Integers - another window over distinct values;
 *     solved in the Ch01 folder (LC992SubarraysWithKDifferentIntegers.java).
 *
 * SEE ALSO
 *   src/Leetcode/Ch01ComplexityAmortisationAndCounting/
 *     LC3LongestSubstringWithoutRepeatingCharacters.java - the same problem in the Ch01 set:
 *     a queue window and a last-position int[256] array where the left edge jumps past the
 *     old copy in one step; both correct
 *   src/Practice/SlidingWindow/LC3LongestSubstringWithoutRepeatingCharactersQueueWindow.java
 *     - the queue window, documented in detail
 *   src/Practice/SlidingWindow/LC3LongestSubstringWithoutRepeatingCharactersThreeApproaches.java
 *     - queue, HashSet window and last-index HashMap versions side by side
 * </pre>
 */
public class LC3LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args) {
        // "Sasdaf" (case matters: 'S' != 's'): the longest repeat-free pieces are "Sasd" and
        // "sdaf" -> 4. This method prints 1 because of its bug (see STATUS)
        String s = "Sasdaf";
        System.out.println(lengthOfLongestSubstring(s));
        // run the test cases (TESTS section at the end of this class)
        runTests();
    }

    // Approach A (brute force): from every start i extend j while the characters stay distinct.
    // KNOWN ISSUE: ch.add(s.charAt(i)) should be s.charAt(j) - see STATUS; returns 1 for any
    // non-empty s
    public static int lengthOfLongestSubstring(String s) {
        int max = 0;
        // every start position i
        for (int i = 0; i < s.length(); i++) {
            // the characters of the current attempt s[i..j]
            Set<Character> ch = new HashSet<>();
            for (int j = i; j < s.length(); j++) {
                // BUG: this adds s[i] again and again; it should add s[j], the character that
                // just joined the window. The second step already finds s[i] "repeated".
                if (!ch.add(s.charAt(i))) {
                    break;
                }
                // s[i..j] is repeat-free: measure it
                max = Math.max(max, j - i + 1);
            }
        }

        return max;
    }


    // Approach B: sliding window s[i..j] with one flag per ASCII character. O(n) time, O(1) space.
    public static int lengthOfLongestSubstringOpt(String s) {
        // seen[c] is true while character c is inside the window s[i..j]
        boolean[] seen = new boolean[128];
        int i = 0, max = 0;
        for (int j = 0; j < s.length(); j++) {
            // the character entering on the right
            int in = s.charAt(j);
            // it is already inside: drop characters from the left until its old copy has left
            while (seen[in]){
                int out = s.charAt(i);
                seen[out] = false;
                i++;
            }
            // s[i..j] is repeat-free now: measure it
            seen[in] = true;
            max = Math.max(max, j - i + 1);
        }
        return max;
    }

    // ================================ TESTS =================================
    // Tests for this class 
    // (LC 3. Longest Substring Without Repeating Characters)
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Cases: the 3 official examples + edge cases (empty string, one space, "abba", "dvdf",
    // "tmmzuxt", the case-sensitive input of main(), all 95 printable ASCII characters twice).
    // Every input is run through BOTH methods of the solution, lengthOfLongestSubstring and
    // lengthOfLongestSubstringOpt. Expected values come from the statement or from a brute force
    // that tries every substring, done outside the solution.
    //   Expected result: 13/20 passed
    //   The 7 failures are the known issue described in the STATUS of
    //   LC3LongestSubstringWithoutRepeatingCharacters.java: lengthOfLongestSubstring returns 1 for
    //   every non-empty string, so it only passes the cases whose right answer is 0 or 1.
    // ==========================================================================
    private static void runTests() {
        // official examples
        both("example 1: abcabcbb", 3, "abcabcbb");
        both("example 2: bbbbb", 1, "bbbbb");
        both("example 3: pwwkew", 3, "pwwkew");
        // edge: the empty string is allowed (0 <= s.length)
        both("empty string", 0, "");
        // edge: a single space is a character too
        both("single space", 1, " ");
        // edge: the left edge must never move backwards
        both("abba", 2, "abba");
        both("dvdf", 3, "dvdf");
        both("tmmzuxt", 5, "tmmzuxt");
        // edge: the input of main() - upper and lower case are different characters
        both("Sasdaf (case matters)", 4, "Sasdaf");
        // edge: all 95 printable ASCII characters, twice in a row
        StringBuilder printable = new StringBuilder();
        for (char c = ' '; c <= '~'; c++) printable.append(c);
        both("95 printable ASCII characters, twice", 95, printable.toString() + printable);
        System.out.println(passed + "/" + total + " passed");
    }

    private static int passed = 0, total = 0;

    // run the same input through both methods of the solution
    private static void both(String name, int expected, String s) {
        check(name + "  [lengthOfLongestSubstring]", expected,
                () -> LC3LongestSubstringWithoutRepeatingCharacters.lengthOfLongestSubstring(s));
        check(name + "  [lengthOfLongestSubstringOpt]", expected,
                () -> LC3LongestSubstringWithoutRepeatingCharacters.lengthOfLongestSubstringOpt(s));
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


