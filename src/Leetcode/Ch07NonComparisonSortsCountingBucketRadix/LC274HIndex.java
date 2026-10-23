package Leetcode.Ch07NonComparisonSortsCountingBucketRadix;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 274. H-Index   [Medium]
 * https://leetcode.com/problems/h-index/
 * Topics: Array, Sorting, Counting Sort
 * Book: Algorithmic Thinking - ch. 7 Non-comparison sorts: counting, bucket, radix, Practice #8
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   citations[i] is the number of citations the i-th paper of a researcher received. The
 *   h-index is the largest h such that the researcher has at least h papers with at least
 *   h citations each. Return the h-index.
 *
 * EXAMPLES
 *   citations = [3,0,6,1,5]  ->  3     three papers (3, 6, 5) have at least 3 citations each
 *   citations = [1,3,1]      ->  1
 *
 * CONSTRAINTS
 *   n == citations.length,   1 <= n <= 5000,   0 <= citations[i] <= 1000
 * </pre>
 */
public class LC274HIndex {

    /**
     * Variant A
     * <br>Target: time O(n^2), space O(1)
     * <br>Complete first: ch. 1 Complexity, amortisation, and counting
     */
    public static int hIndexA(int[] citations) {
        // TODO: write your solution
        int[] range = new int[1000 + 1];
        for(int i = 0; i < range.length; i++){
            for (int citation : citations) {
                if (i <= citation) {
                    range[i]++;
                }
            }
        }

        int ans = Integer.MIN_VALUE;
        for (int i = 0; i < 1001; i++) {
            ans = Math.max(ans, Math.min(i, range[i]));
        }
        return ans;
    }

    /**
     * Variant B
     * <br>Target: time O(n log n), space O(1)
     * <br>Complete first: ch. 6 Sorting as a preprocessing step
     */
    public static int hIndexB(int[] citations) {
        // TODO: write your solution
        Arrays.sort(citations);
        int max = Integer.MIN_VALUE;
        for (int j = citations.length - 1; j >= 0; j--) {
            max = Math.max(max, Math.min(citations.length - j, citations[j]));
        }
        return max;
    }

    /**
     * Variant C
     * <br>Target: time O(n), space O(n)
     * <br>Complete first: ch. 7 Non-comparison sorts: counting, bucket, radix (this chapter)
     */
    public static int hIndexC(int[] citations) {
        // TODO: write your solution

        int[] range = new int[1001];
        for (int citation : citations) {
            range[citation]++;
        }

        int sum = 0;
        int max = Integer.MIN_VALUE;
        for (int i = range.length - 1; i >= 0; i--) {
            if(range[i] > 0){
                sum += range[i];
                max = Math.max(max, Math.min(sum, i));
            }
        }
        return max;
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC274HIndex::hIndexA);
        runAll("B", LC274HIndex::hIndexB);
        runAll("C", LC274HIndex::hIndexC);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 274. H-Index)
    // https://leetcode.com/problems/h-index/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases checked against a brute-force reference. Every case passes a
    // fresh array, so a variant that sorts its input in place is fine.
    //   Expected result: 0/15 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int solve(int[] citations);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", 3, () -> s.solve(new int[]{3, 0, 6, 1, 5}));
        check(v + " example 2", 1, () -> s.solve(new int[]{1, 3, 1}));
        // edge: nobody has been cited
        check(v + " all zeros", 0, () -> s.solve(new int[]{0, 0, 0, 0}));
        // edge: one very highly cited paper, h can never exceed the number of papers
        check(v + " single paper", 1, () -> s.solve(new int[]{100}));
        // edge: every paper qualifies, so h equals the number of papers
        check(v + " h equals n", 5, () -> s.solve(new int[]{5, 5, 5, 5, 5}));
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
