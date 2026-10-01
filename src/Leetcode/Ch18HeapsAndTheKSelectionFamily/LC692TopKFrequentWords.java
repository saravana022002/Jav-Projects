package Leetcode.Ch18HeapsAndTheKSelectionFamily;

import java.util.List;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 692. Top K Frequent Words   [Medium]
 * https://leetcode.com/problems/top-k-frequent-words/
 * Topics: Array, Hash Table, String, Trie, Sorting, Heap (Priority Queue), Bucket Sort, Counting
 * Book: Algorithmic Thinking - ch. 18 Heaps and the k-selection family, Practice #6
 *       also: ch. 7 Non-comparison sorts: counting, bucket, radix (#7)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   Return the k most frequent words of the array words. The result must be ordered by
 *   frequency from the highest to the lowest, and words with the same frequency must appear
 *   in alphabetical (lexicographical) order.
 *   Follow-up: can you do it in O(n log k) time and O(n) extra space?
 *
 * EXAMPLES
 *   words = ["i","love","leetcode","i","love","coding"],  k = 2  ->  ["i","love"]
 *       "i" and "love" occur twice each; "i" comes first alphabetically
 *   words = ["the","day","is","sunny","the","the","the","sunny","is","is"],  k = 4
 *       ->  ["the","is","sunny","day"]     they occur 4, 3, 2 and 1 times
 *
 * CONSTRAINTS
 *   1 <= words.length <= 500,   1 <= words[i].length <= 10,   words[i] has lowercase letters only,
 *   k is in the range [1, number of unique words]
 * </pre>
 */
public class LC692TopKFrequentWords {

    /**
     * Variant A
     * <br>Target: time O(n log n), space O(n)
     */
    public static List<String> topKFrequentA(String[] words, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(n log k), space O(n)
     */
    public static List<String> topKFrequentB(String[] words, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC692TopKFrequentWords::topKFrequentA);
        runAll("B", LC692TopKFrequentWords::topKFrequentB);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 692. Top K Frequent Words)
    // https://leetcode.com/problems/top-k-frequent-words/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B) is checked separately with the same cases: the two official examples
    // plus edge cases (expected lists from a count-and-sort reference). The ORDER of the
    // returned words matters here (frequency descending, then alphabetical), so lists are
    // compared as they are.
    //   Expected result: 0/10 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        List<String> solve(String[] words, int k);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", Arrays.asList("i", "love"),
                () -> s.solve(new String[]{"i", "love", "leetcode", "i", "love", "coding"}, 2));
        check(v + " example 2", Arrays.asList("the", "is", "sunny", "day"),
                () -> s.solve(new String[]{"the", "day", "is", "sunny", "the", "the", "the",
                        "sunny", "is", "is"}, 4));
        // edge: k cuts through a group of equal counts, the alphabetically first words stay
        check(v + " tie group cut by k", Arrays.asList("a", "b"),
                () -> s.solve(new String[]{"b", "a", "c", "a", "b", "c", "d"}, 2));
        // edge: every word once, so the order is purely alphabetical
        check(v + " all words once", Arrays.asList("x", "y", "z"),
                () -> s.solve(new String[]{"z", "y", "x"}, 3));
        // edge: words that are prefixes of each other
        check(v + " prefixes", Arrays.asList("a", "aa", "aaa"),
                () -> s.solve(new String[]{"aa", "a", "aa", "a", "aaa"}, 3));
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
