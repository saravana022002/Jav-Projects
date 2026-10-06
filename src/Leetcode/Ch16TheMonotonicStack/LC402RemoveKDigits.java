package Leetcode.Ch16TheMonotonicStack;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 402. Remove K Digits   [Medium]
 * https://leetcode.com/problems/remove-k-digits/
 * Topics: String, Stack, Greedy, Monotonic Stack
 * Book: Algorithmic Thinking - ch. 16 The monotonic stack, Practice #12
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   num is a string of digits that spells a non-negative integer, and k is a count. Delete
 *   exactly k digits from num; the digits that remain keep their original order and form a
 *   new number. Return the smallest number you can get, as a string without leading zeros
 *   (if no digit remains, the answer is "0").
 *
 * EXAMPLES
 *   num = "1432219", k = 3  ->  "1219"    remove the digits 4, 3 and 2
 *   num = "10200",   k = 1  ->  "200"     remove the 1, and the leading zero disappears
 *   num = "10",      k = 2  ->  "0"       nothing is left, so the answer is "0"
 *
 * CONSTRAINTS
 *   1 <= k <= num.length <= 10^5,   num consists of digits only,
 *   num has no leading zeros except for the number 0 itself
 * </pre>
 */
public class LC402RemoveKDigits {

    /**
     * Variant A
     * <br>Target: time O(k * n^2), space O(n)
     * <br>Complete first: ch. 1 Complexity, amortisation, and counting
     */
    public static String removeKdigitsA(String num, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(n), space O(n)
     * <br>Complete first: ch. 16 The monotonic stack (this chapter)
     */
    public static String removeKdigitsB(String num, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC402RemoveKDigits::removeKdigitsA);
        runAll("B", LC402RemoveKDigits::removeKdigitsB);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 402. Remove K Digits)
    // https://leetcode.com/problems/remove-k-digits/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B) is checked separately with the same cases: the three official
    // examples plus edge cases confirmed with an independent brute force that tries every
    // way to keep num.length() - k digits.
    //   Expected result: 0/12 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        String solve(String num, int k);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", "1219", () -> s.solve("1432219", 3));
        check(v + " example 2", "200", () -> s.solve("10200", 1));
        check(v + " example 3", "0", () -> s.solve("10", 2));
        // edge: digits never decrease, so the removals must come from the end
        check(v + " increasing", "123", () -> s.solve("12345", 2));
        // edge: removing the 1 leaves "00", which must be printed as "0"
        check(v + " only zeros left", "0", () -> s.solve("100", 1));
        // edge: nine of ten digits go; the smallest single digit that remains is the 0
        check(v + " keep one digit", "0", () -> s.solve("1234567890", 9));
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
