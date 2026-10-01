package Leetcode.Ch11BinarySearchOnTheAnswer;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 378. Kth Smallest Element in a Sorted Matrix   [Medium]
 * https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
 * Topics: Array, Binary Search, Sorting, Heap (Priority Queue), Matrix
 * Book: Algorithmic Thinking - ch. 11 Binary search on the answer, Practice #10
 *       also: ch. 14 Merge and the k-way merge (#8)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   matrix is an n x n grid whose rows and columns never decrease (left to right, top to
 *   bottom). Find the entry that would come k-th if all n * n entries were sorted. Equal
 *   entries are counted one by one (it is not the k-th distinct value). The statement
 *   wants memory use better than O(n^2).
 *
 * EXAMPLES
 *   matrix = [[1,5,9],[10,11,13],[12,13,15]], k = 8  ->  13    sorted: 1,5,9,10,11,12,13,13,15
 *   matrix = [[-5]],                          k = 1  ->  -5
 *
 * CONSTRAINTS
 *   n == matrix.length == matrix[i].length,   1 <= n <= 300,   -10^9 <= matrix[i][j] <= 10^9,
 *   all rows and columns are sorted in non-decreasing order,   1 <= k <= n^2
 *   Follow up: constant memory; or even O(n) time (a known advanced algorithm).
 * </pre>
 */
public class LC378KthSmallestElementInASortedMatrix {

    /**
     * Variant A - brute force: copy every entry into one array, sort it (over the memory limit)
     * <br>Target: time O(n^2 log n), space O(n^2)
     */
    public static int kthSmallestSort(int[][] matrix, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - min-heap holding the next unread entry of each row, pop k times
     * <br>Target: time O(n + k log n), space O(n)
     */
    public static int kthSmallestHeap(int[][] matrix, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C - this chapter's technique: binary search on the value, count entries <= mid
     * <br>Target: time O(n log(max - min)), space O(1)
     */
    public static int kthSmallest(int[][] matrix, int k) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC378KthSmallestElementInASortedMatrix::kthSmallestSort);
        runAll("B", LC378KthSmallestElementInASortedMatrix::kthSmallestHeap);
        runAll("C", LC378KthSmallestElementInASortedMatrix::kthSmallest);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class 
    // (LC 378. Kth Smallest Element in a Sorted Matrix)
    // https://leetcode.com/problems/kth-smallest-element-in-a-sorted-matrix/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases checked against an independent reference (flatten and sort).
    //   Expected result: 0/15 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    private static int hung = 0;   // cases of the current variant that never finished
    private static final long TIMEOUT_MS = 5000;

    interface Solver {
        int solve(int[][] matrix, int k);
    }

    private static void runAll(String v, Solver s) {
        hung = 0;
        // official examples
        check(v + " example 1", 13,
                () -> s.solve(new int[][]{{1, 5, 9}, {10, 11, 13}, {12, 13, 15}}, 8));
        check(v + " example 2", -5, () -> s.solve(new int[][]{{-5}}, 1));
        // edge: k = 1 with negative values, the top-left corner is the answer
        check(v + " smallest", -5, () -> s.solve(new int[][]{{-5, -4}, {-3, -1}}, 1));
        // edge: k = n * n, the bottom-right corner is the answer
        check(v + " largest", 3, () -> s.solve(new int[][]{{1, 2}, {1, 3}}, 4));
        // edge: many equal values, k lands on the last copy of a repeated value
        check(v + " duplicates", 2,
                () -> s.solve(new int[][]{{1, 2, 2}, {2, 2, 3}, {2, 3, 3}}, 6));
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
