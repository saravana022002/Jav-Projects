package Leetcode.Ch07NonComparisonSortsCountingBucketRadix;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 1051. Height Checker   [Easy]
 * https://leetcode.com/problems/height-checker/
 * Topics: Array, Sorting, Counting Sort, Bubble Sort
 * Book: Algorithmic Thinking - ch. 7 Non-comparison sorts: counting, bucket, radix, Practice #1
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   Students line up for a photo and are supposed to stand in non-decreasing order of height.
 *   heights[i] is the height of the student who currently stands at position i. Imagine the
 *   correctly ordered line-up and count the positions where the student standing there now
 *   is a different height from the one who belongs there.
 *
 * EXAMPLES
 *   heights = [1,1,4,2,1,3]  ->  3     correct line-up [1,1,1,2,3,4]; positions 2, 4, 5 differ
 *   heights = [5,1,2,3,4]    ->  5     correct line-up [1,2,3,4,5]; every position differs
 *   heights = [1,2,3,4,5]    ->  0     already in order
 *
 * CONSTRAINTS
 *   1 <= heights.length <= 100,   1 <= heights[i] <= 100
 * </pre>
 */
public class LC1051HeightChecker {

    /**
     * Variant A
     * <br>Target: time O(n log n), space O(n)
     * <br>Complete first: ch. 6 Sorting as a preprocessing step
     */
    public static int heightCheckerA(int[] heights) {
        // TODO: write your solution
        int[] expected = heights.clone();
        Arrays.sort(expected);
        int ans = 0;
        for (int i = 0; i < heights.length; i++) {
            if(expected[i] != heights[i]){
                ans++;
            }
        }
        return ans;
    }

    /**
     * Variant B
     * <br>Target: time O(n + R), space O(R)    (R = largest possible height = 100)
     * <br>Complete first: ch. 7 Non-comparison sorts: counting, bucket, radix (this chapter)
     */
    public static int heightCheckerB(int[] heights) {
        // TODO: write your solution
        int[] count = new int[101];
        for (int height : heights) {
            count[height]++;
        }
        int j = 0;
        int ans = 0;
        for (int i = 0; i < count.length; i++) {
            while (count[i] > 0){
                if(heights[j] != i) ans++;
                count[i]--;
                j++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC1051HeightChecker::heightCheckerA);
        runAll("B", LC1051HeightChecker::heightCheckerB);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 1051. Height Checker)
    // https://leetcode.com/problems/height-checker/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B) is checked separately with the same cases: the three official
    // examples plus edge cases checked against a reference solution.
    //   Expected result: 0/12 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int solve(int[] heights);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", 3, () -> s.solve(new int[]{1, 1, 4, 2, 1, 3}));
        check(v + " example 2", 5, () -> s.solve(new int[]{5, 1, 2, 3, 4}));
        check(v + " example 3", 0, () -> s.solve(new int[]{1, 2, 3, 4, 5}));
        // edge: a single student
        check(v + " single student", 0, () -> s.solve(new int[]{7}));
        // edge: reversed line, the middle student is already in place
        check(v + " reversed", 4, () -> s.solve(new int[]{5, 4, 3, 2, 1}));
        // edge: smallest and largest allowed heights, duplicates
        check(v + " boundary heights", 4, () -> s.solve(new int[]{100, 1, 100, 1, 50}));
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
