package Leetcode.Ch07NonComparisonSortsCountingBucketRadix;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 220. Contains Duplicate III   [Hard]
 * https://leetcode.com/problems/contains-duplicate-iii/
 * Topics: Array, Sliding Window, Sorting, Bucket Sort, Ordered Set
 * Book: Algorithmic Thinking - ch. 7 Non-comparison sorts: counting, bucket, radix, Practice #10
 *       also: ch. 3 Hashing as a search-to-lookup transform (#12),
 *             ch. 41 Ordered maps in practice (#2)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   Decide whether nums has two different positions i and j that are close in position
 *   (|i - j| <= indexDiff) and close in value (|nums[i] - nums[j]| <= valueDiff).
 *   Return true if such a pair exists, otherwise false.
 *
 * EXAMPLES
 *   nums = [1,2,3,1],        indexDiff = 3, valueDiff = 0  ->  true
 *       (i, j) = (0, 3): the values are equal and the positions are 3 apart
 *   nums = [1,5,9,1,5,9],    indexDiff = 2, valueDiff = 3  ->  false
 *       no pair of positions satisfies both conditions
 *
 * CONSTRAINTS
 *   2 <= nums.length <= 10^5,   -10^9 <= nums[i] <= 10^9,
 *   1 <= indexDiff <= nums.length,   0 <= valueDiff <= 10^9
 *
 * EARLIER SOLUTIONS IN THIS REPO
 *   src/Leetcode/Ch03HashingAsASearchToLookupTransform/LC220ContainsDuplicateIII.java
 * </pre>
 */
public class LC220ContainsDuplicateIII {

    /**
     * Variant A
     * <br>Target: time O(n * indexDiff), space O(1)
     * <br>Complete first: ch. 1 Complexity, amortisation, and counting
     */
    public static boolean containsNearbyAlmostDuplicateA(
            int[] nums, int indexDiff, int valueDiff) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(n log k), space O(k)    (k = min(n, indexDiff))
     * <br>Complete first: ch. 9 Sliding window, chasing (later chapter; needs ch. 8 first)
     *     + ch. 41 Ordered maps in practice (later chapter; needs ch. 10 first)
     */
    public static boolean containsNearbyAlmostDuplicateB(
            int[] nums, int indexDiff, int valueDiff) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C
     * <br>Target: time O(n), space O(k)    (k = min(n, indexDiff))
     * <br>Complete first: ch. 3 Hashing as a search-to-lookup transform
     *     + ch. 7 Non-comparison sorts: counting, bucket, radix (this chapter)
     *     + ch. 9 Sliding window, chasing (later chapter; needs ch. 8 first)
     */
    public static boolean containsNearbyAlmostDuplicateC(int[] nums, int indexDiff, int valueDiff) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC220ContainsDuplicateIII::containsNearbyAlmostDuplicateA);
        runAll("B", LC220ContainsDuplicateIII::containsNearbyAlmostDuplicateB);
        runAll("C", LC220ContainsDuplicateIII::containsNearbyAlmostDuplicateC);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 220. Contains Duplicate III)
    // https://leetcode.com/problems/contains-duplicate-iii/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases checked against a brute-force reference.
    //   Expected result: 0/15 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        boolean solve(int[] nums, int indexDiff, int valueDiff);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", true, () -> s.solve(new int[]{1, 2, 3, 1}, 3, 0));
        check(v + " example 2", false, () -> s.solve(new int[]{1, 5, 9, 1, 5, 9}, 2, 3));
        // edge: negative and positive value just too far apart (6 > 5)
        check(v + " negative vs positive", false, () -> s.solve(new int[]{-3, 3}, 1, 5));
        // edge: a close pair exists, but too far apart in position (2 > 1)
        check(v + " pair outside the window", false,
                () -> s.solve(new int[]{10, 100, 11, 9, 100, 10}, 1, 1));
        // edge: values differ by exactly valueDiff and sit in neighbouring value ranges
        check(v + " exact value gap", true, () -> s.solve(new int[]{-4, 2}, 1, 6));
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
