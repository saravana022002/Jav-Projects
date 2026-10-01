package Practice.Math;

// ============================================================================
// LC 2965. Find Missing and Repeated Values   [Easy]
// https://leetcode.com/problems/find-missing-and-repeated-values/
// Topics: Array, Hash Table, Math, Matrix
// ============================================================================
//
// PROBLEM
//   An n x n grid should hold every number 1..n^2 exactly once, but one number a shows
//   up twice and so one number b is absent. Return [a, b]: the repeated value first,
//   then the missing one.
//
// EXAMPLES
//   grid = [[1,3],[2,2]]              ->  [2,4]    2 is doubled, 4 never appears
//   grid = [[9,1,7],[8,9,2],[3,4,6]]  ->  [9,5]    9 is doubled, 5 never appears
//
// CONSTRAINTS
//   2 <= n == grid.length == grid[i].length <= 50,   1 <= grid[i][j] <= n * n
//   exactly one value in 1..n^2 is missing and exactly one appears twice
//
// INTUITION
//   Compare the grid with the perfect set 1..N (N = n^2). Call the missing value M
//   and the repeated value R.
//     perfect sum - actual sum                 = M - R      (M absent, R counted twice)
//     perfect sum of squares - actual squares  = M^2 - R^2 = (M - R) * (M + R)
//   Dividing the second line by the first gives M + R. With M - R and M + R known,
//   M = ((M - R) + (M + R)) / 2 and R = M - (M - R). Two equations, two unknowns,
//   no extra memory.
//
// APPROACH (as implemented below)
//   1. Walk the grid: sSumDash = actual sum, s2SumDash = actual sum of squares,
//      length = number of cells (N).
//   2. Perfect values: sSum = N(N+1)/2, s2Sum = N(N+1)(2N+1)/6.
//   3. differenceOfRM = sSum - sSumDash                       (= missing - repeating)
//   4. sumOfRM = (s2Sum - s2SumDash) / differenceOfRM         (= missing + repeating)
//   5. missing = (differenceOfRM + sumOfRM) / 2,  repeating = missing - differenceOfRM.
//   6. Return [repeating, missing] (Math.toIntExact turns the longs back into ints).
//
// DRY RUN   grid = [[1,3],[2,2]]   (the grid main() uses)
//   cells 1,3,2,2 -> sSumDash = 8, s2SumDash = 1 + 9 + 4 + 4 = 18, length = 4
//   sSum = 4*5/2 = 10,   s2Sum = 4*5*9/6 = 30
//   differenceOfRM = 10 - 8 = 2          sumOfRM = (30 - 18) / 2 = 6
//   missing = (2 + 6) / 2 = 4            repeating = 4 - 2 = 2        -> [2, 4]
//
// COMPLEXITY
//   Time : O(n^2) - every cell is read once (linear in the number of values)
//   Space: O(1) - a few long accumulators
//
// GOTCHAS
//   - The name differenceOfRM holds missing - repeating, and it may be negative (when
//     the repeated value is the larger one). The formulas still work because the sign
//     cancels in the division.
//   - It is never 0 (the two values differ), so the division is safe and exact.
//   - Sums of squares grow fast: (long) j * j and long accumulators avoid overflow.
//
// ALSO IN THIS FILE
//   repeatingAndMissing(int[] a) - the same task on a flat array holding 1..n with one
//   value doubled and one missing (Striver SDE Sheet "Repeat and Missing Number"). On
//   LeetCode this is LC 645 Set Mismatch, https://leetcode.com/problems/set-mismatch/
//   (also solved in src/Practice/Arrays/LC645SetMismatch.java). Same algebra with
//   N = a.length; it also returns [repeating, missing].
//
// STATUS
//   main() calls both methods but prints nothing: the grid gives [2, 4] and the array
//   {1, 4, 3, 4} gives [4, 2].
//
// RELATED
//   - LC 268 Missing Number - the same "perfect sum minus actual sum" idea when only one
//     value is unknown, so one equation is enough.
// ============================================================================
public class LC2965FindMissingAndRepeatedValues {
    public static void main(String[] args) {
        // 1-D version: 4 appears twice, 2 is missing -> [4, 2] (not printed)
        int[] a = {1, 4, 3, 4};
        repeatingAndMissing(a);

        // Example 1 - expected [2, 4] (not printed)
        int[][] grid = {{1,3},{2,2}};
        findMissingAndRepeatedValues(grid);

    }

    // returns [repeated value, missing value] for an n x n grid meant to hold 1..n^2
    public static int[] findMissingAndRepeatedValues(int[][] grid) {
        long length = 0;
        long sSumDash = 0;
        long sSum = 0;
        long s2SumDash = 0;
        long s2Sum = 0;

        long sumOfRM = 0;
        long differenceOfRM = 0;
        // actual sum and sum of squares; length counts the cells (N = n^2)
        for (int[] gridRow : grid) {
            for (int j : gridRow) {
                sSumDash += j;
                s2SumDash += (long) j * j;
                length++;
            }
        }

        // what the two sums would be for a perfect 1..N
        sSum = ( length * (length + 1) ) / 2;
        s2Sum = ( length * (length + 1) * (2* length + 1) ) / 6;


        long missing = 0;
        long repeating = 0;
        // perfect - actual = missing - repeating
        differenceOfRM = sSum - sSumDash;
        // (M^2 - R^2) / (M - R) = M + R
        sumOfRM = (s2Sum - s2SumDash) / differenceOfRM;
        // solve: M = ((M - R) + (M + R)) / 2, then R = M - (M - R)
        missing = (differenceOfRM + sumOfRM) / 2;
        repeating = missing - differenceOfRM ;

        int[] ans = new int[2];
        ans[0] = Math.toIntExact(repeating);
        ans[1] = Math.toIntExact(missing);

        return ans;
    }

    // ALSO IN THIS FILE: 1-D version (array of 1..n, LC 645 Set Mismatch) - same algebra
    // with N = a.length. Returns [repeating, missing].
    private static int[] repeatingAndMissing(int[] a) {
        long length = a.length;
        long sSumDash = 0;
        long sSum = 0;
        long s2SumDash = 0;
        long s2Sum = 0;

        long sumOfRM = 0;
        long differenceOfRM = 0;
        for (int j : a) {
            sSumDash += j;
            s2SumDash += (long) j * j;
        }

        sSum = ( length * (length + 1) ) / 2;
        s2Sum = ( length * (length + 1) * (2* length + 1) ) / 6;


        long missing = 0;
        long repeating = 0;
        differenceOfRM = sSum - sSumDash;
        sumOfRM = (s2Sum - s2SumDash) / differenceOfRM;
        missing = (differenceOfRM + sumOfRM) / 2;
        repeating = missing - differenceOfRM ;

        int[] ans = new int[2];
        ans[0] = Math.toIntExact(repeating);
        ans[1] = Math.toIntExact(missing);

        return ans;
    }
}
