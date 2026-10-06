package Leetcode.Ch09SlidingWindowChasing;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 424. Longest Repeating Character Replacement   [Medium]
 * https://leetcode.com/problems/longest-repeating-character-replacement/
 * Topics: Hash Table, String, Sliding Window
 * Book: Algorithmic Thinking - ch. 9 Sliding window, chasing, Practice #6
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   s consists of uppercase letters. In one operation you may overwrite any single character
 *   with any uppercase letter, and you may do at most k operations. Return the length of the
 *   longest substring made of one repeated letter that you can end up with.
 *
 * EXAMPLES
 *   s = "ABAB", k = 2     ->  4    overwrite the two A's with B (or the two B's with A)
 *   s = "AABABBA", k = 1  ->  4    overwrite the middle A: "AABBBBA" contains "BBBB"
 *
 * CONSTRAINTS
 *   1 <= s.length <= 10^5,   s consists of only uppercase English letters,   0 <= k <= s.length
 * </pre>
 */
public class LC424LongestRepeatingCharacterReplacement {

    /**
     * Variant A - brute force: from every start, extend while the overwrites needed stay <= k
     * <br>Target: time O(n^2), space O(1)    (26 letter counts)
     * <br>Complete first: ch. 4 Frequency and multiset reasoning
     */
    public static int characterReplacementA(String s, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(26 * n), space O(1)
     * <br>Complete first: ch. 4 Frequency and multiset reasoning
     *     + ch. 9 Sliding window, chasing (this chapter)
     */
    public static int characterReplacementB(String s, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C
     * <br>Target: time O(n), space O(1)
     * <br>Complete first: ch. 4 Frequency and multiset reasoning
     *     + ch. 9 Sliding window, chasing (this chapter)
     */
    public static int characterReplacementC(String s, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC424LongestRepeatingCharacterReplacement::characterReplacementA);
        runAll("B", LC424LongestRepeatingCharacterReplacement::characterReplacementB);
        runAll("C", LC424LongestRepeatingCharacterReplacement::characterReplacementC);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class 
    // (LC 424. Longest Repeating Character Replacement)
    // https://leetcode.com/problems/longest-repeating-character-replacement/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases checked against a brute-force reference.
    //   Expected result: 0/15 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int solve(String s, int k);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", 4, () -> s.solve("ABAB", 2));
        check(v + " example 2", 4, () -> s.solve("AABABBA", 1));
        // edge: no overwrites allowed, the longest existing run counts
        check(v + " k = 0", 3, () -> s.solve("AABBBCC", 0));
        // edge: the best window sits between other letters, early windows must be given up
        check(v + " best window in the middle", 5, () -> s.solve("ABCDDDDEF", 1));
        // edge: k is as large as the string
        check(v + " k = length", 3, () -> s.solve("ABC", 3));
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
