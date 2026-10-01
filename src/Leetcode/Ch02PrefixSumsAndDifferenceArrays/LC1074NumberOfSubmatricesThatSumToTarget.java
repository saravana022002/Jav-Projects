package Leetcode.Ch02PrefixSumsAndDifferenceArrays;

import java.util.StringTokenizer;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 1074. Number of Submatrices That Sum to Target   [Hard]
 * https://leetcode.com/problems/number-of-submatrices-that-sum-to-target/
 * Topics: Array, Hash Table, Matrix, Prefix Sum
 * Book: Algorithmic Thinking - ch. 2 Prefix sums and difference arrays, Practice #12
 *
 * PROBLEM
 *   matrix is a grid of integers (negatives allowed). Count the non-empty rectangular
 *   blocks (submatrices) of it whose cells add up to exactly target. Two blocks are
 *   different as soon as one corner coordinate differs, so identical-looking blocks at
 *   different places are counted separately.
 *
 * EXAMPLES
 *   matrix = [[0,1,0],[1,1,1],[0,1,0]], target = 0  ->  4   the four 1x1 blocks holding a 0
 *   matrix = [[1,-1],[-1,1]],           target = 0  ->  5   two 1x2, two 2x1 and the whole 2x2
 *   matrix = [[904]],                   target = 0  ->  0
 *
 * CONSTRAINTS
 *   1 <= matrix.length <= 100,   1 <= matrix[0].length <= 100,
 *   -1000 <= matrix[i][j] <= 1000,   -10^8 <= target <= 10^8
 *
 * INTUITION
 *   Adding up a block cell by cell costs O(area). A 2D prefix sum makes it O(1):
 *   P[i][j] = sum of the whole rectangle from (0,0) to (i,j). The block (r1,c1)-(r2,c2) is
 *   the big rectangle P[r2][c2], minus the strip to its left P[r2][c1-1], minus the strip
 *   above it P[r1-1][c2]; the corner those two strips share was removed twice, so add
 *   P[r1-1][c1-1] back (inclusion-exclusion). With O(1) block sums we can afford to look at
 *   every block: pick its top-left and bottom-right corner (four nested loops) and compare.
 *   A 100 x 100 matrix has only 5050 * 5050 = 25,502,500 blocks.
 *
 * APPROACH (as implemented below)
 *   1. Build prefixSum[i][j] = matrix[i][j] + prefixSum[i-1][j] + prefixSum[i][j-1]
 *      - prefixSum[i-1][j-1]; a term whose index would be negative counts as 0.
 *   2. For every top-left (r1, c1) and every bottom-right (r2 >= r1, c2 >= c1):
 *        left    = prefixSum[r2][c1-1]     (0 when c1 == 0)
 *        top     = prefixSum[r1-1][c2]     (0 when r1 == 0)
 *        topLeft = prefixSum[r1-1][c1-1]   (0 when r1 == 0 or c1 == 0)
 *        block sum = prefixSum[r2][c2] - left - top + topLeft
 *      and count++ whenever the block sum equals target.
 *   3. Return count.
 *
 * DRY RUN   matrix = [[0,1,0],[1,1,1],[0,1,0]], target = 0 (the input main() runs)
 *   prefixSum =  0 1 1
 *                1 3 4
 *                1 4 5
 *   block (0,0)-(0,0): 0 - 0 - 0 + 0 = 0 == target -> counted (so are the other three corners)
 *   block (1,1)-(2,2): 5 - prefixSum[2][0] - prefixSum[0][2] + prefixSum[0][0]
 *                      = 5 - 1 - 1 + 0 = 3 (cells 1,1,1,0) -> not counted
 *   Only the four corner cells hold a 0 and no two of them touch, so no bigger block can
 *   sum to 0. count = 4.
 *
 * COMPLEXITY
 *   Time : O(R^2 * C^2) - building prefixSum is O(R * C); the four loops visit
 *          R(R+1)/2 * C(C+1)/2 blocks at O(1) each (about 2.55 * 10^7 for 100 x 100)
 *   Space: O(R * C) for prefixSum
 *   (A faster O(R^2 * C) exists: fix two rows, add the columns between them into one 1D
 *   array and count its subarrays that sum to target with a running-sum HashMap - the LC 560
 *   trick. See RELATED.)
 *
 * GOTCHAS
 *   - A block touching the top row or left column has no strip above / left of it: the
 *     ternaries use 0 there. topLeft needs BOTH r1 > 0 and c1 > 0, hence Math.min(r1, c1) > 0.
 *   - Precedence in "count += a - b - c + d == target ? 1 : 0": the arithmetic first, then
 *     ==, then ?:, then +=. It adds 1 exactly when the block sum equals target.
 *   - Negative cells and negative targets are fine because every block is tested; a sliding
 *     window would break on negatives.
 *   - No overflow: |block sum| <= 100 * 100 * 1000 = 10^7 and count <= 25,502,500.
 *   - The import java.util.StringTokenizer is unused.
 *
 * STATUS
 *   Correct (checked against a brute force on random matrices).
 *
 * RELATED
 *   - LC 304 Range Sum Query 2D - Immutable - the same 2D prefix-sum formula on its own.
 *   - LC 560 Subarray Sum Equals K (solved in src/Practice/PrefixSum) - 1D prefix sums with a
 *     HashMap; applied to every pair of rows it gives the faster O(R^2 * C) solution.
 * </pre>
 */
public class LC1074NumberOfSubmatricesThatSumToTarget {
    public static void main(String[] args) {
        // Example 1: the four 1x1 blocks that hold a 0 -> prints 4
        int[][] matrix =  {{0,1,0},{1,1,1},{0,1,0}};
        int  target = 0;
        System.out.println(numSubmatrixSumTarget(matrix , target));
        // run the test cases (TESTS section at the end of this class)
        runTests();
    }
    
    // Counts the blocks that sum to target: 2D prefix sums + every pair of corners. O(R^2 * C^2)
    public static int numSubmatrixSumTarget(int[][] matrix, int target) {
        int[][] prefixSum = new int[matrix.length][matrix[0].length];
        int rowSize = matrix.length;
        int colSize = matrix[0].length;
        // prefixSum[i][j] = sum of the rectangle (0,0)..(i,j): this cell + the rectangle above
        // + the rectangle to the left - the overlap of those two, which was added twice
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                prefixSum[i][j] = matrix[i][j] + (i > 0 ? prefixSum[i - 1][j] : 0) + (j > 0 ? prefixSum[i][j - 1] : 0) - ((Math.min(i, j) > 0) ? prefixSum[i - 1][j - 1] : 0);
            }
        }
        int count = 0;
        // try every block: top-left corner (r1, c1) and bottom-right corner (r2, c2)
        for (int r1 = 0; r1 < rowSize; r1++) {
            for (int c1 = 0; c1 < colSize; c1++) {
                for (int r2 = r1; r2 < rowSize; r2++) {
                    for (int c2 = c1; c2 < colSize; c2++) {
                        // strips outside the block: to its left, above it, and the corner
                        // they share (each is 0 when the block touches the matrix edge)
                        int left = c1 > 0 ? prefixSum[r2][c1 - 1] : 0;
                        int top = r1 > 0 ? prefixSum[r1 - 1][c2] : 0;
                        int topLeft = Math.min(r1, c1) > 0 ? prefixSum[r1 - 1][c1 - 1] : 0;
                        // block sum = big rectangle - left strip - top strip + shared corner;
                        // count it if it equals target (== is applied before the ?: and the +=)
                        count += prefixSum[r2][c2] - left - top + topLeft == target ? 1 : 0;
                    }
                }
            }
        }
        return count;
    }

    // ================================ TESTS =================================
    // Tests for this class 
    // (LC 1074. Number of Submatrices That Sum to Target)
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Cases: the three official examples + edge cases. Expected values come from the statement,
    // from the reasoning noted per case, and (checked offline) from a brute force that adds up
    // every submatrix cell by cell.
    //   Expected result: 7/7 passed
    // ==========================================================================
    private static void runTests() {
        // official examples
        check("example 1: four 1x1 blocks of 0", 4, () -> LC1074NumberOfSubmatricesThatSumToTarget
                .numSubmatrixSumTarget(new int[][]{{0, 1, 0}, {1, 1, 1}, {0, 1, 0}}, 0));
        check("example 2: [[1,-1],[-1,1]]", 5, () -> LC1074NumberOfSubmatricesThatSumToTarget
                .numSubmatrixSumTarget(new int[][]{{1, -1}, {-1, 1}}, 0));
        check("example 3: [[904]]", 0, () -> LC1074NumberOfSubmatricesThatSumToTarget
                .numSubmatrixSumTarget(new int[][]{{904}}, 0));
        // edge: a single cell that equals the target
        check("single cell equals target", 1, () -> LC1074NumberOfSubmatricesThatSumToTarget
                .numSubmatrixSumTarget(new int[][]{{5}}, 5));
        // edge: negative cells and a negative target - two 1x2 and two 2x1 blocks sum to -2
        check("negative cells, target -2", 4, () -> LC1074NumberOfSubmatricesThatSumToTarget
                .numSubmatrixSumTarget(new int[][]{{-1, -1}, {-1, -1}}, -2));
        // edge: all zeros - every block counts: (3 * 4 / 2) * (3 * 4 / 2) = 36
        check("3x3 of zeros, target 0", 36, () -> LC1074NumberOfSubmatricesThatSumToTarget
                .numSubmatrixSumTarget(new int[3][3], 0));
        // edge: the largest matrix, all zeros: (100 * 101 / 2)^2 = 25502500 blocks
        check("100x100 of zeros, target 0", 25502500, () -> LC1074NumberOfSubmatricesThatSumToTarget
                .numSubmatrixSumTarget(new int[100][100], 0));
        System.out.println(passed + "/" + total + " passed");
    }

    private static int passed = 0, total = 0;

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
        if (o instanceof long[]) return Arrays.toString((long[]) o);
        if (o instanceof char[]) return Arrays.toString((char[]) o);
        if (o instanceof boolean[]) return Arrays.toString((boolean[]) o);
        if (o instanceof Object[]) return Arrays.deepToString((Object[]) o);
        return String.valueOf(o);
    }

    // ============================== END TESTS ===============================
}
