package Leetcode.Ch08TwoPointersConverging;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 680. Valid Palindrome II   [Easy]
 * https://leetcode.com/problems/valid-palindrome-ii/
 * Topics: Two Pointers, String, Greedy
 * Book: Algorithmic Thinking - ch. 8 Two pointers, converging, Practice #3
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   Return true if s is a palindrome already, or becomes one after deleting at most one
 *   character of your choice (the remaining characters keep their order); otherwise return false.
 *
 * EXAMPLES
 *   s = "aba"   ->  true
 *   s = "abca"  ->  true      delete the 'c' (or the 'b')
 *   s = "abc"   ->  false
 *
 * CONSTRAINTS
 *   1 <= s.length <= 10^5,   s consists of lowercase English letters
 * </pre>
 */
public class LC680ValidPalindromeII {

    /**
     * Variant A
     * <br>Target: time O(n^2), space O(1)
     * <br>Complete first: ch. 1 Complexity, amortisation, and counting
     */
    public static boolean validPalindromeA(String s) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(n), space O(1)
     * <br>Complete first: ch. 8 Two pointers, converging (this chapter)
     */
    public static boolean validPalindromeB(String s) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC680ValidPalindromeII::validPalindromeA);
        runAll("B", LC680ValidPalindromeII::validPalindromeB);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 680. Valid Palindrome II)
    // https://leetcode.com/problems/valid-palindrome-ii/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B) is checked separately with the same cases: the three official
    // examples plus edge cases checked against a try-every-deletion reference. The inputs are
    // kept short because the brute-force variant is quadratic.
    //   Expected result: 0/12 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        boolean solve(String s);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", true, () -> s.solve("aba"));
        check(v + " example 2", true, () -> s.solve("abca"));
        check(v + " example 3", false, () -> s.solve("abc"));
        // edge: a single character
        check(v + " single character", true, () -> s.solve("a"));
        // edge: at the first mismatch only the right character can be deleted
        check(v + " delete on the right", true, () -> s.solve("cbbcc"));
        // edge: at the first mismatch only the left character can be deleted
        check(v + " delete on the left", true, () -> s.solve("ccbbc"));
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
