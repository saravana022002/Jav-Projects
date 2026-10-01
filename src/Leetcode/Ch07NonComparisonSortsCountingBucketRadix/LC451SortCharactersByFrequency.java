package Leetcode.Ch07NonComparisonSortsCountingBucketRadix;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 451. Sort Characters By Frequency   [Medium]
 * https://leetcode.com/problems/sort-characters-by-frequency/
 * Topics: Hash Table, String, Sorting, Heap (Priority Queue), Bucket Sort, Counting
 * Book: Algorithmic Thinking - ch. 7 Non-comparison sorts: counting, bucket, radix, Practice #5
 *       also: ch. 4 Frequency and multiset reasoning (#6)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   Rearrange the characters of s so that characters that occur more often come first.
 *   All copies of one character must stay together as one group. Upper and lower case are
 *   different characters. When several arrangements obey these rules (for example groups of
 *   equal size), any of them is accepted - the test checks the rules, not one fixed string.
 *
 * EXAMPLES
 *   s = "tree"    ->  "eert"     'e' occurs twice, so it goes first; "eetr" is accepted too
 *   s = "cccaaa"  ->  "aaaccc"   both groups have size 3; "cccaaa" is accepted too
 *   s = "Aabb"    ->  "bbAa"     "bbaA" is accepted too; 'A' and 'a' are different characters
 *
 * CONSTRAINTS
 *   1 <= s.length <= 5 * 10^5,   s consists of uppercase and lowercase English letters and digits
 *
 * EARLIER SOLUTIONS IN THIS REPO
 *   src/Leetcode/Ch04FrequencyAndMultisetReasoning/LC451SortCharactersByFrequency.java
 * </pre>
 */
public class LC451SortCharactersByFrequency {

    /**
     * Variant A
     * <br>Target: time O(n log n), space O(n)
     */
    public static String frequencySortA(String s) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(n + k log k), space O(k) besides the result    (k = distinct characters)
     */
    public static String frequencySortB(String s) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C
     * <br>Target: time O(n), space O(n)
     */
    public static String frequencySortC(String s) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC451SortCharactersByFrequency::frequencySortA);
        runAll("B", LC451SortCharactersByFrequency::frequencySortB);
        runAll("C", LC451SortCharactersByFrequency::frequencySortC);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 451. Sort Characters By Frequency)
    // https://leetcode.com/problems/sort-characters-by-frequency/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the three official
    // examples plus edge cases. Several answers are valid, so each answer is checked against
    // the rules (same characters, each character in one group, groups never grow) instead of
    // being compared with one fixed string.
    //   Expected result: 0/18 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        String solve(String s);
    }

    private static void runAll(String v, Solver s) {
        // official examples (any valid arrangement is accepted)
        check(v + " example 1", "valid", () -> verdict("tree", s.solve("tree")));
        check(v + " example 2", "valid", () -> verdict("cccaaa", s.solve("cccaaa")));
        check(v + " example 3", "valid", () -> verdict("Aabb", s.solve("Aabb")));
        // edge: a single character
        check(v + " single character", "valid", () -> verdict("q", s.solve("q")));
        // edge: letters of both cases and digits, several groups with equal sizes
        String mixed = "mississippiMISSISSIPPI112233";
        check(v + " mixed case and digits", "valid", () -> verdict(mixed, s.solve(mixed)));
        // edge: a long input with skewed frequencies (200000 characters)
        String longInput = skewed(200_000);
        check(v + " long input", "valid", () -> verdict(longInput, s.solve(longInput)));
    }

    // deterministic pseudo-random string over 62 symbols; low symbols are rare, high ones common
    private static String skewed(int n) {
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder sb = new StringBuilder();
        long x = 12345;
        for (int i = 0; i < n; i++) {
            x = (x * 1103515245L + 12345L) & 0x7fffffffL;
            int r = (int) ((x >>> 8) % 3844);
            sb.append(alphabet.charAt((int) Math.sqrt(r)));
        }
        return sb.toString();
    }

    // "valid" when out obeys the rules for input in, otherwise a short reason
    private static String verdict(String in, String out) {
        if (out == null) return "returned null";
        if (out.length() != in.length()) {
            return "length " + out.length() + " instead of " + in.length() + ": " + cut(out);
        }
        int[] want = new int[65536], have = new int[65536];
        for (char c : in.toCharArray()) want[c]++;
        for (char c : out.toCharArray()) have[c]++;
        if (!Arrays.equals(want, have)) return "not a rearrangement of the input: " + cut(out);
        boolean[] seen = new boolean[65536];
        int previous = Integer.MAX_VALUE;
        int i = 0;
        while (i < out.length()) {
            char c = out.charAt(i);
            if (seen[c]) return "'" + c + "' appears in two separate groups: " + cut(out);
            seen[c] = true;
            int j = i;
            while (j < out.length() && out.charAt(j) == c) j++;
            if (j - i > previous) {
                return "a group of " + (j - i) + " comes after a smaller group: " + cut(out);
            }
            previous = j - i;
            i = j;
        }
        return "valid";
    }

    private static String cut(String s) {
        return s.length() <= 40 ? "\"" + s + "\"" : "\"" + s.substring(0, 40) + "...\"";
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
