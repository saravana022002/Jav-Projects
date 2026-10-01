package Leetcode.Ch15StacksAndExpressionStructure;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 394. Decode String   [Medium]
 * https://leetcode.com/problems/decode-string/
 * Topics: String, Stack, Recursion
 * Book: Algorithmic Thinking - ch. 15 Stacks and expression structure, Practice #8
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   s is an encoded string. The rule k[text] means: write text exactly k times (k is a
 *   positive integer, text can contain letters and further k[text] groups). Groups can sit
 *   next to plain letters or next to each other. Digits appear only as repeat counts, and s
 *   is always well formed. Return the decoded string.
 *
 * EXAMPLES
 *   s = "3[a]2[bc]"       ->  "aaabcbc"
 *   s = "3[a2[c]]"        ->  "accaccacc"
 *   s = "2[abc]3[cd]ef"   ->  "abcabccdcdcdef"
 *
 * CONSTRAINTS
 *   1 <= s.length <= 30,   s consists of lowercase English letters, digits and square brackets,
 *   s is a valid encoding,   every repeat count is in [1, 300],
 *   the decoded string is never longer than 10^5
 * </pre>
 */
public class LC394DecodeString {

    /**
     * Variant A - brute force: expand one innermost k[text] group in place, repeat until none left
     * <br>Target: time O(n * (n + L)), space O(n + L)    (L = length of the decoded string)
     */
    public static String decodeStringBruteForce(String s) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - recursion: one call per bracket level, returning at the matching ']'
     * <br>Target: time O(n + L * d), space O(L + d)    (d = nesting depth)
     */
    public static String decodeStringRecursive(String s) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C - this chapter's technique: stack of (repeat count, text so far) pairs
     * <br>Target: time O(n + L * d), space O(L + d)
     */
    public static String decodeString(String s) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC394DecodeString::decodeStringBruteForce);
        runAll("B", LC394DecodeString::decodeStringRecursive);
        runAll("C", LC394DecodeString::decodeString);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 394. Decode String)
    // https://leetcode.com/problems/decode-string/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the three official
    // examples plus edge cases confirmed with an independent regex-based reference.
    //   Expected result: 0/18 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        String solve(String s);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", "aaabcbc", () -> s.solve("3[a]2[bc]"));
        check(v + " example 2", "accaccacc", () -> s.solve("3[a2[c]]"));
        check(v + " example 3", "abcabccdcdcdef", () -> s.solve("2[abc]3[cd]ef"));
        // edge: a repeat count with two digits
        check(v + " two-digit count", "aaaaaaaaaa", () -> s.solve("10[a]"));
        // edge: letters before the group, around a nested group, and after everything
        check(v + " letters around", "xabbcabbcd", () -> s.solve("x2[a2[b]c]d"));
        // edge: three levels of nesting
        check(v + " deep nesting", "aaaaaaaa", () -> s.solve("2[2[2[a]]]"));
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
