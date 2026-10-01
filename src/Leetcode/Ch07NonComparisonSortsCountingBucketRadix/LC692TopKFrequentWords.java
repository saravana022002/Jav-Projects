package Leetcode.Ch07NonComparisonSortsCountingBucketRadix;

import java.util.List;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 692. Top K Frequent Words   [Medium]
 * https://leetcode.com/problems/top-k-frequent-words/
 * Topics: Array, Hash Table, String, Trie, Sorting, Heap (Priority Queue), Bucket Sort, Counting
 * Book: Algorithmic Thinking - ch. 7 Non-comparison sorts: counting, bucket, radix, Practice #7
 *       also: ch. 18 Heaps and the k-selection family (#6)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   Return the k words that occur most often in words. List them from the most frequent to
 *   the least frequent; words with the same frequency are listed in alphabetical
 *   (lexicographic) order, smaller word first.
 *   Follow-up: can it be done in O(n log k) time with O(n) extra space?
 *
 * EXAMPLES
 *   words = ["i","love","leetcode","i","love","coding"], k = 2  ->  ["i","love"]
 *       "i" and "love" both occur twice; "i" comes first alphabetically
 *   words = ["the","day","is","sunny","the","the","the","sunny","is","is"], k = 4
 *       ->  ["the","is","sunny","day"]     they occur 4, 3, 2 and 1 times
 *
 * CONSTRAINTS
 *   1 <= words.length <= 500,   1 <= words[i].length <= 10,
 *   words[i] consists of lowercase English letters,   k is in the range [1, number of unique words]
 * </pre>
 */
public class LC692TopKFrequentWords {

    /**
     * Variant A - count the words, sort the distinct words by (count, word), take the first k
     * <br>Target: time O(n log n), space O(n)
     */
    public static List<String> topKFrequentSort(String[] words, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - count the words, keep a heap of at most k words with the right tie-break
     * <br>Target: time O(n log k), space O(n)
     */
    public static List<String> topKFrequentHeap(String[] words, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C - this chapter's technique: bucket the words by count, order each bucket by word
     * <br>Target: time O(n log n), space O(n)
     */
    public static List<String> topKFrequent(String[] words, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC692TopKFrequentWords::topKFrequentSort);
        runAll("B", LC692TopKFrequentWords::topKFrequentHeap);
        runAll("C", LC692TopKFrequentWords::topKFrequent);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 692. Top K Frequent Words)
    // https://leetcode.com/problems/top-k-frequent-words/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases checked against a reference solution. The order of the returned
    // list matters here (frequency first, then alphabetical), so lists are compared as they are.
    //   Expected result: 0/15 passed (every case fails until you implement the methods)
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
        // edge: two words tie for the last place, the alphabetically smaller one wins
        check(v + " tie at the cut-off", Arrays.asList("a"),
                () -> s.solve(new String[]{"b", "a", "b", "a"}, 1));
        // edge: every word occurs once, so the whole answer is alphabetical
        check(v + " all once, k = all", Arrays.asList("a", "b", "c"),
                () -> s.solve(new String[]{"c", "b", "a"}, 3));
        // edge: a word that is a prefix of another one sorts first
        check(v + " prefix words", Arrays.asList("a", "aa"),
                () -> s.solve(new String[]{"aaa", "aa", "a", "aa", "aaa", "a", "a"}, 2));
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
