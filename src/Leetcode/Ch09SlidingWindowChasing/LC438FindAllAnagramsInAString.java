package Leetcode.Ch09SlidingWindowChasing;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 438. Find All Anagrams in a String   [Medium]
 * https://leetcode.com/problems/find-all-anagrams-in-a-string/
 * Topics: Hash Table, String, Sliding Window
 * Book: Algorithmic Thinking - ch. 9 Sliding window, chasing, Practice #3
 *       also: ch. 4 Frequency and multiset reasoning (#8)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   s and p are strings of lowercase letters. A start index i counts when the piece of s that
 *   begins at i and is as long as p is an anagram of p (the same letters with the same
 *   counts, in any order). Return every such start index; the order of the list does not
 *   matter.
 *
 * EXAMPLES
 *   s = "cbaebabacd", p = "abc"  ->  [0,6]     "cba" at 0 and "bac" at 6
 *   s = "abab",       p = "ab"   ->  [0,1,2]   "ab" at 0, "ba" at 1, "ab" at 2
 *
 * CONSTRAINTS
 *   1 <= s.length, p.length <= 3 * 10^4,   s and p consist of lowercase English letters
 *
 * EARLIER SOLUTIONS IN THIS REPO
 *   src/Leetcode/Ch04FrequencyAndMultisetReasoning/LC438FindAllAnagramsInAString.java
 * </pre>
 */
public class LC438FindAllAnagramsInAString {

    /**
     * Variant A
     * <br>Target: time O(n * m), space O(1)    (n = s.length(), m = p.length())
     */
    public static List<Integer> findAnagramsA(String s, String p) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(26 * n + m), space O(1)
     */
    public static List<Integer> findAnagramsB(String s, String p) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C
     * <br>Target: time O(n + m), space O(1)
     */
    public static List<Integer> findAnagramsC(String s, String p) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC438FindAllAnagramsInAString::findAnagramsA);
        runAll("B", LC438FindAllAnagramsInAString::findAnagramsB);
        runAll("C", LC438FindAllAnagramsInAString::findAnagramsC);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 438. Find All Anagrams in a String)
    // https://leetcode.com/problems/find-all-anagrams-in-a-string/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases checked against a brute-force reference.
    // "Any order" is handled by sorting your list before comparing it.
    //   Expected result: 0/15 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        List<Integer> solve(String s, String p);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", Arrays.asList(0, 6), () -> sorted(s.solve("cbaebabacd", "abc")));
        check(v + " example 2", Arrays.asList(0, 1, 2), () -> sorted(s.solve("abab", "ab")));
        // edge: p is longer than s, so there is no window at all
        check(v + " p longer than s", Arrays.asList(), () -> sorted(s.solve("ab", "abc")));
        // edge: letter counts matter, not just which letters occur
        check(v + " counts matter", Arrays.asList(0, 3, 4),
                () -> sorted(s.solve("aabbaab", "aab")));
        // edge: the window has some of p's letters but never all of them
        check(v + " partial letters", Arrays.asList(), () -> sorted(s.solve("aaaa", "ab")));
    }

    // the answer may come in any order: sort a copy before comparing
    private static List<Integer> sorted(List<Integer> list) {
        List<Integer> copy = new ArrayList<>(list);
        Collections.sort(copy);
        return copy;
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
