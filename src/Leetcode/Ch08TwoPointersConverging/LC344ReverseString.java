package Leetcode.Ch08TwoPointersConverging;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 344. Reverse String   [Easy]
 * https://leetcode.com/problems/reverse-string/
 * Topics: Two Pointers, String
 * Book: Algorithmic Thinking - ch. 8 Two pointers, converging, Practice #1
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   s is an array of characters. Reverse it in place: the given array itself must end up
 *   holding the characters in the opposite order, and only O(1) extra memory may be used.
 *   Nothing is returned.
 *
 * EXAMPLES
 *   s = ["h","e","l","l","o"]       ->  s becomes ["o","l","l","e","h"]
 *   s = ["H","a","n","n","a","h"]   ->  s becomes ["h","a","n","n","a","H"]
 *
 * CONSTRAINTS
 *   1 <= s.length <= 10^5,   s[i] is a printable ASCII character
 * </pre>
 */
public class LC344ReverseString {

    /**
     * Variant A
     * <br>Target: time O(n), space O(n)
     * <br>Complete first: ch. 1 Complexity, amortisation, and counting
     */
    public static void reverseStringA(char[] s) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(n), space O(1)
     * <br>Complete first: ch. 8 Two pointers, converging (this chapter)
     */
    public static void reverseStringB(char[] s) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC344ReverseString::reverseStringA);
        runAll("B", LC344ReverseString::reverseStringB);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 344. Reverse String)
    // https://leetcode.com/problems/reverse-string/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B) is checked separately with the same cases: the two official
    // examples plus edge cases. The methods return nothing, so each case runs the method on a
    // fresh char array and compares the characters left in that same array with the expectation.
    //   Expected result: 0/10 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        void solve(char[] s);
    }

    // runs the in-place method on the characters of text and returns what the array holds after
    private static String apply(Solver s, String text) {
        char[] chars = text.toCharArray();
        s.solve(chars);
        return new String(chars);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", "olleh", () -> apply(s, "hello"));
        check(v + " example 2", "hannaH", () -> apply(s, "Hannah"));
        // edge: a single character
        check(v + " single character", "x", () -> apply(s, "x"));
        // edge: spaces and punctuation are ordinary characters
        check(v + " spaces and symbols", "~!b a", () -> apply(s, "a b!~"));
        // edge: the maximum length
        check(v + " 100000 characters", reversedBig(), () -> apply(s, big()));
    }

    // 100000 characters: a..z repeated
    private static String big() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 100_000; i++) sb.append((char) ('a' + i % 26));
        return sb.toString();
    }

    // the same characters, written backwards by an independent loop
    private static String reversedBig() {
        StringBuilder sb = new StringBuilder();
        for (int i = 99_999; i >= 0; i--) sb.append((char) ('a' + i % 26));
        return sb.toString();
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
        if (o instanceof String && ((String) o).length() > 40) {
            String t = (String) o;
            return "a String of length " + t.length() + " starting \"" + t.substring(0, 20) + "\"";
        }
        return String.valueOf(o);
    }

    // ============================== END TESTS ===============================
}
