package Practice.Arrays;

import java.util.Arrays;

// ============================================================================
// LC 118. Pascal's Triangle   [Easy]
// https://leetcode.com/problems/pascals-triangle/
// Topics: Array, Dynamic Programming
// ============================================================================
//
// PROBLEM
//   Given N (numRows on LeetCode), return the first N rows of Pascal's triangle.
//   The top row is [1]. Every row starts and ends with 1, and each number in between is
//   the sum of the two numbers just above it (same position and one to the left, in the
//   row above). So row i (0-based) holds i + 1 numbers.
//
// EXAMPLES
//   numRows = 5  ->  [[1],[1,1],[1,2,1],[1,3,3,1],[1,4,6,4,1]]
//                    e.g. the 6 in the last row is 3 + 3 from the row above it
//   numRows = 1  ->  [[1]]      only the top row
//
// CONSTRAINTS
//   1 <= numRows <= 30
//
// INTUITION
//   A row depends only on the row right above it: its two ends are 1 and every inner
//   cell adds the two cells above it. So build the rows top to bottom and fill each one
//   from the row that is already finished - a small DP where "the previous row" is the
//   only state needed.
//
// APPROACHES IN THIS FILE
//   A. approachOwnCode(N) - inner cell = sum of the two cells above it - O(N^2)
//                           <- main() prints this one
//   B. approachOptimal(N) - unfinished try at C(i,j) = C(i,j-1) * (i+1-j) / j,
//                           returns wrong rows (see STATUS); main() ignores its result
//   (APPROACH and DRY RUN below describe A.)
//
// APPROACH (as implemented below, approachOwnCode)
//   1. Allocate a jagged int[N][]; row i gets its own int[i + 1].
//   2. For every cell (i, j): if it is an edge (j == 0 or i == j), store 1.
//   3. Otherwise array[i][j] = array[i - 1][j] + array[i - 1][j - 1]
//      (the cell directly above + the cell above-left).
//   4. Return array.
//
// DRY RUN   N = 5 (what main() runs)
//   i=0: [1]
//   i=1: [1,1]                                  both cells are edges
//   i=2: j=1: 1 + 1 = 2                      => [1,2,1]
//   i=3: j=1: 2 + 1 = 3,  j=2: 1 + 2 = 3     => [1,3,3,1]
//   i=4: j=1: 3 + 1 = 4,  j=2: 3 + 3 = 6,  j=3: 1 + 3 = 4   => [1,4,6,4,1]
//   main() prints [[1], [1, 1], [1, 2, 1], [1, 3, 3, 1], [1, 4, 6, 4, 1]]
//
// STATUS
//   approachOwnCode (the one main() prints) is correct.
//   Unfinished: approachOptimal (the combination-formula idea) has three defects:
//     - its outer loop stops at i < N - 1, so the last row is left null;
//     - its inner loop stops at j < i, so the last cell of each row is left 0;
//     - ((i + 1 - j) / j) divides BEFORE multiplying and truncates (row 4 gets 4 instead
//       of 6 in the middle).
//   For N = 5 it returns [[1],[1,0],[1,2,0],[1,3,3,0],null]. main() stores it in array2
//   and never uses it. The code is left unchanged on purpose.
//   Signature differs from LeetCode: returns a jagged int[][] instead of
//   List<List<Integer>>. The statement once pasted here was the Coding Ninjas version of
//   this same problem (N <= 30).
//
// COMPLEXITY
//   Time : O(N^2) - each of the N(N+1)/2 cells is filled once in O(1)
//   Space: O(N^2) for the returned triangle; nothing extra besides it
//
// GOTCHAS
//   - Rows have different lengths, so each row is allocated separately (jagged array).
//   - Check the edges first: at j == i the row above has no index j, so the sum formula
//     would go out of bounds there.
//   - int is plenty: the largest value for N = 30 is C(29,14) = 77558760.
//
// RELATED
//   - LC 119 Pascal's Triangle II - return one row only; the same "sum of the two cells
//     above" update works in a single array filled from right to left.
//
// SEE ALSO
//   LC118PascalsTriangleCombinationFormula.java - builds each row from its own previous
//   entry with C(i,j) = C(i,j-1) * (i-j+1) / j (the correct version of approachOptimal)
// ============================================================================
public class LC118PascalsTrianglePreviousRow {

    public static void main(String[] args) {

        // Example 1: N = 5. Prints [[1], [1, 1], [1, 2, 1], [1, 3, 3, 1], [1, 4, 6, 4, 1]]
        int N = 5;

        int[][] array = approachOwnCode(N);
        // array2 is never used; approachOptimal returns wrong rows (see STATUS)
        int[][] array2 = approachOptimal(N);
        System.out.println(Arrays.deepToString(array));
    }

    // Approach A: every inner cell is the sum of the two cells above it. O(N^2).
    // Returns the first N rows as a jagged array.
    private static int[][] approachOwnCode(int N) {
        int[][] array = new int[N][];

        for (int i = 0; i <= N - 1; i++) {
            // row i has i + 1 cells
            array[i] = new int[i + 1];
            for (int j = 0; j <= i; j++) {
                // both ends of a row are 1 (and they have no two parents to add)
                if (j == 0 || i == j) {
                    array[i][j] = 1;
                } else {
                    // directly above + above-left, both already in the finished row i - 1
                    array[i][j] = array[i - 1][j] + array[i - 1][j - 1];
                }
            }
        }
        return array;
    }


    // Approach B (unfinished, WRONG - see STATUS): meant to fill row i with
    // C(i,j) = C(i,j-1) * (i+1-j) / j. Not used by main().
    private static int[][] approachOptimal(int N) {
        int[][] array = new int[N][];

        // bug: i < N - 1 never builds the last row, so array[N - 1] stays null
        for (int i = 0; i < N - 1; i++) {

            array[i] = new int[i + 1];
            array[i][0] = 1;
            // bug: j < i never writes the last cell array[i][i], so it stays 0
            for (int j = 1; j < i; j++) {
                // bug: (i + 1 - j) / j is truncated before the multiply; the correct order is
                // array[i][j - 1] * (i + 1 - j) / j
                array[i][j] = array[i][j-1] * ((i+ 1- j) / j);
            }

        }

        return array;
    }
}
