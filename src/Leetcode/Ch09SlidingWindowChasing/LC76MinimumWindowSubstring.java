package Leetcode.Ch09SlidingWindowChasing;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 76. Minimum Window Substring   [Hard]
 * https://leetcode.com/problems/minimum-window-substring/
 * Topics: Hash Table, String, Sliding Window
 * Book: Algorithmic Thinking - ch. 9 Sliding window, chasing, Practice #8
 *       also: ch. 4 Frequency and multiset reasoning (#11)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   Find the shortest substring of s that contains every character of t, duplicates included:
 *   if t has two 'a's, the window needs at least two 'a's. Return that substring, or the
 *   empty string "" when no window of s covers t. The tests only use inputs whose shortest
 *   window is unique.
 *
 * EXAMPLES
 *   s = "ADOBECODEBANC", t = "ABC"  ->  "BANC"    it holds an 'A', a 'B' and a 'C'
 *   s = "a", t = "a"               ->  "a"       the whole string is the window
 *   s = "a", t = "aa"              ->  ""        t needs two 'a's, s has only one
 *
 * CONSTRAINTS
 *   m = s.length, n = t.length,   1 <= m, n <= 10^5,
 *   s and t consist of uppercase and lowercase English letters
 *
 * EARLIER SOLUTIONS IN THIS REPO
 *   src/Leetcode/Ch04FrequencyAndMultisetReasoning/LC76MinimumWindowSubstring.java
 * </pre>
 */
public class LC76MinimumWindowSubstring {

    /**
     * Variant A - brute force: from every start, extend right until the window covers t
     * <br>Target: time O(m^2 + n), space O(1)    (52 letter counts)
     * <br>Complete first: ch. 4 Frequency and multiset reasoning
     */
    public static String minWindowA(String s, String t) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(52 * m + n), space O(1)
     * <br>Complete first: ch. 4 Frequency and multiset reasoning
     *     + ch. 9 Sliding window, chasing (this chapter)
     */
    public static String minWindowB(String s, String t) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C
     * <br>Target: time O(m + n), space O(1)
     * <br>Complete first: ch. 4 Frequency and multiset reasoning
     *     + ch. 9 Sliding window, chasing (this chapter)
     */
    public static String minWindowC(String s, String t) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC76MinimumWindowSubstring::minWindowA);
        runAll("B", LC76MinimumWindowSubstring::minWindowB);
        runAll("C", LC76MinimumWindowSubstring::minWindowC);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 76. Minimum Window Substring)
    // https://leetcode.com/problems/minimum-window-substring/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the three official
    // examples plus edge cases checked against a brute-force reference. Every case has a
    // unique shortest window, as the problem promises.
    //   Expected result: 0/18 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        String solve(String s, String t);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", "BANC", () -> s.solve("ADOBECODEBANC", "ABC"));
        check(v + " example 2", "a", () -> s.solve("a", "a"));
        check(v + " example 3", "", () -> s.solve("a", "aa"));
        // edge: t needs the letter A twice, so the window must reach the second A
        check(v + " duplicates in t", "ADOBECODEBA", () -> s.solve("ADOBECODEBANC", "AABC"));
        // edge: upper and lower case letters are different characters
        check(v + " case matters", "bBA", () -> s.solve("abBA", "Ab"));
        // edge: one letter of t never occurs in s
        check(v + " missing letter", "", () -> s.solve("ABCDE", "ABX"));
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
        if (o instanceof Object[]) return Arrays.deepToString((Object[]) o);
        return String.valueOf(o);
    }

    // ============================== END TESTS ===============================
}
