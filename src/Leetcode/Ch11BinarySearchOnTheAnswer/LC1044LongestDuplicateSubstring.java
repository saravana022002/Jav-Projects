package Leetcode.Ch11BinarySearchOnTheAnswer;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 1044. Longest Duplicate Substring   [Hard]
 * https://leetcode.com/problems/longest-duplicate-substring/
 * Topics: String, Binary Search, Sliding Window, Rolling Hash, Suffix Array, Hash Function,
 *         Suffix Automaton, Suffix Tree, Z Algorithm, Boyer-Moore String-Search Algorithm
 * Book: Algorithmic Thinking - ch. 11 Binary search on the answer, Practice #12
 *       also: ch. 28 Rolling hash and Rabin-Karp (#6), ch. 30 Suffix structures (#4),
 *             ch. 79 Modular arithmetic and fast exponentiation (#13),
 *             ch. 86 Randomisation as a defence against the adversary (#12)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   A duplicated substring is a contiguous piece of s that occurs at least twice; the
 *   occurrences may overlap. Return any duplicated substring of the longest possible
 *   length, or the empty string if s has no duplicated substring at all.
 *
 * EXAMPLES
 *   s = "banana"  ->  "ana"    occurs at positions 1 and 3, overlapping in the middle 'a'
 *   s = "abcd"    ->  ""       no piece occurs twice
 *
 * CONSTRAINTS
 *   2 <= s.length <= 3 * 10^4,   s consists of lowercase English letters
 * </pre>
 */
public class LC1044LongestDuplicateSubstring {

    /**
     * Variant A
     * <br>Target: time O(n^3), space O(n^2)
     */
    public static String longestDupSubstringA(String s) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(n^2 log n), space O(n^2)
     */
    public static String longestDupSubstringB(String s) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C
     * <br>Target: time O(n log n) expected, space O(n)
     */
    public static String longestDupSubstringC(String s) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC1044LongestDuplicateSubstring::longestDupSubstringA);
        runAll("B", LC1044LongestDuplicateSubstring::longestDupSubstringB);
        runAll("C", LC1044LongestDuplicateSubstring::longestDupSubstringC);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 1044. Longest Duplicate Substring)
    // https://leetcode.com/problems/longest-duplicate-substring/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases whose longest length comes from an independent brute force.
    // Any longest duplicated substring is accepted, so the test checks the returned string
    // itself: it must occur at least twice in the input and have the expected length.
    //   Expected result: 0/15 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    private static int hung = 0;   // cases of the current variant that never finished
    private static final long TIMEOUT_MS = 5000;

    interface Solver {
        String solve(String s);
    }

    private static void runAll(String v, Solver s) {
        hung = 0;
        // official examples
        check(v + " example 1", longest(3), () -> verdict("banana", s.solve("banana")));
        check(v + " example 2", longest(0), () -> verdict("abcd", s.solve("abcd")));
        // edge: the shortest input that has a duplicate
        check(v + " tiny", longest(1), () -> verdict("aa", s.solve("aa")));
        // edge: all letters equal, the two occurrences overlap almost completely
        check(v + " overlap", longest(4), () -> verdict("aaaaa", s.solve("aaaaa")));
        // edge: a repeating pattern, the longest duplicate starts at 0 and at 3
        check(v + " repeating", longest(9), () -> verdict("abcabcabcabc", s.solve("abcabcabcabc")));
    }

    private static String longest(int length) {
        return length == 0 ? "the empty string" : "a duplicated substring of length " + length;
    }

    // describes the answer: valid (occurs at least twice in s) and how long it is
    private static String verdict(String input, String answer) {
        boolean twice = answer.isEmpty() || input.indexOf(answer) != input.lastIndexOf(answer);
        return twice ? longest(answer.length()) : "not a duplicated substring: \"" + answer + "\"";
    }

    // one case: a wrong value, an exception or an endless loop counts as FAIL; the run continues
    private static void check(String name, Object expected, Supplier<Object> actual) {
        total++;
        Object got = hung > 0 ? "skipped (an earlier case of this variant never finished)"
                : callWithTimeout(actual);
        boolean ok = Objects.deepEquals(expected, got);
        if (ok) passed++;
        System.out.println((ok ? "PASS  " : "FAIL  ") + name
                + (ok ? "" : "   expected " + show(expected) + ", got " + show(got)));
    }

    // runs the case on its own thread, so that an endless loop cannot block the whole run
    private static Object callWithTimeout(Supplier<Object> actual) {
        Object[] box = new Object[1];
        Thread worker = new Thread(null, () -> {
            try {
                box[0] = actual.get();
            } catch (Throwable t) {
                box[0] = "threw " + t.getClass().getSimpleName();
            }
        }, "case", 1L << 26);
        worker.setDaemon(true);
        worker.start();
        try {
            worker.join(TIMEOUT_MS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        if (worker.isAlive()) {
            hung++;
            return "no result after " + TIMEOUT_MS + " ms (endless loop?)";
        }
        return box[0];
    }

    private static String show(Object o) {
        if (o instanceof int[]) return Arrays.toString((int[]) o);
        if (o instanceof Object[]) return Arrays.deepToString((Object[]) o);
        return String.valueOf(o);
    }

    // ============================== END TESTS ===============================
}
