package Leetcode.Ch09SlidingWindowChasing;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 1456. Maximum Number of Vowels in a Substring of Given Length   [Medium]
 * https://leetcode.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length/
 * Topics: String, Sliding Window
 * Book: Algorithmic Thinking - ch. 9 Sliding window, chasing, Practice #2
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   s is a string of lowercase letters and k is a length. Look at every substring of exactly
 *   k characters, count the vowels (a, e, i, o, u) in each, and return the largest count.
 *
 * EXAMPLES
 *   s = "abciiidef", k = 3  ->  3    "iii" has three vowels
 *   s = "aeiou",     k = 2  ->  2    every substring of length 2 has two vowels
 *   s = "leetcode",  k = 3  ->  2    "lee", "eet" and "ode" have two vowels each
 *
 * CONSTRAINTS
 *   1 <= s.length <= 10^5,   s consists of lowercase English letters,   1 <= k <= s.length
 * </pre>
 */
public class LC1456MaximumNumberOfVowelsInASubstringOfGivenLength {

    /**
     * Variant A
     * <br>Target: time O(n * k), space O(1)
     */
    public static int maxVowelsA(String s, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(n), space O(n)
     */
    public static int maxVowelsB(String s, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C
     * <br>Target: time O(n), space O(1)
     */
    public static int maxVowelsC(String s, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC1456MaximumNumberOfVowelsInASubstringOfGivenLength::maxVowelsA);
        runAll("B", LC1456MaximumNumberOfVowelsInASubstringOfGivenLength::maxVowelsB);
        runAll("C", LC1456MaximumNumberOfVowelsInASubstringOfGivenLength::maxVowelsC);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class 
    //   (LC 1456. Maximum Number of Vowels in a Substring of Given Length)
    // https://leetcode.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the three official
    // examples plus edge cases checked against a brute-force reference.
    //   Expected result: 0/18 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int solve(String s, int k);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", 3, () -> s.solve("abciiidef", 3));
        check(v + " example 2", 2, () -> s.solve("aeiou", 2));
        check(v + " example 3", 2, () -> s.solve("leetcode", 3));
        // edge: no vowel anywhere
        check(v + " no vowels", 0, () -> s.solve("bcdfg", 2));
        // edge: the only window is the whole string
        check(v + " k equals length", 2, () -> s.solve("abcde", 5));
        // edge: the best window is the last one
        check(v + " best at the end", 3, () -> s.solve("xyzaei", 3));
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
                + (ok ? "" : "   expected " + expected + ", got " + got));
    }

    // ============================== END TESTS ===============================
}
