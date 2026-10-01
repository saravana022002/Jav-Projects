package Leetcode.Ch14MergeAndTheKWayMerge;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 373. Find K Pairs with Smallest Sums   [Medium]
 * https://leetcode.com/problems/find-k-pairs-with-smallest-sums/
 * Topics: Array, Heap (Priority Queue)
 * Book: Algorithmic Thinking - ch. 14 Merge and the k-way merge, Practice #9
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   nums1 and nums2 are sorted in non-decreasing order. A pair takes one value u from nums1
 *   and one value v from nums2; its sum is u + v. Return the k pairs with the smallest sums,
 *   as a list of [u, v] lists. Pairs are told apart by their positions, so equal-looking pairs
 *   count separately. The examples list the pairs by increasing sum; the test accepts any order.
 *   Below, m = nums1.length and n = nums2.length.
 *
 * EXAMPLES
 *   nums1 = [1,7,11],  nums2 = [2,4,6],  k = 3  ->  [[1,2],[1,4],[1,6]]
 *       the pairs by increasing sum: [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 *   nums1 = [1,1,2],   nums2 = [1,2,3],  k = 2  ->  [[1,1],[1,1]]
 *       the pairs by increasing sum: [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
 *
 * CONSTRAINTS
 *   1 <= nums1.length, nums2.length <= 10^5,   -10^9 <= nums1[i], nums2[i] <= 10^9,
 *   both arrays are sorted in non-decreasing order,   1 <= k <= 10^4,   k <= m * n
 *
 * EARLIER SOLUTIONS IN THIS REPO
 *   src/Practice/Heap/LC373FindKPairsWithSmallestSums.java
 *   (an earlier attempt - look at it only after you have tried this one yourself)
 * </pre>
 */
public class LC373FindKPairsWithSmallestSums {

    /**
     * Variant A - brute force: build every pair, sort them by sum, keep the first k
     * <br>Target: time O(m n log(m n)), space O(m n)
     */
    public static List<List<Integer>> kSmallestPairsBruteForce(int[] nums1, int[] nums2, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - heap on the index grid: start at (0,0), expand right and down, skip seen cells
     * <br>Target: time O(k log k), space O(k)
     */
    public static List<List<Integer>> kSmallestPairsGrid(int[] nums1, int[] nums2, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C - this chapter's technique: k-way merge, each element of nums1 starts a stream
     * <br>Target: time O(k log min(m, k)), space O(min(m, k))
     */
    public static List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC373FindKPairsWithSmallestSums::kSmallestPairsBruteForce);
        runAll("B", LC373FindKPairsWithSmallestSums::kSmallestPairsGrid);
        runAll("C", LC373FindKPairsWithSmallestSums::kSmallestPairs);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 373. Find K Pairs with Smallest Sums)
    // https://leetcode.com/problems/find-k-pairs-with-smallest-sums/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases (expected pairs checked against an all-pairs reference).
    // The pairs may come back in any order, so the answer is sorted (by sum, then by first
    // value) before comparing; expected lists are in that order. Every case is built so that
    // the set of k smallest pairs is unique (no tie across the cut-off).
    //   Expected result: 0/15 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        List<List<Integer>> solve(int[] nums1, int[] nums2, int k);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", pairs(1, 2, 1, 4, 1, 6),
                () -> sorted(s.solve(new int[]{1, 7, 11}, new int[]{2, 4, 6}, 3)));
        check(v + " example 2", pairs(1, 1, 1, 1),
                () -> sorted(s.solve(new int[]{1, 1, 2}, new int[]{1, 2, 3}, 2)));
        // edge: k equals the number of pairs, so every pair is returned
        check(v + " all pairs", pairs(1, 3, 1, 4, 2, 3, 2, 4),
                () -> sorted(s.solve(new int[]{1, 2}, new int[]{3, 4}, 4)));
        // edge: the cheapest pairs run down nums1 first, then step along nums2
        check(v + " down then across", pairs(1, 1, 2, 1, 3, 1, 1, 10, 2, 10),
                () -> sorted(s.solve(new int[]{1, 2, 3}, new int[]{1, 10, 100}, 5)));
        // edge: extreme values (sums reach +-2 * 10^9) and two pairs with equal sums
        check(v + " extremes", pairs(-1000000000, -1000000000, -1000000000, 1000000000,
                        1000000000, -1000000000),
                () -> sorted(s.solve(new int[]{-1000000000, 1000000000},
                        new int[]{-1000000000, 1000000000}, 3)));
    }

    // builds a list of [u, v] pairs from u0, v0, u1, v1, ...
    private static List<List<Integer>> pairs(int... uv) {
        List<List<Integer>> out = new ArrayList<>();
        for (int i = 0; i < uv.length; i += 2) out.add(Arrays.asList(uv[i], uv[i + 1]));
        return out;
    }

    // order-insensitive form of an answer: pairs sorted by sum, then by first value
    private static List<List<Integer>> sorted(List<List<Integer>> a) {
        if (a == null) return null;
        List<List<Integer>> copy = new ArrayList<>(a);
        copy.sort((p, q) -> {
            long sp = (long) p.get(0) + p.get(1), sq = (long) q.get(0) + q.get(1);
            return sp != sq ? Long.compare(sp, sq) : Integer.compare(p.get(0), q.get(0));
        });
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
                + (ok ? "" : "   expected " + show(expected) + ", got " + show(got)));
    }

    private static String show(Object o) {
        if (o instanceof int[]) return Arrays.toString((int[]) o);
        if (o instanceof Object[]) return Arrays.deepToString((Object[]) o);
        return String.valueOf(o);
    }

    // ============================== END TESTS ===============================
}
